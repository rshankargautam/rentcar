package car.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import car.pojo.Customer;
import car.pojo.Orders;
import car.service.CarService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private CarService carService;

	/*****************************************************************/

	@GetMapping(path = "login")
	public String authenticateUser(@RequestBody Customer user) {
		if (carService.authenticateUser(user) != null) {
			if (carService.authenticateUser(user).getRole().equals("admin"))
				return "admin";
			else if(carService.authenticateUser(user).getRole().equals("user"))
				return "customer-home";
			else
				throw new InvalidUserException("User not found");
		}
		return "Welcome " + user.getCustName();
	}
	/*****************************************************************/

	@PostMapping(path = "register", consumes= { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Customer addCust(@RequestBody Customer cust) {
		System.out.println("CustomerRestController=> /customer/register");
		cust.setRole("user");
		cust.setCustBlocked(false);
		return carService.addCust(cust);
	}

	/*****************************************************************/

	@PostMapping(path = "rentcar")
	public Orders rentCar(@RequestBody Orders order) {
		if(carService.checkBlockedCustomer(order.getCustomer().getCustLoginName())==true) {
			throw new BlockedUserException("Your ID is Blocked");
		}else {
			carService.rentCar(order);
		}
		return order;
	}

}

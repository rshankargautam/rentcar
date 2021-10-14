package car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import car.pojo.Car;
import car.pojo.Customer;
import car.pojo.Orders;
import car.service.CarService;

@RestController
@RequestMapping("admin")
public class AdminRestController {

	@Autowired
	private CarService carService;

	/*****************************************************************/

	@PutMapping(path = "customer")
	public String editCust(@RequestBody Customer cust) {
		carService.editCust(cust);
		return "customer edited";
	}

	/*****************************************************************/

	@DeleteMapping(path = "customer")
	public String deleteCust(@RequestBody Customer cust) {
		carService.deleteCust(cust);
		return "customer deleted";
	}

	/*****************************************************************/

	@PostMapping(path = "car")
	public String addCar(@RequestBody Car car) {
		car.setCarStatus("available");
		carService.addCar(car);
		return "car added";
	}

	/*****************************************************************/

	@PutMapping(path = "car")
	public String editCar(@RequestBody Car car, BindingResult result) {
		car.setCarStatus("available");
		if (result.hasErrors()) {
			return "error";
		} else {
			carService.editCar(car);
		}
		return "car edited";
	}

	/*****************************************************************/

	@DeleteMapping(path = "car")
	public String deleteCar(@RequestBody Car car) {
		carService.deleteCar(car);
		return "car deleted";
	}


	@GetMapping(path = "cars")
	public List<Car> showCars() {
		
		return carService.showCars();

	}

	/*****************************************************************/

	@GetMapping(path = "transactions")
	public List<Orders> transactionReport() {
		
		return carService.transactionReport();
	}

	/*****************************************************************/

	@GetMapping(path = "customers")
	public List<Customer> seeCustomer() {
		
		return carService.seeCustomer();
	}

	/*****************************************************************/

	@GetMapping(path = "cartype/{carNumber}")
	public Car getCarType(@PathVariable String carNumber) {
		return carService.getCarType(carNumber);
	}

	/*****************************************************************/

	@GetMapping(path = "isuserblocked/{custLoginName}")
	public String checkBlockedCustomer(@PathVariable String custLoginName) {
		
		if (carService.checkBlockedCustomer(custLoginName) == true)
			return "blocked";

		return "not blocked";

	}

	/*****************************************************************/

	@PutMapping(path = "block/{custLoginName}")
	public String blockCustomer(@PathVariable String custLoginName) {
		if (carService.checkBlockedCustomer(custLoginName) == true)
			return "Already blocked";
		else
			carService.blockCustomer(custLoginName);
		
		return "customer is blocked";
	}
}

package car.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import car.dao.CarDao;
import car.pojo.Car;
import car.pojo.Customer;
import car.pojo.Orders;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	  CarDao dao;

	@Override
	@Transactional
	public Customer addCust(Customer cust) {
		cust.setCustBlocked(false);
		cust.setRole("user");
		dao.addCust(cust);
		return cust;
	}

	@Override
	@Transactional
	public Customer editCust(Customer cust) {
		dao.editCust(cust);
		return cust;
	}

	@Override
	@Transactional
	public Customer deleteCust(Customer cust) {
		dao.deleteCust(cust);
		return cust;
	}

	@Override
	@Transactional
	public Car addCar(Car car) {
		car.setCarStatus("Available");
		dao.addCar(car);

		return car;
	}

	@Override
	public Car editCar(Car car) {
		dao.editCar(car);
		return car;
	}

	@Override
	@Transactional
	public Car deleteCar(Car car) {
		dao.deleteCar(car);
		return car;
	}

	@Override
	@Transactional
	public Orders rentCar(Orders order) {
		if(checkBlockedCustomer(order.getCustomer().getCustLoginName())==false) {
			order.setRentPrice(order.getKm()*6);
			dao.rentCar(order);
		}
		return order;
	}

	@Override
	@Transactional
	public List<Car> showCars() {

		return dao.showCars();
	}

	@Override
	@Transactional
	public List<Orders> transactionReport() {

		return dao.transactionReport();
	}

	@Override
	@Transactional
	public List<Customer> seeCustomer() {

		return dao.seeCustomer();
	}

	@Override
	@Transactional
	public Car getCarType(String carNumber) {
		return dao.getCarType(carNumber);
	}

	@Override
	@Transactional
	public Customer authenticateUser(Customer user) {
		return dao.authenticateUser(user);
	}

	@Override
	@Transactional
	public boolean checkBlockedCustomer(String custLoginName) {
		return dao.checkBlockedCustomer(custLoginName);
	}

	@Override
	@Transactional
	public Customer blockCustomer(String custLoginName) {
		Customer cust = new Customer();
		cust.setCustLoginName(custLoginName);
		cust.setCustBlocked(true);
		dao.blockCustomer(cust);
		return cust;
	}
}
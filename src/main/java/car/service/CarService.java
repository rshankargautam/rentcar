package car.service;

import java.util.List;

import car.pojo.Car;
import car.pojo.Customer;
import car.pojo.Orders;

public interface CarService {
	public Customer addCust(Customer cust) ;
	public Customer editCust(Customer cust) ;
	public Customer deleteCust(Customer cust) ;

	public Car addCar(Car car) ;
	public Car editCar(Car car) ;
	public Car deleteCar(Car car) ;

	public Orders rentCar(Orders order) ;

	public List<Car> showCars() ;

	public List<Orders> transactionReport() ;
	public List<Customer> seeCustomer() ;
	public Car getCarType(String carNumber) ;

	public Customer authenticateUser(Customer user) ;
	public boolean checkBlockedCustomer(String custLoginName) ;
	public Customer blockCustomer(String custLoginName) ;
}

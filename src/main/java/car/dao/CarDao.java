package car.dao;

import java.util.List;

import car.pojo.Car;
import car.pojo.Customer;
import car.pojo.Orders;

public interface CarDao {
	public void addCust(Customer cust);

	public void editCust(Customer cust);

	public void deleteCust(Customer cust);

	public void addCar(Car car);

	public void editCar(Car car);

	public void deleteCar(Car car);

	public void rentCar(Orders order);

	public List<Car> showCars();

	public List<Orders> transactionReport();

	public List<Customer> seeCustomer();

	public Car getCarType(String carNumber);

	public Customer authenticateUser(Customer user);

	public boolean checkBlockedCustomer(String custLoginName);

	public void blockCustomer(Customer cust);
}

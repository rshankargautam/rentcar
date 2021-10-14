package car.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import car.pojo.Car;
import car.pojo.Customer;
import car.pojo.Orders;

@Repository
public class CarDaoImpl implements CarDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@ExceptionHandler
	public void addCust(Customer cust) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(cust);
			System.out.println("in add customer");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@ExceptionHandler
	public void editCust(Customer cust) {
		Session session = sessionFactory.getCurrentSession();
		if (authenticateUser(cust) != null) {
			session.merge(cust);
		}
		session.saveOrUpdate(cust);
	}

	@Override
	@ExceptionHandler
	public void deleteCust(Customer cust) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cust);
	}

	@Override
	@Transactional
	@ExceptionHandler
	public void addCar(Car car) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(car);
		session.getTransaction().commit();
	}

	@Override
	@Transactional
	@ExceptionHandler
	public void editCar(Car car) {
		Session session = sessionFactory.getCurrentSession();
		Car uc = session.get(Car.class, car.getCarNumber(), LockMode.PESSIMISTIC_WRITE);
		uc.setCarType(car.getCarType());
		session.saveOrUpdate(uc);
	}

	@Override
	@Transactional
	@ExceptionHandler
	public void deleteCar(Car car) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(car);
	}

	@Override
	@Transactional
	@ExceptionHandler
	public List<Car> showCars() {
		List<Car> car = new ArrayList<Car>();
		try {
			Session session = sessionFactory.getCurrentSession();
			car = session.createQuery("from car").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}

	@Override
	@Transactional
	@ExceptionHandler
	public void blockCustomer(Customer cust) {
		Session session = sessionFactory.getCurrentSession();
		Customer uc = session.get(Customer.class, cust.getCustLoginName(), LockMode.PESSIMISTIC_WRITE);
		uc.setCustBlocked(cust.getCustBlocked());
		session.saveOrUpdate(uc);
	}

	@Override
	@Transactional
	@ExceptionHandler
	public boolean checkBlockedCustomer(String custLoginName) {
		List<Customer> customers = new ArrayList<Customer>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("custLoginName", custLoginName));
		customers = criteria.list();
		if (customers.get(0).getCustBlocked() == true)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	@ExceptionHandler
	public List<Customer> seeCustomer() {
		List<Customer> cust = new ArrayList<Customer>();
		try {
			Session session = sessionFactory.getCurrentSession();
			cust = session.createQuery("from customer").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
	}

	@Override
	@Transactional
	@ExceptionHandler
	public List<Orders> transactionReport() {
		List<Orders> orders = new ArrayList<Orders>();
		try {
			Session session = sessionFactory.getCurrentSession();
			orders = session.createQuery("from Order").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	@ExceptionHandler
	public void rentCar(Orders order) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(order);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@ExceptionHandler
	public Car getCarType(String carType) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Car.class);
		criteria.add(Restrictions.eq("carType", carType));
		List<Car> car = criteria.list();
		return car.get(0);
	}

	@Override
	@ExceptionHandler
	public Customer authenticateUser(Customer user) {

		List<Customer> cust = new ArrayList<Customer>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("name", user.getCustLoginName()));
			criteria.add(Restrictions.eq("custPass", user.getCustPass()));
			cust = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cust.get(0) != null) {
			return cust.get(0);
		}
		return null;
	}

}

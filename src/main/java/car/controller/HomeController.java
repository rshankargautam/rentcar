package car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import car.service.CarService;

@Controller
public class HomeController {

	@Autowired
	private CarService carService;

	@RequestMapping("/")
	public String home() {

		return "home";
	}


}

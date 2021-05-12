package spring.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	
	@RequestMapping("/home")
	public static String home() {
		return "index";
	}
	
	@RequestMapping("/home2")
	public static String home2() {
		return "index2";
	}
	

	
}

package es.cuore80getafe.cuore80getafe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/defaultsite")
	public String defaultsite() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
}

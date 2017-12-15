package fr.dta.webapprest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String foo() {
		return "Hello wooooooooooooooooooorld";
	}
	
}

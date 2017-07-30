package com.rurallabs.sportsbets.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

	private static final String VIEW_BASE = "admin/";

	public AdminController() {
		super();
	}

	@RequestMapping("/")
	public String index() {
		return VIEW_BASE + "index";
	}
}

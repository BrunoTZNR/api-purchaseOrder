package com.purchase.purchaseApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

	@RequestMapping("/home")
	public String index() {
		return "Bruno Marco da Silva - 202120832<br>Ésoj Airam Alves Rodrigues - 202120628";
	}
}

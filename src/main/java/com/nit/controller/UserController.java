package com.nit.controller;

import java.util.Random;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/")
	public String getHome() {
		return "Welcome to spring security\n<a href=balance>balance</a>\n<a href=offers>offers</a>\n<a href=deposite>deposite</a> <a href=logout>Logout</a>";
	}
	
	@GetMapping("/balance")
	//@PreAuthorize("hasRole('ROLE_USER')")
	public String getBalance() {
		return "Your Account Balance is "+new Random().nextInt(100000)+"<a href=logout>Logout</a>";
	}
	@GetMapping("/offers")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String getOffers() {
		return "Offer is "+new Random().nextInt(100)+"%"+" <a href=logout>Logout</a>";
	}
	@GetMapping("/deposite")
	public String getDeposite() {
		return "You Successfully Deposited with"+new Random().nextInt(50000)+" <a href=logout>Logout</a>";
	}
	@GetMapping("/denied")
	public String authorisationFail() {
		return "<h1 style=color:red;text-align:center>Authorization failed</h1";
	}
	}

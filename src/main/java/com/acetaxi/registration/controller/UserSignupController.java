package com.acetaxi.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;
import com.acetaxi.registration.service.UserSignupServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/acetaxi")
@Slf4j
public class UserSignupController {

	@Autowired
	private UserSignupServiceImpl userSignupServiceImpl;
//	@Autowired
//	private RegistrationServiceImpl registrationServiceImpl;

	@PostMapping("/driver/signup")
	public UserResp driverSignup(@RequestBody User user) {
		log.info("driverSignup(-)");
		return userSignupServiceImpl.userSignup(user);

	}

	@GetMapping("/driver/login")
	public UserResp login(@RequestParam String email, @RequestParam String password) {
		log.info("login(-)");
		return userSignupServiceImpl.loginValidation(email, password);

	}

	@PutMapping("/driver/resetpassword")
	public Response resetPassword(@RequestParam String email, @RequestParam String newpassword) {
		log.info("resetPassword email(-) :" + email);
		return userSignupServiceImpl.resetPassword(email, newpassword);

	}
	/*
	 * 
	 * @PostMapping("/addDriver") public Response saveDriver(@RequestBody Driver
	 * driver) { log.info("saveDriver(-)"); return
	 * registrationServiceImpl.addDriver(driver); }
	 */
	

}

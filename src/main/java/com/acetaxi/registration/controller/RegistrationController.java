package com.acetaxi.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acetaxi.registration.entity.Driver;
import com.acetaxi.registration.entity.Vehicle;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;
import com.acetaxi.registration.service.RegistrationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/acetaxi")
@Slf4j
public class RegistrationController {

	@Autowired
	private RegistrationServiceImpl registrationServiceImpl;

	@PostMapping("/driver/addDriver")
	public Response saveDriver(@RequestBody Driver driver) {
		log.info("saveDriver(-)");
		return registrationServiceImpl.addDriver(driver);
	}

	@PostMapping("/driver/addVehicle")
	public Response saveVehicle(@RequestBody Vehicle vehicle) {
		log.info("saveVehicle(-)");
		return registrationServiceImpl.addVehicle(vehicle);
	}

	@GetMapping("/driver/getDriver")
	public Driver getDriver(@RequestParam String email) {
		log.info("getDriver(-)");
		return registrationServiceImpl.getDriver(email);
	}

	@GetMapping("/driver/getUser")
	public UserResp getUser(@RequestParam String email) {
		log.info("getUser(-)");
		return registrationServiceImpl.getUser(email);
	}

	@GetMapping("/driver/getVehicle")
	public Vehicle getVehicle(@RequestParam String email) {
		log.info("getVehicle(-)");
		return registrationServiceImpl.getVehicle(email);
	}

	@DeleteMapping("/admin/deleteDriver")
	public Driver deleteDriver(@RequestParam String email) {
		log.info("deleteDriver(-)");
		return registrationServiceImpl.deleteDriver(email);
	}

	@DeleteMapping("/admin/deleteVehicle")
	public Vehicle deleteVehicle(@RequestParam String vehicleNumber) {
		log.info("deleteVehicle(-)");
		return registrationServiceImpl.deleteVehicle(vehicleNumber);
	}

	@GetMapping("/admin/getAllDrivers")
	public List<Driver> getAllDrivers() {
		log.info("getAllDrivers(-)");
		return registrationServiceImpl.getAllDrivers();
	}

	@GetMapping("/admin/getAllVehicles")
	public List<Vehicle> getAllVehicles() {
		log.info("getAllVehicles(-)");
		return registrationServiceImpl.getAllVehicles();
	}
}

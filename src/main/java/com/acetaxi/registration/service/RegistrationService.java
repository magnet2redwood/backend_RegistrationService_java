package com.acetaxi.registration.service;

import com.acetaxi.registration.entity.Driver;
import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.entity.Vehicle;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;

public interface RegistrationService {

	public Response addDriver(Driver driver);
	
	public UserResp getUser(String email);
	public Driver getDriver(String email);
	public Vehicle getVehicle(String vehicleNumber);
}

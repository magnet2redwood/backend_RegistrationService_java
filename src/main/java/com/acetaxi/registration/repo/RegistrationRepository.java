package com.acetaxi.registration.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acetaxi.registration.entity.Driver;
import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.entity.Vehicle;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RegistrationRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public Response addDriver(Driver driver) {
		log.info("addDriver(-)");
		Response res = new Response();
		try {
			dynamoDBMapper.save(driver);
			res.setStatus("success");
		} catch (Exception e) {
			res.setErrorMessage(e.getMessage());
			res.setStatus("failed");
			log.error(e.getMessage());
		}
		return res;
	}

	public Response addVehicle(Vehicle vehicle) {
		log.info("addVehicle(-)");
		Response res = new Response();
		try {
//			vehicle.setEmail(email);
			dynamoDBMapper.save(vehicle);
			res.setStatus("success");
		} catch (Exception e) {
			res.setErrorMessage(e.getMessage());
			res.setStatus("failed");
			log.error(e.getMessage());
		}
		return res;
	}
	
	public Driver getDriver(String email) {
		log.info("getDriver(-)");
		try {
			Driver driver = dynamoDBMapper.load(Driver.class, email);
			return driver;
		} catch (Exception e) {
			log.error("Error querying DynamoDB:" + e.getMessage());
			return null;
		}
	}

	
	public UserResp getUser(String email) {
		log.info("getUser(-)");
		UserResp resp=new UserResp();
		try {
			User user = dynamoDBMapper.load(User.class, email);
			if(user!=null) {
				resp.setFirstName(user.getFirstName());
				resp.setLastName(user.getLastName());
				resp.setMobileNumber(user.getMobileNumber());
				resp.setEmail(user.getEmail());
			}
			return resp;
		} catch (Exception e) {
			log.error("Error querying DynamoDB: " + e.getMessage());
			return null;
		}
	}

	
	public Vehicle getVehicle(String email) {
		try {
			Vehicle vehicle = dynamoDBMapper.load(Vehicle.class, email);
			return vehicle;
		} catch (Exception e) {
			log.error("Error querying DynamoDB: " + e.getMessage());
			return null;
		}
	}
	public Driver deleteDriver(String email) {
		try {
			// Create a new Driver object with the email as the primary key
			Driver driver = new Driver();
			driver.setEmail(email);

			// Use the DynamoDBMapper to delete the item
			dynamoDBMapper.delete(driver);

			// Return the deleted driver (if needed)
			return driver;
		} catch (Exception e) {
			log.error("Error deleting item from DynamoDB: " + e.getMessage());
			return null;
		}
	}
	
	public Vehicle deleteVehicle(String vehicleNumber) {
		try {
			// Create a new Driver object with the email as the primary key
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleNumber(vehicleNumber);

			// Use the DynamoDBMapper to delete the item
			dynamoDBMapper.delete(vehicle);

			// Return the deleted driver (if needed)
			return vehicle;
		} catch (Exception e) {
			log.error("Error deleting item from DynamoDB: " + e.getMessage());
			return null;
		}
	}

}

package com.acetaxi.registration.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acetaxi.registration.entity.Driver;
import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.entity.Vehicle;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;
import com.acetaxi.registration.repo.RegistrationRepository;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;

	@Override
	public Response addDriver(Driver driver) {
		log.info("addDriver(-)");
		return registrationRepository.addDriver(driver);

	}

	public Response addVehicle(Vehicle vehicle) {
		log.info("addVehicle(-)");
		return registrationRepository.addVehicle(vehicle);

	}

	public Driver getDriver(String email) {
		return registrationRepository.getDriver(email);
	}
	
	public UserResp getUser(String email) {
		return registrationRepository.getUser(email);
	}


	public Vehicle getVehicle(String email) {
		return registrationRepository.getVehicle(email);
	}

	public Driver deleteDriver(String email) {
		return registrationRepository.deleteDriver(email);
	}

	public Vehicle deleteVehicle(String vehicleNumber) {
		return registrationRepository.deleteVehicle(vehicleNumber);
	}

	public List<Driver> getAllDrivers() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIA43QVUG3FPQUAW3V2",
				"IuqbiSXwEwrkylCqe98pbtLNUovA1ilT3brHnroW");

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		DynamoDB dynamoDB = new DynamoDB(client);
		List<Driver> drivers = new ArrayList<>();

		// Get a reference to the DynamoDB table
		Table table = dynamoDB.getTable("Driver");

		// Perform a scan to retrieve all items from the table
		ItemCollection<ScanOutcome> items = table.scan();

		// Iterate through the items and convert them to Driver objects
		for (Item item : items) {
			Driver driver = new Driver();
//	            driver.setId(item.getString("id"));
			driver.setFirstName(item.getString("firstName"));
			driver.setLastName(item.getString("lastName"));
			driver.setMobileNumber(item.getString("mobileNumber"));
			driver.setEmail(item.getString("email"));
			driver.setDob(item.getString("dob"));
			// Assuming you have a list of vehicles
//	             driver.setListofVehicle(new HashSet<>(item.getList("listofVehicle")));
			drivers.add(driver);
		}

		return drivers;
	}
	
	
	public List<Vehicle> getAllVehicles() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIA43QVUG3FPQUAW3V2",
				"IuqbiSXwEwrkylCqe98pbtLNUovA1ilT3brHnroW");

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		DynamoDB dynamoDB = new DynamoDB(client);
		List<Vehicle> vehicles = new ArrayList<>();

		// Get a reference to the DynamoDB table
		Table table = dynamoDB.getTable("Vehicle");

		// Perform a scan to retrieve all items from the table
		ItemCollection<ScanOutcome> items = table.scan();

		// Iterate through the items and convert them to Driver objects
		for (Item item : items) {
			Vehicle vehicle = new Vehicle();
//	            driver.setId(item.getString("id"));
			vehicle.setVehicleNumber(item.getString("vehicleNumber"));
			vehicle.setVehicleName(item.getString("vehicleName"));
			vehicle.setEmail(item.getString("email"));
			// Assuming you have a list of vehicles
//	             driver.setListofVehicle(new HashSet<>(item.getList("listofVehicle")));
			vehicles.add(vehicle);
		}

		return vehicles;
	}

}

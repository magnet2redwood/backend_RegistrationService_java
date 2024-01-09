package com.acetaxi.registration.entity;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@DynamoDBDocument
@Component
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Vehicle")
public class Vehicle {


	@DynamoDBHashKey(attributeName = "email")
	private String email;
	@DynamoDBAttribute(attributeName = "vehicleNumber")
	private String vehicleNumber;
	@DynamoDBAttribute(attributeName = "vehicleName")
	private String vehicleName;

	@DynamoDBAttribute(attributeName = "status")
	private String status;
	@DynamoDBAttribute(attributeName = "typeofVehicle")
	private String typeofVehicle;
	@DynamoDBAttribute(attributeName = "vehicleModel")
	private String vehicleModle;
	@DynamoDBAttribute(attributeName = "manufactureDate")
	private String manufactureDate;
	@DynamoDBAttribute(attributeName = "ageOfVehicle")
	private String ageOfVehicle;
	@DynamoDBAttribute(attributeName = "chasisNumber")
	private String chasisNumber;
	@DynamoDBAttribute(attributeName = "engineNumber")
	private String engineNumber;
	@DynamoDBAttribute(attributeName = "pollutionCertificate")
	private String pollutionCertificate;
	@DynamoDBAttribute(attributeName = "vehicleCategory")
	private String vehicleCategory;
	@DynamoDBAttribute(attributeName = "typeOfOwner")
	private String typeOfOwner;
	@DynamoDBAttribute(attributeName = "vehicleSeating")
	private String vehicleSeating;
	@DynamoDBAttribute(attributeName = "typeOfTaxiBoard")
	private String typeOfTaxiBoard;
	@DynamoDBAttribute(attributeName = "vehicleInsuranceCompany")
	private String vehicleInsuranceCompany;
	@DynamoDBAttribute(attributeName = "vehicleInsuranceExpiry")
	private String vehicleInsuranceExpiry;
	@DynamoDBAttribute(attributeName = "drivingLicenceNumber")
	private String drivingLicenceNumber;
	@DynamoDBAttribute(attributeName = "licenceExpiry")
	private String licenceExpiry;
	@DynamoDBAttribute(attributeName = "vehiclaeDamage")
	private String vehiclaeDamage;
	
	


}

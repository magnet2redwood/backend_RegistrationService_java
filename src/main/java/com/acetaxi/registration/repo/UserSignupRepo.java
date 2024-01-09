package com.acetaxi.registration.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acetaxi.registration.entity.Driver;
import com.acetaxi.registration.entity.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserSignupRepo {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	public User save(User user) {
		dynamoDBMapper.save(user);
		return user;
	}

	/*
	 * public User getUserByEmail(String email) { // Create a scan expression to
	 * search for the user by email DynamoDBScanExpression scanExpression = new
	 * DynamoDBScanExpression(); scanExpression.addFilterCondition("email", new
	 * Condition().withComparisonOperator(ComparisonOperator.EQ)
	 * .withAttributeValueList(new AttributeValue(email)));
	 * 
	 * // Perform the scan PaginatedScanList<User> users =
	 * dynamoDBMapper.scan(User.class, scanExpression);
	 * 
	 * // Check if a user with the given email exists if (!users.isEmpty()) { //
	 * Return the first user found (assuming email is unique) return users.get(0); }
	 * else { // No user found with the specified email return null; } }
	 */

	public User getUser(String email) {

		try {
			User user = dynamoDBMapper.load(User.class, email);
			return user;
		} catch (Exception e) {
			log.error("Error querying DynamoDB: " + e.getMessage());
			return null;
		}

	}

	public void updatePasswordByEmail(User user) {
		dynamoDBMapper.save(user);
		log.info("user updated");

	}

}

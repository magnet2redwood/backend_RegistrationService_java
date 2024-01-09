package com.acetaxi.registration.modle;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {

	private String firstName;
	private String lastName;
	private String email;
	private String status;
	private String errorMessage;
	private String mobileNumber;
	
}

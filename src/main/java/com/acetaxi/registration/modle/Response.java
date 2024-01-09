package com.acetaxi.registration.modle;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private String status;
	private String errorMessage;
}

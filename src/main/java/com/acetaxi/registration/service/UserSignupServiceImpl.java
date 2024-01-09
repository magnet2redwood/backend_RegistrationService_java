package com.acetaxi.registration.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;
import com.acetaxi.registration.repo.UserSignupRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSignupServiceImpl implements UserSingupService {

	@Autowired
	UserSignupRepo userSignupRepo;

	@Override
	public UserResp userSignup(User user) {
		UserResp resp = new UserResp();
		try {
			User existinguser = userSignupRepo.getUser(user.getEmail());

			log.info("User email :" + user.getEmail());
			if (null != existinguser) {
				log.info("user exist");
				resp.setStatus("userexits");
				resp.setFirstName(existinguser.getFirstName());
				resp.setLastName(existinguser.getLastName());
				resp.setEmail(existinguser.getEmail());
				resp.setMobileNumber(existinguser.getMobileNumber());
				return resp;
			}

			user.setPassword(pwdEncoder(user.getPassword()));
			log.info("new user signup process");

			userSignupRepo.save(user);
			resp.setStatus("success");
			resp.setFirstName(user.getFirstName());
			resp.setLastName(user.getLastName());
			resp.setEmail(user.getEmail());
			resp.setMobileNumber(user.getMobileNumber());
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus("failed");
			resp.setErrorMessage(e.getMessage());
			return resp;
		}
		return resp;
	}

	public UserResp loginValidation(String email, String pwd) {
		UserResp res = new UserResp();
		User existinguser = userSignupRepo.getUser(email);
		if (existinguser != null) {
			log.info("pwd :" + pwd);
			if (pwdDecoder(existinguser.getPassword()).equals(pwd)) {
				res.setStatus("success");
				res.setFirstName(existinguser.getFirstName());
				res.setLastName(existinguser.getLastName());
				res.setMobileNumber(existinguser.getMobileNumber());
				res.setEmail(existinguser.getEmail());
			} else {
				res.setFirstName(existinguser.getFirstName());
				res.setLastName(existinguser.getLastName());
				res.setMobileNumber(existinguser.getMobileNumber());
				res.setEmail(existinguser.getEmail());
				res.setStatus("wrong pwd");
			}
		} else {
			log.info("user not exist");
			res.setStatus("user not exist");
		}

		return res;
	}

	public Response resetPassword(String email, String newpwd) {
		log.info("resetPassword(-)");
		Response resp = new Response();
		User existinguser = userSignupRepo.getUser(email);
		if (existinguser != null) {
			log.info("existing user :" + existinguser.getEmail());
			log.info("new pwd  :" + existinguser.getPassword()+"encoded pwd :"+pwdEncoder(newpwd));
			existinguser.setPassword(pwdEncoder(newpwd));
			try {
				userSignupRepo.updatePasswordByEmail(existinguser);
				resp.setStatus("success");
			} catch (Exception e) {
				e.printStackTrace();
				resp.setStatus("failed");
			}
		} else {
			resp.setStatus("user not exist");
		}
		return resp;

	}

	private String pwdEncoder(String pwd) {
		log.info("password before encode :" + pwd);
		String BasicBase64format = Base64.getEncoder().encodeToString(pwd.getBytes());
		log.info("Encoded String:\n" + BasicBase64format);
		return BasicBase64format;

	}

	private String pwdDecoder(String pwd) {
		byte[] actualByte = Base64.getDecoder().decode(pwd);
		String actualpwd = new String(actualByte);
		log.info("actualpwd :" + actualpwd);
		return actualpwd;
	}
}

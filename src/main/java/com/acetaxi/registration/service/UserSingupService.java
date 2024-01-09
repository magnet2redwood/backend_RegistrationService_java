package com.acetaxi.registration.service;

import com.acetaxi.registration.entity.User;
import com.acetaxi.registration.modle.Response;
import com.acetaxi.registration.modle.UserResp;

public interface UserSingupService {

	public UserResp userSignup(User user);

	public UserResp loginValidation(String userName, String pwd);

	public Response resetPassword(String email, String newpwd);
}

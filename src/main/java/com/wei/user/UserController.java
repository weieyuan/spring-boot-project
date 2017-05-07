package com.wei.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wei.model.UserRegistry;
import com.wei.vo.CommonRes;
import com.wei.vo.User;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "registry")
	public CommonRes registry(@Valid @RequestBody UserRegistry oUserRegistry, BindingResult result) {

		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			return new CommonRes(false, error.getDefaultMessage());
		}
		return userService.registry(oUserRegistry);
	}

	@PostMapping(value = "login")
	public CommonRes login(@Valid @RequestBody User oUser, BindingResult result) {

		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			return new CommonRes(false, error.getDefaultMessage());
		}

		return userService.login(oUser);

	}

}

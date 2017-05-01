package com.wei.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wei.model.UserRegistry;
import com.wei.vo.CommonRes;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "registry")
	public CommonRes registry(@RequestBody UserRegistry oUserRegistry) {

		return userService.registry(oUserRegistry);
	}

}

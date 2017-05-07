package com.wei.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class User {

	@Getter
	@Setter
	@NotNull(message = "用户名或者密码不正确")
	@Size(min = 1, max = 10, message = "用户名或者密码不正确")
	private String userName;

	@Getter
	@Setter
	@NotNull(message = "用户名或者密码不正确")
	@Size(min = 8, max = 20, message = "用户名或者密码不正确")
	private String passWord;

}

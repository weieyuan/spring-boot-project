package com.wei.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
public class UserRegistry {

	@Id
	@GeneratedValue
	@Setter
	@Getter
	private Long id;

	@Setter
	@Getter
	@NotNull(message = "邮箱地址不能为空")
	@Pattern(regexp = "^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "邮箱地址不正确")
	private String email;

	@Setter
	@Getter
	@NotNull(message = "用户名不能为空")
	@NotEmpty(message = "用户名不能为空")
	@Size(min = 1, max = 10, message = "用户名长度不能超过10")
	private String userName;

	@Setter
	@Getter
	@NotNull(message = "密码不能为空")
	@NotEmpty(message = "密码不能为空")
	@Size(min = 8, max = 20, message = "密码长度不能小于8且不能大于20")
	private String passWord;

}

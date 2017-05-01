package com.wei.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private String email;

	@Setter
	@Getter
	private String userName;
	@Setter
	@Getter
	private String passWord;

}

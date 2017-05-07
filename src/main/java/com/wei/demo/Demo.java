package com.wei.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wei.utils.EncryptorUtils;

@Controller
public class Demo {

	@RequestMapping(value = "/")
	@ResponseBody
	public String doSth() {
		// 生成数据库用户名和密码的密文
		String dataBaseUserName = "platform_admin";
		String dataBasePassword = "weieyuan";
		String userName = EncryptorUtils.encrypt(dataBaseUserName);
		String passWord = EncryptorUtils.encrypt(dataBasePassword);
		System.out.println(userName);
		System.out.println(passWord);
		System.out.println(EncryptorUtils.decrypt(userName));
		System.out.println(EncryptorUtils.decrypt(passWord));

		return "Hello World!";
	}

}

package com.wei.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Demo {

	@RequestMapping(value = "/")
	@ResponseBody
	public String doSth() {
		return "Hello World!";
	}

}

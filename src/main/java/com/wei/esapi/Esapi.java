package com.wei.esapi;

import org.owasp.esapi.reference.crypto.JavaEncryptor;

public class Esapi {

	/**
	 * 将ESAPI.properties和validation.properties放置到c://user/用户名/esapi目录下
	 * 用于生成ESAPI.properties文件中的Encryptor.MasterKey和Encryptor.MasterSal
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		JavaEncryptor.main(args);
	}

}

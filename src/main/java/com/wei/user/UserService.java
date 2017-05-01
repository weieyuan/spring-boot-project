package com.wei.user;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wei.model.UserRegistry;
import com.wei.repository.UserRegistryRepository;
import com.wei.vo.CommonRes;

@Service
public class UserService {

	@Autowired
	private UserRegistryRepository userRegistryRepository;

	public CommonRes registry(UserRegistry oUserRegistry) {
		// 校验
		String email = oUserRegistry.getEmail();
		String userName = oUserRegistry.getUserName();
		String passWord = oUserRegistry.getPassWord();
		boolean b = ESAPI.validator().isValidInput("邮箱地址不正确", email, "Email", 256, false);
		if (!b) {
			return new CommonRes(false, "邮箱地址不正确");
		}
		if (userName.length() <= 0) {
			return new CommonRes(false, "用户名不能为空");
		}
		if (userName.length() > 10) {
			return new CommonRes(false, "用户名长度不能超过10");
		}
		if (passWord.length() < 8) {
			return new CommonRes(false, "密码的长度不能小于8");
		}
		if (passWord.length() > 20) {
			return new CommonRes(false, "密码的长度不能大于20");
		}

		try {
			// 加密
			CipherText oCipherText;
			oCipherText = ESAPI.encryptor().encrypt(new PlainText(oUserRegistry.getUserName()));
			oUserRegistry.setUserName(oCipherText.getEncodedIVCipherText());
			oCipherText = ESAPI.encryptor().encrypt(new PlainText(oUserRegistry.getPassWord()));
			oUserRegistry.setPassWord(oCipherText.getEncodedIVCipherText());
			// 保存
			userRegistryRepository.save(oUserRegistry);

		} catch (EncryptionException e) {
			e.printStackTrace();
		}

		return new CommonRes(true, "注册成功");
	}

}

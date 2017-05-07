package com.wei.user;

import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncryptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wei.filter.FilterHelper;
import com.wei.model.UserRegistry;
import com.wei.repository.UserRegistryRepository;
import com.wei.utils.EncryptorUtils;
import com.wei.utils.HttpUtils;
import com.wei.vo.CommonRes;
import com.wei.vo.User;

@Service
public class UserService {

	@Autowired
	private UserRegistryRepository userRegistryRepository;

	public CommonRes registry(UserRegistry oUserRegistry) {
		// 校验
		// String email = oUserRegistry.getEmail();
		String userName = oUserRegistry.getUserName();
		String passWord = oUserRegistry.getPassWord();
		// boolean b = ESAPI.validator().isValidInput("邮箱地址不正确", email, "Email",
		// 256, false);
		Iterable<UserRegistry> lstUsers = userRegistryRepository.findAll();
		Iterator<UserRegistry> ite = lstUsers.iterator();
		while (ite.hasNext()) {
			UserRegistry oDUserRegistry = ite.next();
			String oDUserName = EncryptorUtils.decrypt(oDUserRegistry.getUserName());
			if (oDUserName.equals(userName)) {
				return new CommonRes(false, "该用户名已经被注册，请使用其他用户名");
			}
		}
		// 加密
		oUserRegistry.setUserName(EncryptorUtils.encrypt(userName));
		oUserRegistry.setPassWord(EncryptorUtils.encrypt(passWord));
		// 保存
		userRegistryRepository.save(oUserRegistry);

		return new CommonRes(true, "html/registry_success.html");
	}

	public CommonRes login(User oUser) {
		String userName = oUser.getUserName();
		String passWord = oUser.getPassWord();

		Iterable<UserRegistry> lstUsers = userRegistryRepository.findAll();
		Iterator<UserRegistry> ite = lstUsers.iterator();
		while (ite.hasNext()) {
			UserRegistry oDUserRegistry = ite.next();
			String oDUserName = EncryptorUtils.decrypt(oDUserRegistry.getUserName());
			if (oDUserName.equals(userName)) {
				String oDPassWord = EncryptorUtils.decrypt(oDUserRegistry.getPassWord());
				if (oDPassWord.equals(passWord)) {
					// 记录用户的登录状态
					HttpServletRequest request = HttpUtils.getHttpServletRequest();
					HttpSession session = request.getSession();
					String randomStr = "";
					try {
						randomStr = ESAPI.randomizer().getRandomGUID();
					} catch (EncryptionException e) {
						randomStr = UUID.randomUUID().toString();
					}
					session.setAttribute(FilterHelper.USER_TOKEN, randomStr);
					Cookie cookie = new Cookie(FilterHelper.USER_TOKEN, randomStr);
					cookie.setHttpOnly(true);
					HttpServletResponse response = HttpUtils.getHttpServletResponse();
					response.addCookie(cookie);
					return new CommonRes(true, "html/login_success.html");
				}
				break;
			}
		}

		return new CommonRes(false, "用户名或密码错误");
	}

}

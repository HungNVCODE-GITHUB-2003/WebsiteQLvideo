package com.poly.service.implement;

import javax.servlet.ServletContext;

import com.poly.entity.User;
import com.poly.service.EmailService;
import com.poly.util.SendMailUtil;

public class EmailServiceImpl implements EmailService {

	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertaiment HungSmall";
	private static final String EMAIL_FORGOT_PASSWORD = "Online Entertaiment HungSmall - New Password";

	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");

		try {
			String content = null;
			String subject = null;
			switch (type) {
			case "welcom": {
				subject = "EMAIL_WELCOME_SUBJECT";
				content = "Xin chào " + recipient.getUsername() + ", Have a good time watching the movie ^^";
				break;
			}
			case "forgot": {
				subject = "EMAIL_FORGOT_PASSWORD";
				content = "Dear " + recipient.getUsername() + ", your new password here  : "
						+ recipient.getPassword();
				break;
			}
			default:
				subject = "Online Entertaiment HungSmall";
				content = "Maybe this email is wrong , don't care about it ";
			}
			SendMailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

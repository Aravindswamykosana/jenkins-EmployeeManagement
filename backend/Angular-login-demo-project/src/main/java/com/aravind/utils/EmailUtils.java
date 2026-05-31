package com.aravind.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public void sendResetEmail(String toEmail, String token) {
		try {
			String resetLink = "http://localhost:4200/reset-password?token=" + token;

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(toEmail);
			helper.setSubject("Password Reset Request");

			String content = """
					<html>
					  <body>
					    <h2 style="color: #2E86C1;">Reset Your Password</h2>
					    <p>Hello,</p>
					    <p>Click the button below to reset your password:</p>
					    <a href="%s" style="
					        display: inline-block;
					        padding: 10px 20px;
					        font-size: 16px;
					        color: white;
					        background-color: #2E86C1;
					        text-decoration: none;
					        border-radius: 5px;">Reset Password</a>
					    <p>If you didn’t request this, you can ignore this email.</p>
					    <br>
					    <p>Thanks,<br>The Team</p>
					  </body>
					</html>
					""".formatted(resetLink);

			helper.setText(content, true); // true → means HTML

			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendWelcomeEmail(String toEmail, String name) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setTo(toEmail);
	        helper.setSubject("Welcome to Our App!");

	        String content = """
	            <html>
	              <body>
	                <h2 style="color: #2E86C1;">Hello, %s!</h2>
	                <p>Thank you for registering with us.</p>
	                <p>We’re excited to have you on board!</p>
	                <br>
	                <p>Regards,<br>The Team</p>
	              </body>
	            </html>
	            """.formatted(name);
	        helper.setText(content, true);
	        mailSender.send(message);

	    } catch (Exception e) {
	        e.printStackTrace();
	        // or log the exception
	    }
	}


}

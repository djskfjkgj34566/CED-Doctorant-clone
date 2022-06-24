package com.ced.soutenancemodule.controller;

import com.ced.soutenancemodule.model.User;
import com.ced.soutenancemodule.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("api/v1/ms-soutenance")
public class MailController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;

	@RequestMapping("send-mail")
	public String send() {

		user.setFirstName("bill");
		//user.setLastName("fresnel");
		user.setEmailAddress("gofresnel@gmail.com"); //Receiver's email address
		/*List<String> emails = new ArrayList<>();
		emails.add("gofresnel@gmail.com");
		emails.add("ngomafresnel@gmail.com");
		user.setEmailAddress(emails);*/

		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

	@RequestMapping("send-mail-attachment")
	public String sendWithAttachment(@ModelAttribute User user) throws MessagingException {
	//User user1 = new User();
	user.setEmailAddress(user.getEmailAddress());
	user.setEmailAddress2(user.getEmailAddress2());
	user.setSujet(user.getSujet());
		user.setFirstName("bill");

		//user.setEmailAddress("gofresnel@gmail.com"); //Receiver's email address
		//user.setEmailAddress2("ngomafresnel@gmail.com");
		/*List<String> emails = new ArrayList<>();
		emails.add("gofresnel@gmail.com");
		emails.add("ngomafresnel@gmail.com");
		user.setEmailAddress(emails);*/

		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}

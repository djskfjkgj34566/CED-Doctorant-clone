package com.ced.soutenancemodule.service;

import com.ced.soutenancemodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class MailService {

	private final JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(User user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setSubject("Testing Mail API");
		mail.setText("Thank you");
		javaMailSender.send(mail);
	}

	public void sendEmailWithAttachment(User user)throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		/*List<String> emails = new ArrayList<>();
		emails.add(user.getEmailAddress());
		emails.add(user.getEmailAddress2());
		helper.setTo(InternetAddress.parse("emails"));*/
		helper.setTo(new String[]{user.getEmailAddress(),user.getEmailAddress2()});

		//helper.setTo(user.getEmailAddress());
		helper.setSubject(user.getSujet());
		//helper.setSubject("Démande d'encadrement de l'UCA");
		helper.setText(
				"Bonjour Chers Professeurs !"
						+"\n\n Nous vous envoyons  ce message car vous avez été selectionne comme rapporteurs"
						+ "\n\n Please find the attached document below."
						+ "\n\n http://localhost:8083/api/v1/ms-file/binaire/e452cff1-1b7c-4b31-9b6d-09416ebbcd33-Capture.png"
						+ "\n\n http://localhost:8083/api/v1/ms-file/binaire/4ffc3f9f-2b8f-48c8-a1d2-589b9244218c-Chap3_Série2.pdf"
						+ "\n\n http://localhost:8083/api/v1/ms-file/binaire/da842f27-0593-4bdc-af4d-38897f507037-doc.rar");

		String aaa="Attachmentt.pdf";
		ClassPathResource classPathResource1 = new ClassPathResource(aaa);
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(Objects.requireNonNull(classPathResource1.getFilename()), classPathResource1);
		helper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);

		javaMailSender.send(message);
	}

}

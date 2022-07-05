package com.ced.soutenancemodule.service;

import com.ced.soutenancemodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
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
						+ "\n\n Please find the attached document below.");
		String aaa="Attachmentt.pdf";
		ClassPathResource classPathResource1 = new ClassPathResource(aaa);
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(Objects.requireNonNull(classPathResource1.getFilename()), classPathResource1);
		helper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);

		javaMailSender.send(message);
	}

	public int sendEmailWithUploadedAttachement(final String subject, final String message, final String toEmailAddress, final MultipartFile[] attachments){
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("diversinfo8086@gmail.com");
			helper.setTo(toEmailAddress);
			helper.setSubject(subject);
			helper.setText(message);
			// add the file attachment
			for (MultipartFile multiFile : attachments) {
				File convFile = new File(multiFile.getOriginalFilename());
				multiFile.transferTo(convFile);
				FileSystemResource fr = new FileSystemResource(convFile);
				helper.addAttachment(convFile.getName(), fr);
			}
			javaMailSender.send(mimeMessage);
			System.out.println("Email sending with multiple attachments complete.");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}

package com.ays.spring.email.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.ays.spring.email.dao.SendingEmailService;
import com.ays.spring.email.entities.MailModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class SendingEmailServiceImpl implements SendingEmailService {

	private static Logger log = LoggerFactory.getLogger(SendingEmailServiceImpl.class);
//@Autowired :Bir bean içerisindeki değerleri başka beanin içerisine enjekte edebilir.Değerlerini koruyarak kullanabiliriz.
//@Autowired :Bir değişken, setter ya da yapılandırıcı metot üzerinde kullanılabilir.
	@Autowired
	private JavaMailSender epostaGonderen;

	@Autowired
	@Qualifier("emailconfiguration")
	private Configuration emailConfig;

	@Override
	public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {

		System.out.println("...Mail işlemleri...");
		
		MailModel mail_model = new MailModel();
		mail_model.setFrom("leylanur19h@gmail.com");
		mail_model.setTo("aylacanayla1@gmail.com");
		mail_model.setSubject("Mail detayları");
		
		System.out.println("......");

		Map<String, String> epostaislem = new HashMap<String, String>();
		epostaislem.put("name", "Leyla");
		epostaislem.put("location", "Antalya");
		epostaislem.put("signature", "ayl.yml");
		mail_model.setModel(epostaislem);

		log.info("Email Send: " + mail_model.getTo());

		MimeMessage mesaj = epostaGonderen.createMimeMessage();
		MimeMessageHelper mimemesaj = new MimeMessageHelper(mesaj,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		mimemesaj.addInline("ayl.png", new ClassPathResource("classpath:/ayl.png"));

		Template template = emailConfig.getTemplate("email.ftlh");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail_model.getModel());

		mimemesaj.setTo("aylacanayla1@gmail.com");
		mimemesaj.setText(html, true);
		mimemesaj.setSubject("..Bilgileriniz..");
		mimemesaj.setFrom("leylanur19h@gmail.com");

		epostaGonderen.send(mesaj);

	}
}

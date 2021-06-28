package com.ays.spring.email.api;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ays.spring.email.dao.SendingEmailService;
import com.ays.spring.email.entities.MailModel;

import freemarker.template.TemplateException;

//@RestController :Controller ve ResponseBody’nin birleşimi(RestController (Spring 4.0))
@RestController
public class SendEmailController {
	
//@Autowired :Bir bean içerisindeki değerleri başka beanin içerisine enjekte edebilir.Değerlerini koruyarak kullanabiliriz.
//@Autowired :Bir değişken, setter ya da yapılandırıcı metot üzerinde kullanılabilir.
	
	@Autowired
	private SendingEmailService emailServis;
	
//@RequestMapping :Beanimizdeki metodu yada tüm beani map etmeyi sağlar.
//@RequestMapping :HTTP isteğine karşılık gelen ilgili metod,RequestMapping tarafından çalıştırılması sağlanmaktadır.	
//@ResponseBody :Controller anatosyonu ile oluşturulmuş bir Controller’dan Restful response alabilmek için ilgili fonksiyona ResponseBody annotasyonu eklesek yeterlidir. 
	
	@RequestMapping(value = "/mailgonder", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> restPostLoanRequest(MailModel mailModel) {
		System.out.print("...Mail detay...");
		try {
			emailServis.sendEmail(mailModel);
			return ResponseEntity.ok().body(mailModel.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(e.getMessage());
		} catch (TemplateException e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(e.getMessage());
		}

	}
}

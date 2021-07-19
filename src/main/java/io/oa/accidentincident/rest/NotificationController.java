package io.oa.accidentincident.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.oa.accidentincident.form.NotificationForm;
@CrossOrigin
@RestController
public class NotificationController {
	   @Autowired
	    private JavaMailSender emailSender;
		@PreAuthorize("@authorizationSE.can('afficher', 'notificationaccident') or @authorizationSE.can('afficher', 'gestionmateriel')or @authorizationSE.can('afficher', 'gestionaccidentinform')")
	   @PostMapping("/notificationemail" )
	    public void sendSimpleMessage(@RequestBody NotificationForm notification) {
	
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("applicationtranstu@gmail.com");
	        message.setTo("marwenyounes1@gmail.com"); 
	        message.setSubject(notification.getSubject()); 
	        message.setText(notification.getText());
	        emailSender.send(message);
	        
	    }
	   private final static String ACCOUNT_SID = "AC7c33bdb79a7845be1322e3f556b3fa35";
	   private final static String AUTH_ID = "974d31862b59eaa517a0e781cfd9f50d";

	   static {
	      Twilio.init(ACCOUNT_SID, AUTH_ID);
	   }
	   @PostMapping("/notificationsms" )
	    public void sendSimpleSMS() {
	
		  
		   Message message = Message.creator(
				    new PhoneNumber("+21620702369"),
				    new PhoneNumber("+21631358071"),
				    "Sample Twilio SMS using Java")
				.create();
	        
	    }
}

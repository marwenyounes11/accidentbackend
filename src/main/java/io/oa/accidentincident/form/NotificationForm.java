package io.oa.accidentincident.form;

public class NotificationForm {
	String subject;
	String text;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public NotificationForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotificationForm(String subject, String text) {
		super();
		this.subject = subject;
		this.text = text;
	}
	
	
}

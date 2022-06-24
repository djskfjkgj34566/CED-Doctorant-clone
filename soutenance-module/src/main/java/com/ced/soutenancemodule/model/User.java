package com.ced.soutenancemodule.model;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String firstName;
	private String sujet;
	private String emailAddress;
	private String emailAddress2;
	private String emailAddress3;
	//private List<String> emailAddress;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	/*public List<String> getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(List<String> emailAddress) {
		this.emailAddress = emailAddress;
	}*/

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress2() {
		return emailAddress2;
	}

	public void setEmailAddress2(String emailAddress2) {
		this.emailAddress2 = emailAddress2;
	}

	public String getEmailAddress3() {
		return emailAddress3;
	}

	public void setEmailAddress3(String emailAddress3) {
		this.emailAddress3 = emailAddress3;
	}
}

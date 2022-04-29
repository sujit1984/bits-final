package com.test.bitswilp.microservices.contactsapi.Contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Contacts {
	
	@Id
	@GeneratedValue
	private Integer contactId; 
	
	@Size(min = 2, message ="First Name should have at least 2 characters")
	private String firstName;
	
	@Size(min = 2, max = 20, message="Last Name should have at least 2 characters")
	@Size(max = 20, message="Last Name should not be more than 20 characters")
	private String lastName;
	
	@Size(min = 8, max = 10)
	private String phone;
	
	@Size(min=3, max = 40)
	@Email(regexp = ".+@.+\\..+", message ="Email is in the wrong format!!!")
	private String email;
	

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User [contactId=%s, firstName=%s, lastName=%s, phone=%s, email=%s]", contactId, firstName,
				lastName, phone, email);
	}

	public Contacts(Integer contactId, String firstName, String lastName, String phone, String email) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public Contacts() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

package com.test.bitswilp.microservices.contactsapi.Contacts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ContactsDaoService {
	
	private static List<Contacts> contacts = new ArrayList<>();
	
	private static int contactsCount = 113;
	
	static {
		contacts.add(new Contacts(111,"Sujit","Sreenath","8129297878","sujit_s@invalidemail.com"));
		contacts.add(new Contacts(112,"Adam","John","8718212112","adam.john@invalidemail.com"));
		contacts.add(new Contacts(113,"Ruth","Calva","9823239328","ruth.calva@invalidemail.com"));
	}
		
	public List<Contacts> findall(){
		
		return contacts;
	}
	
	public Contacts save(Contacts contact) {
		
		if(contact.getContactId()==null) {
			contact.setContactId(++contactsCount);
			}
		contacts.add(contact);
		return contact;
		
	}
	
	public Contacts findOne(int id){
		
		for(Contacts contact:contacts) {
			if(contact.getContactId()==id) {
				return contact;
			}
			
		}
		return null;
	}
	
	public Contacts deleteById(int id){
		
		Iterator<Contacts> iterator = contacts.iterator();
		
		while(iterator.hasNext()) {
			Contacts contact = iterator.next();
			if(contact.getContactId() == id)
			{
				iterator.remove();
				return contact;
			
			}
			
		}
		return null;
	}
}

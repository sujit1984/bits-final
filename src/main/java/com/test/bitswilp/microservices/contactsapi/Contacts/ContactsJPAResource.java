package com.test.bitswilp.microservices.contactsapi.Contacts;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ContactsJPAResource {
	
	@Autowired
	private ContactsDaoService service;
	
	@Autowired
	private ContactsRepository contactRepository;
	

	//GET /contacts
	//retrieveAllContacts(){}
	@GetMapping("/jpa/contacts")
	public List<Contacts> retrieveAllContacts(){
		
		return contactRepository.findAll();
	}
	
	
	//GET /contacts/{id}
	//retrieveContact(int id){}
	@GetMapping("/jpa/contacts/{id}")
	public EntityModel<Contacts> retrieveContact(@PathVariable int id){
		
		 Optional<Contacts> contact = contactRepository.findById(id);
			 if(!contact.isPresent())
		 {
			 throw new ContactNotFoundException("id-"+ id);
		 }
			 
			
		 
		EntityModel<Contacts> model = EntityModel.of(contact.get());
		
		WebMvcLinkBuilder linkToContacts = linkTo(methodOn(this.getClass()).retrieveAllContacts());
			 model.add(linkToContacts.withRel("all-contacts"));
		 return model;
	}
	
	
	@DeleteMapping("/jpa/contacts/{id}")
	public void deleteContact(@PathVariable int id){
		
		 contactRepository.deleteById(id);
	}
	
	
	//CREATED 
	// input -- details of the contact
	//output -- CREATED status & created URI - resource 
	@PostMapping("/jpa/contacts")
	public ResponseEntity<Object> createContact(@Valid @RequestBody Contacts contact) {
			
		Contacts savedContact = contactRepository.save(contact);
		
		//CREATED Status
		//
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedContact.getContactId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}

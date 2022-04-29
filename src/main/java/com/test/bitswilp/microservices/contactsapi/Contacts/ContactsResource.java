package com.test.bitswilp.microservices.contactsapi.Contacts;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ContactsResource {
	
	@Autowired
	private ContactsDaoService service;

	//GET /users
	//retrieveAllUsers(){}
	@GetMapping("/contacts")
	public List<Contacts> retrieveAllContacts(){
		
		return service.findall();
	}
	
	
	//GET /users/{id}
	//retrieveContact(int id){}
	@GetMapping("/contacts/{id}")
	public EntityModel<Contacts> retrieveContact(@PathVariable int id){
		
		 Contacts contact = service.findOne(id);
			 if(contact==null)
		 {
			 throw new ContactNotFoundException("id-"+ id);
		 }
			 
			 
		 
		EntityModel<Contacts> model = EntityModel.of(contact);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllContacts());
			 model.add(linkToUsers.withRel("all-contacts"));
		 return model;
	}
	
	
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable int id){
		
		 Contacts contact = service.deleteById(id);
			 if(contact==null)
		 {
			 throw new ContactNotFoundException("id-"+ id);
		 }
		 
		
	}
	
	
	//CREATED 
	// input -- details of the user
	//output -- CREATED status & created URI - resource 
	@PostMapping("/contacts")
	public ResponseEntity<Object> createContact(@Valid @RequestBody Contacts contact) {
			
		Contacts savedContact = service.save(contact);
		
		//CREATED Status
		//
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedContact.getContactId()).toUri();
		return ResponseEntity.created(location).build();
	}

}

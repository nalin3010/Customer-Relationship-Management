package com.project.MyCRMsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MyCRMsystem.entity.Contacts;
import com.project.MyCRMsystem.repository.ContactsRepository;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {
	
	
	// logging
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	@Autowired
	private ContactsRepository contactRepository;
	
	@GetMapping
	 private List<Contacts> getAllContacts()
	    {
		logger.info("Fetching all contacts");
		List<Contacts> contacts = contactRepository.findAll();
		logger.info("Total contacts fetched: {}", contacts.size());
	        return contacts;
	    }
	 
	@GetMapping("/{id}")
	 private ResponseEntity<Optional<Contacts>> getContactById(@PathVariable Integer id) {
		logger.info("Fetching contact with ID: {}", id);
		 Contacts contact = contactRepository.findById(id)
	                .orElseThrow(() -> {
	                	logger.warn("Contact not found with ID: {}", id);
	                	return new NoSuchElementException("Contact not found");
	                });
		 logger.info("Contact found: {}", contact);

	        return ResponseEntity.ok(Optional.ofNullable(contact));
	 }
	 
	@PostMapping("/create")
	 private Contacts createContact(@RequestBody Contacts contact)
	    {
			logger.info("Creating new contact: {}", contact);
			Contacts savedContact = contactRepository.save(contact);
			logger.info("Contact created with ID: {}", savedContact.getId());
	        return savedContact;
	    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Contacts> updateContact(@PathVariable Integer id, @RequestBody Contacts contactDetails)
	    {
		 	logger.info("Updating contact with ID: {}", id);
	        Contacts contact = contactRepository.findById(id)
	        		.orElseThrow(() -> {
	                    logger.warn("Contact not found for update with ID: {}", id);
	                    return new NoSuchElementException("Contact not found");
	                });

	        contact.setFirst_name(contactDetails.getFirst_name());
	        contact.setLast_name(contactDetails.getLast_name());
	        contact.setEmail(contactDetails.getEmail());
	        contact.setPhone(contactDetails.getPhone());
	        contact.setCompany(contactDetails.getCompany());

	        Contacts updatedContacts = contactRepository.save(contact);
	        logger.info("Contact updated with ID: {}", updatedContacts.getId());
	        
	        return ResponseEntity.ok(updatedContacts);
	    }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Map<String, Boolean>> deleteContact(@PathVariable Integer id)
	    {
		 	logger.info("Deleting contact with ID: {}", id);
	        Contacts contact = contactRepository.findById(id)
	        		.orElseThrow(() -> {
	                    logger.warn("Attempt to delete non-existing contact with ID: {}", id);
	                    return new NoSuchElementException("Contact not found");
	                });
	        contactRepository.delete(contact);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        logger.info("Contact with ID: {} deleted successfully", id);
	        return ResponseEntity.ok(response);
	    }
	 
	 
}

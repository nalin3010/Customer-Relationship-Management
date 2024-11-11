package com.project.MyCRMsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

import com.project.MyCRMsystem.entity.Tickets;
import com.project.MyCRMsystem.repository.TicketsRepository;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@GetMapping
	public List<Tickets> getAllTickets() {
		logger.info("Fetching all tickets");
		List<Tickets> tickets = ticketsRepository.findAll();
        logger.info("Total tickets fetched: {}", tickets.size());
        return tickets;
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable Integer id) {
		logger.info("Fetching ticket with ID: {}", id);
        Tickets ticket = ticketsRepository.findById(id)
				    		.orElseThrow(() -> {
				                logger.error("Ticket not found with id: {}", id);
				                return new NoSuchElementException("Ticket not found with id: " + id);
				            });
        logger.info("Ticket found: {}", ticket);
        return ResponseEntity.ok(ticket);
    }
	
	
	@PostMapping
    public Tickets createTicket(@RequestBody Tickets ticket) {
		 logger.info("Creating a new ticket: {}", ticket);
		 Tickets savedTicket = ticketsRepository.save(ticket);
	        logger.info("Ticket created successfully with ID: {}", savedTicket.getId());
	        return savedTicket;
    }
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Tickets> updateTicket(@PathVariable Integer id, @RequestBody Tickets ticketDetails) {
		logger.info("Updating ticket with ID: {}", id);
        Tickets ticket = ticketsRepository.findById(id)
        		.orElseThrow(() -> {
                    logger.error("Ticket not found with id: {}", id);
                    return new NoSuchElementException("Ticket not found with id: " + id);
                });

        ticket.setStatus(ticketDetails.getStatus());
        ticket.setIssue_description(ticketDetails.getIssue_description());

        Tickets updatedTicket = ticketsRepository.save(ticket);
        logger.info("Ticket updated successfully: {}", updatedTicket);
        return ResponseEntity.ok(updatedTicket);
    }
	
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTicket(@PathVariable Integer id) {
		logger.info("Deleting ticket with ID: {}", id);
		Tickets ticket = ticketsRepository.findById(id)
							.orElseThrow(() -> {
			                    logger.error("Ticket not found with id: {}", id);
			                    return new NoSuchElementException("Ticket not found with id: " + id);
			                });

		ticketsRepository.delete(ticket);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logger.info("Ticket with ID: {} deleted successfully", id);
        return ResponseEntity.ok(response);
    }
	
	
	// Custom endpoint to get tickets by status
    @GetMapping("/status/{status}")
    public List<Tickets> getTicketsByStatus(@PathVariable String status) {
    	logger.info("Fetching tickets with status: {}", status);
    	 List<Tickets> ticketsByStatus = ticketsRepository.findByStatus(status);
         logger.info("Total tickets fetched with status '{}': {}", status, ticketsByStatus.size());
         return ticketsByStatus;
    }

	
	
	
}

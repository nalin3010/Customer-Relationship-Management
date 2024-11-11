package com.project.MyCRMsystem.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tickets")
public class Tickets {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "ticket_number")
	    private String ticket_number;

	    @Column(name = "issue_description")
	    private String issue_description;

	    @Column(name = "status")
	    private String status;

	    @ManyToOne
	    @JoinColumn(name = "contact_id")
	    private Contacts contact;
	    
	    @CreationTimestamp
	    @Column(name = "created_at")
	    private LocalDate created_at;

		public Tickets(int id, String ticket_number, String issue_description, String status, Contacts contact,
				LocalDate created_at) {
			super();
			this.id = id;
			this.ticket_number = ticket_number;
			this.issue_description = issue_description;
			this.status = status;
			this.contact = contact;
			this.created_at = created_at;
		}

		public Tickets() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTicket_number() {
			return ticket_number;
		}

		public void setTicket_number(String ticket_number) {
			this.ticket_number = ticket_number;
		}

		public String getIssue_description() {
			return issue_description;
		}

		public void setIssue_description(String issue_description) {
			this.issue_description = issue_description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Contacts getContact() {
			return contact;
		}

		public void setContact(Contacts contact) {
			this.contact = contact;
		}

		public LocalDate getCreated_at() {
			return created_at;
		}

		public void setCreated_at(LocalDate created_at) {
			this.created_at = created_at;
		}
}

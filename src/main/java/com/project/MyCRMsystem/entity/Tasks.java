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
@Table(name = "tasks")
public class Tasks {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "title")
	    private String title;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "status")
	    private String status;

	    @Column(name = "due_date")
	    private LocalDate due_date;

	    @ManyToOne
	    @JoinColumn(name = "contact_id")
	    private Contacts contact;
	    
	    @CreationTimestamp
	    @Column(name = "created_at")
	    private LocalDate created_at;

		public Tasks(int id, String title, String description, String status, LocalDate due_date, Contacts contact,
				LocalDate created_at) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.status = status;
			this.due_date = due_date;
			this.contact = contact;
			this.created_at = created_at;
		}

		public Tasks() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public LocalDate getDue_date() {
			return due_date;
		}

		public void setDue_date(LocalDate due_date) {
			this.due_date = due_date;
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

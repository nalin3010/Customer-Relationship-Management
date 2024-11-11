package com.project.MyCRMsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_interaction_logs")
public class CustomerInteractionLog {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "interaction_type")
	    private String interactionType; // e.g., call, email, meeting

	    @Column(name = "interaction_date")
	    private LocalDateTime interactionDate;

	    @Column(name = "notes")
	    private String notes;

	    @ManyToOne
	    @JoinColumn(name = "sales_pipeline_id")
	    private SalesPipeline salesPipeline; // Link to the associated sales activity

		public CustomerInteractionLog(Long id, String interactionType, LocalDateTime interactionDate, String notes,
				SalesPipeline salesPipeline) {
			super();
			this.id = id;
			this.interactionType = interactionType;
			this.interactionDate = interactionDate;
			this.notes = notes;
			this.salesPipeline = salesPipeline;
		}

		public CustomerInteractionLog() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getInteractionType() {
			return interactionType;
		}

		public void setInteractionType(String interactionType) {
			this.interactionType = interactionType;
		}

		public LocalDateTime getInteractionDate() {
			return interactionDate;
		}

		public void setInteractionDate(LocalDateTime interactionDate) {
			this.interactionDate = interactionDate;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public SalesPipeline getSalesPipeline() {
			return salesPipeline;
		}

		public void setSalesPipeline(SalesPipeline salesPipeline) {
			this.salesPipeline = salesPipeline;
		}
}

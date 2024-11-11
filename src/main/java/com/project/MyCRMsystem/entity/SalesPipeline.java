package com.project.MyCRMsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_pipeline")
public class SalesPipeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "stage")
    private SalesStage stage;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "close_date")
    private LocalDate close_date;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contacts contact;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate created_at;
    
    @Column(name = "probability_of_closing")
    private double probabilityOfClosing;

	public SalesPipeline(int id, SalesStage stage, BigDecimal amount, LocalDate close_date, Contacts contact,
			LocalDate created_at, double probabilityOfClosing) {
		super();
		this.id = id;
		this.stage = stage;
		this.amount = amount;
		this.close_date = close_date;
		this.contact = contact;
		this.created_at = created_at;
		this.probabilityOfClosing = probabilityOfClosing;
	}

	public SalesPipeline() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SalesStage getStage() {
		return stage;
	}

	public void setStage(SalesStage stage) {
		this.stage = stage;
		// Set probability of closing based on the sales stage
        switch (stage) {
            case LEAD:
                this.probabilityOfClosing = 0.25; // 25%
                break;
            case CONTACT_MADE:
                this.probabilityOfClosing = 0.50; // 50%
                break;
            case DEMO_SCHEDULED:
                this.probabilityOfClosing = 0.75; // 75%
                break;
            case CLOSED:
                this.probabilityOfClosing = 1.00; // 100%
                this.close_date = LocalDate.now();
                break;
            default:
                this.probabilityOfClosing = 0.0; // Default case
        }
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getClose_date() {
		return close_date;
	}

	public void setClose_date(LocalDate close_date) {
		this.close_date = close_date;
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

	public double getProbabilityOfClosing() {
		return probabilityOfClosing;
	}

	public void setProbabilityOfClosing(double probabilityOfClosing) {
		this.probabilityOfClosing = probabilityOfClosing;
	}

}

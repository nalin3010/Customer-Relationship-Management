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

import com.project.MyCRMsystem.entity.SalesPipeline;
import com.project.MyCRMsystem.entity.SalesStage;
import com.project.MyCRMsystem.repository.SalesPipelineRepository;

@RestController
@RequestMapping("/api/sales")
public class SalesPipelineController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SalesPipelineRepository salesPipelineRepository;
	
	
	@GetMapping
	public List<SalesPipeline> getAllSales() {
		logger.info("Fetching all sales records");
		List<SalesPipeline> sales = salesPipelineRepository.findAll();
        logger.info("Total sales records fetched: {}", sales.size());
        return sales;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SalesPipeline> getSalesById(@PathVariable int id) {
		 logger.info("Fetching sale with ID: {}", id);
		SalesPipeline sale = salesPipelineRepository.findById(id)
				.orElseThrow(() -> {
	                logger.error("Sale not found with id: {}", id);
	                return new NoSuchElementException("Sale not found with id: " + id);
	            });
		logger.info("Sale found: {}", sale);
		return ResponseEntity.ok(sale);
	}
	
	
	@PostMapping
	public SalesPipeline createSale(@RequestBody SalesPipeline sale) {
		logger.info("Creating a new sale: {}", sale);
		SalesPipeline savedSale = salesPipelineRepository.save(sale);
        logger.info("Sale created successfully with ID: {}", savedSale.getId());
        return savedSale;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<SalesPipeline> updateSale(@PathVariable int id, @RequestBody SalesPipeline saleDetails) {
		logger.info("Updating sale with ID: {}", id);
		SalesPipeline sale = salesPipelineRepository.findById(id)
				.orElseThrow(() -> {
	                logger.error("Sale not found with id: {}", id);
	                return new NoSuchElementException("Sale not found with id: " + id);
	            });
		sale.setStage(saleDetails.getStage());
        sale.setAmount(saleDetails.getAmount());
        sale.setClose_date(saleDetails.getClose_date());
        sale.setProbabilityOfClosing(saleDetails.getProbabilityOfClosing());
        
        SalesPipeline updatedSale = salesPipelineRepository.save(sale);
        
        logger.info("Sale updated successfully: {}", updatedSale);
        
        return ResponseEntity.ok(updatedSale);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSale(@PathVariable int id) {
		logger.info("Deleting sale with ID: {}", id);
		SalesPipeline sale = salesPipelineRepository.findById(id)
				.orElseThrow(() -> {
	                logger.error("Sale not found with id: {}", id);
	                return new NoSuchElementException("Sale not found with id: " + id);
	            });

        salesPipelineRepository.delete(sale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logger.info("Sale with ID: {} deleted successfully", id);
        return ResponseEntity.ok(response);
	}
	
	 // Custom endpoint to get sales by stage
    @GetMapping("/stage/{stage}")
    public List<SalesPipeline> getSalesByStage(@PathVariable SalesStage stage) {
    	logger.info("Fetching sales by stage: {}", stage);
    	 List<SalesPipeline> salesByStage = salesPipelineRepository.findByStage(stage);
         logger.info("Total sales records found for stage {}: {}", stage, salesByStage.size());
         return salesByStage;
    }
	
	
}

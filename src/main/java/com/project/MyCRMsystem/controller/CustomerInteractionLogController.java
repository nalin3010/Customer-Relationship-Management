package com.project.MyCRMsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MyCRMsystem.entity.CustomerInteractionLog;
import com.project.MyCRMsystem.service.CustomerInteractionLogService;

@RestController
@RequestMapping("/api/customer-interaction-logs")
public class CustomerInteractionLogController {
	
	 @Autowired
	    private CustomerInteractionLogService logService;

	    @PostMapping
	    public ResponseEntity<CustomerInteractionLog> createLog(@Validated @RequestBody CustomerInteractionLog log) {
	        CustomerInteractionLog savedLog = logService.saveLog(log);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
	    }

	    @GetMapping
	    public ResponseEntity<List<CustomerInteractionLog>> getAllLogs() {
	        List<CustomerInteractionLog> logs = logService.getAllLogs();
	        return ResponseEntity.ok(logs);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CustomerInteractionLog> getLogById(@PathVariable Long id) {
	        CustomerInteractionLog log = logService.getLogById(id);
	        return log != null ? ResponseEntity.ok(log) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
	        logService.deleteLog(id);
	        return ResponseEntity.noContent().build();
	    }
	
}

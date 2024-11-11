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

import com.project.MyCRMsystem.entity.Tasks;
import com.project.MyCRMsystem.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	 @Autowired
	    private TaskRepository taskRepository;
	 

	    @GetMapping
	    public List<Tasks> getAllTasks() {
	    	logger.info("Fetching all tasks");
	    	 List<Tasks> tasks = taskRepository.findAll();
	         logger.info("Total tasks fetched: {}", tasks.size());
	         return tasks;
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Tasks> getTaskById(@PathVariable Integer id) {
	    	logger.info("Fetching task with ID: {}", id);
	        Tasks task = taskRepository.findById(id)
	        		.orElseThrow(() -> {
	                    logger.error("Task not found with id: {}", id);
	                    return new NoSuchElementException("Task not found with id: " + id);
	                });
	        logger.info("Task found: {}", task);
	        return ResponseEntity.ok(task);
	    }
	    
	    @PostMapping
	    public Tasks createTask(@RequestBody Tasks task) {
	    	logger.info("Creating a new task: {}", task);
	    	Tasks savedTask = taskRepository.save(task);
	        logger.info("Task created successfully with ID: {}", savedTask.getId());
	        return savedTask;
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Tasks> updateTask(@PathVariable Integer id, @RequestBody Tasks taskDetails) {
	    	 logger.info("Updating task with ID: {}", id);
	        Tasks task = taskRepository.findById(id)
			        		.orElseThrow(() -> {
			                    logger.error("Task not found with id: {}", id);
			                    return new NoSuchElementException("Task not found with id: " + id);
			                });

	        task.setTitle(taskDetails.getTitle());
	        task.setDescription(taskDetails.getDescription());
	        task.setStatus(taskDetails.getStatus());
	        task.setDue_date(taskDetails.getDue_date());

	        Tasks updatedTask = taskRepository.save(task);
	        logger.info("Task updated successfully: {}", updatedTask);
	        return ResponseEntity.ok(updatedTask);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Integer id) {
	    	logger.info("Deleting task with ID: {}", id);
	        Tasks task = taskRepository.findById(id)
			        		.orElseThrow(() -> {
			                    logger.error("Task not found with id: {}", id);
			                    return new NoSuchElementException("Task not found with id: " + id);
			                });

	        taskRepository.delete(task);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        logger.info("Task with ID: {} deleted successfully", id);
	        return ResponseEntity.ok(response);
	    }
	    
	    // Custom endpoint to get tasks by status
	    @GetMapping("/status/{status}")
	    public List<Tasks> getTasksByStatus(@PathVariable String status) {
	    	 logger.info("Fetching tasks with status: {}", status);
	    	 List<Tasks> tasksByStatus = taskRepository.findByStatus(status);
	         logger.info("Total tasks fetched with status '{}': {}", status, tasksByStatus.size());
	         return tasksByStatus;
	    }
	
}

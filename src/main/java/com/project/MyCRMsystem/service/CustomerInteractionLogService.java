package com.project.MyCRMsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MyCRMsystem.entity.CustomerInteractionLog;
import com.project.MyCRMsystem.repository.CustomerInteractionLogRepository;

@Service
public class CustomerInteractionLogService {
	@Autowired
    private CustomerInteractionLogRepository logRepository;

    public CustomerInteractionLog saveLog(CustomerInteractionLog log) {
        return logRepository.save(log);
    }

    public List<CustomerInteractionLog> getAllLogs() {
        return logRepository.findAll();
    }

    public CustomerInteractionLog getLogById(Long id) {
        return logRepository.findById(id).orElse(null);
    }

    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }
}

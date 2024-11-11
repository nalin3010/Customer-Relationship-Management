package com.project.MyCRMsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MyCRMsystem.entity.CustomerInteractionLog;

public interface CustomerInteractionLogRepository extends JpaRepository<CustomerInteractionLog, Long> {
	
}

package com.project.MyCRMsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MyCRMsystem.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {
	List<Tasks> findByStatus(String status);
}

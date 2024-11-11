package com.project.MyCRMsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MyCRMsystem.entity.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Integer> {
	List<Tickets> findByStatus(String status);
}

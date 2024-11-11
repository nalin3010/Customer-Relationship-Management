package com.project.MyCRMsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MyCRMsystem.entity.SalesPipeline;
import com.project.MyCRMsystem.entity.SalesStage;

public interface SalesPipelineRepository extends JpaRepository<SalesPipeline, Integer> {
	public List<SalesPipeline> findByStage(SalesStage stage);
}

package com.project.MyCRMsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.MyCRMsystem.entity.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
}

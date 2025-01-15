package com.taggle.taggleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taggle.taggleapi.model.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
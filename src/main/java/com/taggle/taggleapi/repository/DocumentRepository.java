package com.taggle.taggleapi.repository;

import com.taggle.taggleapi.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
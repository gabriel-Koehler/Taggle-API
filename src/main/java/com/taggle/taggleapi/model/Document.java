package com.taggle.taggleapi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String type;
    private String title;
} 

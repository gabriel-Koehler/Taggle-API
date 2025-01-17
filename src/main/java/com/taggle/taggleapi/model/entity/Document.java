package com.taggle.taggleapi.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.taggle.taggleapi.model.DTO.ConvertToResponse;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id")
public abstract class Document implements ConvertToResponse<Document> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String title;
    private LocalDateTime atCreate= LocalDateTime.now();
    @Nullable
    private LocalDateTime atLastAlteration;
    private Boolean isActive=true;
    @Nullable
    @ManyToOne
    private Folder parentFolder;
    @ManyToOne
    private UserTaggle owner;
} 

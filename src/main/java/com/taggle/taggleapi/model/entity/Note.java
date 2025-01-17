package com.taggle.taggleapi.model.entity;

import com.taggle.taggleapi.model.DTO.ConvertToResponse;

import jakarta.persistence.Entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Note extends Document{
    
    private String content;

    @Override
    public Note toDTO() {
        Note dto = new Note();
        dto.setId(getId());
        dto.setAtCreate(getAtCreate());
        dto.setContent(this.content);
        dto.setOwner(getOwner().toDTO());
        dto.setParentFolder(getParentFolder().toDTO());
        return dto;
    }


}

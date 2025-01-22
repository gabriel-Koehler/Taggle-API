package com.taggle.taggleapi.model.entity;

import java.util.List;

import com.taggle.taggleapi.model.DTO.ConvertToResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Folder extends Document {
    
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Document> content;

    @Override
    public Folder toDTO() {
        Folder dto = new Folder();
        dto.setId(getId());
        dto.setTitle(getTitle());
        dto.setAtCreate(getAtCreate());
        dto.setAtLastAlteration(getAtLastAlteration());
        dto.setIsActive(getIsActive());
        dto.setOwner(getOwner()!=null?getOwner().toDTO():null);
        dto.setParentFolder(getParentFolder()!=null?getParentFolder().toDTO():null);
        return dto;
    }

}

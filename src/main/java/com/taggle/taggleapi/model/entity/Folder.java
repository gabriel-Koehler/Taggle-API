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
public class Folder extends Document  implements ConvertToResponse<Folder>{
    
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Document> content;

    @Override
    public Folder toDTO() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDTO'");
    }

}

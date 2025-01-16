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
public class Note extends Document implements ConvertToResponse<UserTaggle>{
    
    private String content;

    @Override
    public UserTaggle toDTO() {
        UserTaggle dtoTaggle = new UserTaggle();
        dtoTaggle.setId(this.getId());
        return dtoTaggle;
    }

}

package com.taggle.taggleapi.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.taggle.taggleapi.model.DTO.ConvertToResponse;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTaggle implements ConvertToResponse<UserTaggle> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id")
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "owner")
    @Cascade(CascadeType.REMOVE)
    private List<Document> documents;

    private LocalDateTime atCreate= LocalDateTime.now();
    @Nullable
    private LocalDateTime atLastAlteration;
    private Boolean isActive=true;
    @Override
    public UserTaggle toDTO() {
        UserTaggle dto = new UserTaggle();
        dto.setId(this.id);
        dto.setUsername(this.username);
        dto.setIsActive(this.isActive);
        dto.setAtLastAlteration(this.atLastAlteration);
        dto.setAtCreate(this.atCreate);
        return dto;
    }
}

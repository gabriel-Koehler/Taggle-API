package com.taggle.taggleapi.model.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.taggle.taggleapi.model.DTO.ConvertToResponse;

import jakarta.annotation.Nullable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class UserTaggle implements ConvertToResponse<UserTaggle>,UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "id")
    private Long id;
    private String username;
    @Nullable
    private String email;
    private String password;
    @OneToMany(mappedBy = "owner")
    @Cascade(CascadeType.REMOVE)
    private List<Document> documents;

    private LocalDateTime atCreate= LocalDateTime.now();
    @Nullable
    private LocalDateTime atLastAlteration;
    private Boolean isActive=true;
    @Enumerated(EnumType.STRING)
    private Roles role;
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

    public Boolean isLoginCorrect(String password,PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(password,this.password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return isActive;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return isActive;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return isActive;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return isActive;
    }
}

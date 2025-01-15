package com.taggle.taggleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taggle.taggleapi.model.entity.UserTaggle;

@Repository
public interface UserTaggleRepository extends JpaRepository<UserTaggle,Long> {
    
}

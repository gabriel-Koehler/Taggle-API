package com.taggle.taggleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taggle.taggleapi.model.entity.UserTaggle;
import java.util.Optional;


@Repository
public interface UserTaggleRepository extends JpaRepository<UserTaggle,Long> {
    Optional<UserTaggle> findByUsername(String username);
    Optional<UserTaggle> findByEmail(String email);
    Optional<UserTaggle> findByUsernameOrEmail(String username, String email);
}

package com.taggle.taggleapi.model.DTO.UserTaggle;

import java.nio.file.attribute.UserPrincipal;

import com.taggle.taggleapi.model.entity.Roles;
import com.taggle.taggleapi.model.entity.UserTaggle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTagglePrincipal {
    private final Long id;
    private final String username;
    private final String password;
    private final Roles role;
    private final boolean isActive;

    public static UserTagglePrincipal fromUser(UserTaggle user) {
        return new UserTagglePrincipal(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRole(),
            user.getIsActive()
        );
    }
}

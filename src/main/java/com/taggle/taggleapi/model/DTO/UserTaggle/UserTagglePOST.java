package com.taggle.taggleapi.model.DTO.UserTaggle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTagglePOST {
    private String username;
    private String password;
}

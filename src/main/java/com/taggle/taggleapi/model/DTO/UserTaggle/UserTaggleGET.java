package com.taggle.taggleapi.model.DTO.UserTaggle;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTaggleGET {
    private Long id;
    private String username;
    private LocalDateTime atCreate;
    private LocalDateTime atLastAlteration;
    private Boolean isActive;
}

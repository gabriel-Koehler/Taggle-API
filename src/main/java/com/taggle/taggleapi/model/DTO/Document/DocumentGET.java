package com.taggle.taggleapi.model.DTO.Document;

import java.time.LocalDateTime;
import java.util.List;

import com.taggle.taggleapi.model.entity.Document;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class DocumentGET {
    private Long id;
    private String type;
    private String title;
    private LocalDateTime atCreate;
    private LocalDateTime atLastAlteration;
    private Boolean isActive=true;
}

package com.taggle.taggleapi.model.DTO.Document;

import java.time.LocalDateTime;
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
    private Long parentFolder;
    private Boolean isActive=true;
}

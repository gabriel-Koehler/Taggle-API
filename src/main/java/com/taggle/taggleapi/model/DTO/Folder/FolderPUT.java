package com.taggle.taggleapi.model.DTO.Folder;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FolderPUT {
    private Long id;
    private String title;
    private LocalDateTime atLastAlteration=LocalDateTime.now();
}

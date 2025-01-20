package com.taggle.taggleapi.model.DTO.Note;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotePUT {
    private Long id;
    private String title;
    private LocalDateTime atLastAlteration=LocalDateTime.now();
}

package com.taggle.taggleapi.model.DTO.Document;

import com.taggle.taggleapi.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDocGET extends DocumentGET{
    private String content;

    public NoteDocGET(Note note) {
        super(
            note.getId(),
            "Note",
            note.getTitle(),
            note.getAtCreate(),
            note.getAtLastAlteration(),
            note.getParentFolder().getId(),
            note.getIsActive());
        this.content = note.getContent();
    }
}

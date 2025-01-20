package com.taggle.taggleapi.model.DTO.Document;

import java.util.List;

import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FolderDocGET extends DocumentGET{
    private List<DocumentGET> content;

    public FolderDocGET(Folder doc) {        
        super(doc.getId(),doc.getTitle(),doc.getType(),doc.getAtLastAlteration(),doc.getAtCreate(),doc.getIsActive());
        this.content =doc.getContent().stream().map((e)->{
            if(e instanceof Folder){
                Folder folder = (Folder)e;
                return new FolderDocGET(folder);
            }else{
                Note note = (Note) e;
                return new NoteDocGET(note);
            }
        } ).toList();
    }
}

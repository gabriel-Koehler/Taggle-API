package com.taggle.taggleapi.model.DTO.Folder;

import java.util.List;

import com.taggle.taggleapi.model.DTO.Document.DocumentGET;
import com.taggle.taggleapi.model.entity.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
public class FolderGet {
    private Long id;
    private String title;
    private List<DocumentGET> content;

    public FolderGet(Long id,String title,List<Document> content) {
        this.id = id;
        this.title = title;
        this.content = content.stream().map((e)->e.toDTO()).toList();
    }
}

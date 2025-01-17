package com.taggle.taggleapi.service;

import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.repository.DocumentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentService {
    private DocumentRepository repository;

    public Folder saveFolder(Folder object) {
        return repository.save(object);
    }
    public Note saveNote(Note object) {
        return repository.save(object);
    }


    public Document getDocumentPerOwner(long id) {
        return null;
    }
}

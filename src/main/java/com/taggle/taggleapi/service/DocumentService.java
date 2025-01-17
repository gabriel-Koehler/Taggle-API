package com.taggle.taggleapi.service;

import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.repository.FolderRepository;
import com.taggle.taggleapi.repository.NoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentService {
    private FolderRepository folderRepository;
    private NoteRepository noteRepository;

    public Folder saveFolder(Folder object) {
        return folderRepository.save(object);
    }
    public Note saveNote(Note object) {
        return noteRepository.save(object);
    }


    public Document getDocumentPerOwner(long id) {
        return null;
    }
}

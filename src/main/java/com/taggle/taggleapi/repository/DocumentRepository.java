package com.taggle.taggleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    public List<Document> findByOwner(Long ownerId);
    public List<Document> findByDocumentType(String documentType);
    public List<Folder> findByFolderOwner(Long ownerId);
    public List<Note> findByNoteOwner(Long ownerId);
}
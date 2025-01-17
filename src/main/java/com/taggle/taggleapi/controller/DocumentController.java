package com.taggle.taggleapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.service.DocumentService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {
    private DocumentService documentService;

    @PostMapping("/create/folder")
    public ResponseEntity<Folder> createFolder() {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/note")
    public ResponseEntity<Note> createNote(@RequestBody Note entity,@PathVariable Long parentFolderId) {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Document> getAllDocuments() {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}

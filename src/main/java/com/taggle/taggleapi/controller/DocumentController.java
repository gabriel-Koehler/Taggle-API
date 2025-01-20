package com.taggle.taggleapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.DTO.Document.FolderDocGET;
import com.taggle.taggleapi.model.DTO.Folder.FolderPOST;
import com.taggle.taggleapi.model.DTO.Folder.FolderPUT;
import com.taggle.taggleapi.model.DTO.Note.NotePOST;
import com.taggle.taggleapi.model.DTO.Note.NotePUT;
import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.service.DocumentService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {
    private DocumentService documentService;

    @PostMapping("/create/folder/{ownerId}")
    public ResponseEntity<Folder> createFolder(@RequestBody FolderPOST document,@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.saveFolder(document, ownerId),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/note/{id}/{ownerId}")
    public ResponseEntity<Note> createNote(@RequestBody NotePOST entity,@PathVariable Long parentFolderId,@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.saveNote(entity, parentFolderId, ownerId),HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/move/{id}/{toMove}")
    public ResponseEntity<Document> moveDocument(@PathVariable Long id, @PathVariable Long toMove) {
        
        try{
            return new ResponseEntity<>(documentService.moveDocument(id,toMove),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{ownerId}")
    public ResponseEntity<List<FolderDocGET>> getAllDocumentsByOwner(@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.getFolders(ownerId),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/note/owner")
    public ResponseEntity<Note> getNoteByOwner() {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/put/folder")
    public ResponseEntity<Folder> putFolder( @RequestBody FolderPUT entity) {
        try{
            return new ResponseEntity<>(documentService.updateFolder(entity),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/put/note")
    public ResponseEntity<Note> putNote(@RequestBody NotePUT entity) {
        try{
            return new ResponseEntity<>(documentService.updateNote(entity),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    

}

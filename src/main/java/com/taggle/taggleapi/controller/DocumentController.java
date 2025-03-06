package com.taggle.taggleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taggle.taggleapi.model.DTO.Document.FolderDocGET;
import com.taggle.taggleapi.model.DTO.Document.NoteDocGET;
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
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    //finished
    @PostMapping("/create/folder/{ownerId}/{parentFolderId}")
    public ResponseEntity<Folder> createFolder(@RequestBody FolderPOST document,@PathVariable Long ownerId,@PathVariable Long parentFolderId) {
        try{
            return new ResponseEntity<>(documentService.saveFolder(document, ownerId, parentFolderId ),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //finished
    @PostMapping("/create/note/{folderId}/{ownerId}")
    public ResponseEntity<Note> createNote(@RequestBody NotePOST entity,@PathVariable Long folderId,@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.saveNote(entity, folderId, ownerId),HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //finished
    @PutMapping("/move/{documentId}/{toMoveId}")
    public ResponseEntity<Document> moveDocument(@PathVariable Long documentId, @PathVariable Long toMoveId) {
        
        try{
            return new ResponseEntity<>(documentService.moveDocument(documentId,toMoveId),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //finished
    @GetMapping("/get/{ownerId}")
    public ResponseEntity<List<FolderDocGET>> getAllDocumentsByOwner(@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.getFoldersByOwner(ownerId),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // @GetMapping("/get/{folderId}")
    // public ResponseEntity<List<FolderDocGET>> getAllNoteByFolder(@PathVariable Long ownerId) {
    //     try{
    //         return new ResponseEntity<>(documentService.getFoldersByOwner(ownerId),HttpStatus.OK);
    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //         return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @GetMapping("/get/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/notes/owner/{ownerId}")
    public ResponseEntity<List<NoteDocGET>> getNoteByOwner(@PathVariable Long ownerId) {
        try{
            return new ResponseEntity<>(documentService.getNoteOwner(ownerId),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //finished
    @PutMapping("/put/folder")
    public ResponseEntity<Folder> putFolder( @RequestBody FolderPUT entity) {
        try{
            return new ResponseEntity<>(documentService.updateFolder(entity),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //finished
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

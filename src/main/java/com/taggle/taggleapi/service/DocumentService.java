package com.taggle.taggleapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.DTO.Document.FolderDocGET;
import com.taggle.taggleapi.model.DTO.Document.NoteDocGET;
import com.taggle.taggleapi.model.DTO.Folder.FolderPOST;
import com.taggle.taggleapi.model.DTO.Folder.FolderPUT;
import com.taggle.taggleapi.model.DTO.Note.NotePOST;
import com.taggle.taggleapi.model.DTO.Note.NotePUT;
import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.repository.FolderRepository;
import com.taggle.taggleapi.repository.NoteRepository;

import lombok.AllArgsConstructor;

@Service
public class DocumentService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DocumentRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private NoteRepository noteRepository;

    public Folder saveFolder(FolderPOST entity,Long ownerId) {
        Folder folder = new Folder();
        mapper.map(entity,folder);
        folder.setOwner(userService.getUserTaggle(ownerId));
        folder.setType("Folder");
        return folderRepository.save(folder).toDTO();
    }
    public Note saveNote(NotePOST entity,Long id,Long ownerId) {
        Note note = new Note();
        mapper.map(entity,note);
        note.setTitle(entity.getTitle());
        note.setContent(entity.getContent());
        note.setType("Note");
        note.setParentFolder(folderRepository.findById(id).get());
        note.setOwner(userService.getUserTaggle(ownerId));
        System.out.println(note.toDTO());
        return noteRepository.save(note).toDTO();
    }
    public Folder updateFolder(FolderPUT entity) {
        Folder folder =  folderRepository.findById(entity.getId()).get();
        folder.setTitle(entity.getTitle());
        folder.setAtLastAlteration(entity.getAtLastAlteration());
        return folderRepository.save(folder).toDTO();
        
    }
    public Document moveDocument(Long id, Long toMove){
        Folder folder =new Folder();
        Note note=new Note();
        Document doc = repository.findById(id).get();
        if(doc.getType()=="Folder"){
            mapper.map(doc,folder);
        }else{
            mapper.map(doc,note);
        }
        // System.out.println(folder.toDTO());
        // System.out.println(note.toDTO());
        // System.out.println(doc.toDTO().getOwner());
        doc.setParentFolder((Folder) repository.findById(toMove).get());

        return repository.save(doc);
    }
    public Note updateNote(NotePUT entity) {
        Note note=noteRepository.findById(entity.getId()).get();
        note.setAtLastAlteration(entity.getAtLastAlteration());
        note.setTitle(entity.getTitle());
        return noteRepository.save(note).toDTO();
    }
    public List<FolderDocGET> getFoldersByOwner(Long id){
        return folderRepository.findByOwner(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
        .stream().filter((f)->f.getParentFolder()==null)
        .map((f)->new FolderDocGET(f))
        .toList();
    }
    public List<FolderDocGET> getFolders(Long id){
        return folderRepository.findByOwner(userService.getUserTaggle(id))
        .stream().map((f)->new FolderDocGET(f))
        .toList();
    }

    public List<NoteDocGET> getNoteOwner(long id) {
        return noteRepository.findByOwner(userService.getUserTaggle(id)).stream().map((e)->new NoteDocGET(e)).toList();
    }
    public Document getDocumentPerOwner(long id) {
        return null;
    }
}

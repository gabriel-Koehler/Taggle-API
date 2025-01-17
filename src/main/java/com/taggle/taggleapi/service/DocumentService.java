package com.taggle.taggleapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.DTO.Folder.FolderPOST;
import com.taggle.taggleapi.model.DTO.Folder.FolderPUT;
import com.taggle.taggleapi.model.entity.Document;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.repository.FolderRepository;
import com.taggle.taggleapi.repository.NoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentService {
    private final ModelMapper mapper;
    private UserService userService;
    private FolderRepository folderRepository;
    private NoteRepository noteRepository;

    public Folder saveFolder(FolderPOST entity,Long ownerId) {
        Folder folder = new Folder();
        mapper.map(entity,folder);
        folder.setOwner(userService.getUserTaggle(ownerId));
        return folderRepository.save(folder).toDTO();
    }
    public Note saveNote(Note entity,Long id) {
        entity.setParentFolder(folderRepository.findById(id).get());
        return noteRepository.save(entity);
    }
    public Folder updateFolder(FolderPUT entity) {
        Folder newFolder=folderRepository.findById(entity.getId()).get();
        Folder folder=new Folder();
        mapper.map(newFolder, folder);
        return folderRepository.save(folder).toDTO();
        
    }
    public Note updateNote(Note entity) {
        
        return null;
    }

    public Document getDocumentPerOwner(long id) {
        return null;
    }
}

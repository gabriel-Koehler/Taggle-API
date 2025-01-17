package com.taggle.taggleapi.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.repository.FolderRepository;
import com.taggle.taggleapi.repository.NoteRepository;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private UserTaggleRepository repository;
    private FolderRepository folderRepository;
    private NoteRepository noteRepository;

    public UserTaggle saveUserTaggle(UserTaggle userTaggle) {
        repository.save(userTaggle);
        Folder defaultFolder = new Folder();
        defaultFolder.setTitle("Default For Note");
        defaultFolder.setType("Folder");
        defaultFolder.setOwner(userTaggle);
        folderRepository.save(defaultFolder);
        
        Note defaultNote = new Note();
        defaultNote.setTitle("default Note");
        defaultNote.setContent("This is a default note");
        defaultNote.setType("Note");
        defaultNote.setOwner(userTaggle);
        defaultNote.setParentFolder(defaultFolder);
        noteRepository.save(defaultNote);

        return userTaggle;
    }
    public UserTaggle putUserTaggle(UserTaggle userTaggle,Long userId) {
        repository.findById(userId).get();
        return repository.save(userTaggle);
    }
    public UserTaggle getUserTaggle(Long userId) {
        return repository.findById(userId).get();
    }
    public List<UserTaggle> getAllUserTaggle() {
        List<UserTaggle> users =repository.findAll();

        return users.stream().map(UserTaggle::toDTO).toList();
    }
    public void deleteUserTaggle(Long userId) {
        repository.deleteById(userId);
    }
}

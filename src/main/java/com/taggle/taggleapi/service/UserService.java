package com.taggle.taggleapi.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private UserTaggleRepository repository;
    private DocumentRepository documentRepository;

    public UserTaggle saveUserTaggle(UserTaggle userTaggle) {
        repository.save(userTaggle);
        Folder defaultFolder = new Folder();
        defaultFolder.setTitle("default");
        defaultFolder.setType("Folder");
        defaultFolder.setOwner(userTaggle);
        documentRepository.save(defaultFolder);
        return userTaggle;
    }
    public void putUserTaggle(UserTaggle userTaggle) {
        repository.save(userTaggle);
    }
    public UserTaggle getUserTaggle(Long userId) {
        return repository.findById(userId).get();
    }
    public List<UserTaggle> getAllUserTaggle() {
        return repository.findAll();
    }
    public void deleteUserTaggle(Long userId) {
        repository.deleteById(userId);
    }
}

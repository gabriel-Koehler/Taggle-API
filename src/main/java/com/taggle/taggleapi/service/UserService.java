package com.taggle.taggleapi.service;

import org.springframework.stereotype.Service;

import com.taggle.taggleapi.model.UserTaggle;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private UserTaggleRepository repository;
    private DocumentRepository documentRepository;

    public UserTaggle saveUserTaggle(UserTaggle userTaggle) {

        return repository.save(userTaggle);
    }
    public void putUserTaggle(UserTaggle userTaggle) {
        repository.save(userTaggle);
    }
    public void getUserTaggle(Long userId) {
        repository.findById(userId);
    }
    public void getAllUserTaggle() {
        repository.findAll();
    }
    public void deleteUserTaggle(Long userId) {
        repository.deleteById(userId);
    }
}

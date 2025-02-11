package com.taggle.taggleapi.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.taggle.taggleapi.model.DTO.UserTaggle.UserTaggleGET;
import com.taggle.taggleapi.model.DTO.UserTaggle.UserTagglePOST;
import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.model.entity.Roles;
import com.taggle.taggleapi.model.entity.UserTaggle;
import com.taggle.taggleapi.repository.DocumentRepository;
import com.taggle.taggleapi.repository.FolderRepository;
import com.taggle.taggleapi.repository.NoteRepository;
import com.taggle.taggleapi.repository.UserTaggleRepository;

import lombok.AllArgsConstructor;

@Service
public class UserService{
    @Autowired
    private UserTaggleRepository repository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserTaggle saveUserTaggle(UserTagglePOST userPOST) {
        if(repository.findByUsername(userPOST.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username alredy exists");
        }
        System.out.println(userPOST);
        UserTaggle userTaggle = new UserTaggle();
        mapper.map(userPOST, userTaggle);
        userTaggle.setPassword(bCryptPasswordEncoder.encode(userPOST.getPassword()));
        userTaggle.setRole(Roles.ROLE_ADMIN);
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
    public UserTaggle findByUsername(String username) {
        return repository.findByUsername(username).get();
    }
    public UserTaggle putUserTaggle(UserTaggle userTaggle,Long userId) {
        repository.findById(userId).get();
        return repository.save(userTaggle);
    }
    public UserTaggle getUserTaggle(Long userId) {
        return repository.findById(userId).get();
    }
    public List<UserTaggleGET> getAllUserTaggle() {
        List<UserTaggle> users =repository.findAll();
        // System.out.println(users);
        return users.stream().map(e -> new UserTaggleGET(
                                    e.getId(),
                                    e.getUsername(),
                                    e.getAtCreate(),
                                    e.getAtLastAlteration(),
                                    e.getIsActive()
                                    )).toList();
    }
    public void deleteUserTaggle(Long userId) {
        repository.deleteById(userId);
    }
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    // }
}

package com.taggle.taggleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taggle.taggleapi.model.entity.Note;
import com.taggle.taggleapi.model.entity.UserTaggle;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findByOwner(UserTaggle owner);
}

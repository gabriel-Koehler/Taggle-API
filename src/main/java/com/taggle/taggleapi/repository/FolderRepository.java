package com.taggle.taggleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taggle.taggleapi.model.entity.Folder;
import com.taggle.taggleapi.model.entity.UserTaggle;

import java.util.List;


@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
  List<Folder> findByOwner(UserTaggle owner);
  // List<Folder> findByOwner(UserTaggle owner);
}

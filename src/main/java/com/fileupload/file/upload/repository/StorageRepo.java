package com.fileupload.file.upload.repository;

import com.fileupload.file.upload.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepo extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);
}

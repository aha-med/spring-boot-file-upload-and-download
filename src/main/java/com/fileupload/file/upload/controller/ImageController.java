package com.fileupload.file.upload.controller;


import com.fileupload.file.upload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("image")MultipartFile file)
                              throws IOException {
        String uploadImage=imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }
    @GetMapping("/download/{name}")
    public ResponseEntity<?> downloadImages(@PathVariable String name) throws DataFormatException {
       byte[]imageData= imageService.downloadImage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg"))
                .body(imageData);
    }

}

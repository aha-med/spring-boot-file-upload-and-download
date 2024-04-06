package com.fileupload.file.upload.service;

import com.fileupload.file.upload.Utils;
import com.fileupload.file.upload.entity.Image;
import com.fileupload.file.upload.repository.StorageRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {
    private final StorageRepo storageRepo;
    ImageService(StorageRepo storageRepo){
        this.storageRepo=storageRepo;
    }
    public String uploadImage(MultipartFile file) throws IOException {
       Image image= storageRepo.save(Image.builder().name(file.getOriginalFilename())
                .type(file.getContentType()).image(Utils.compressImage(file.getBytes())).build());
        if(image!=null){
            return "file uploaded successfully";
        }
        return null;
    }
    public byte[] downloadImage(String filename) throws DataFormatException {
        Optional<Image> image= storageRepo.findByName(filename);
        return Utils.decompressImage(image.get().getImage());
    }


}

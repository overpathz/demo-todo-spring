package com.example.demoreplay.service;

import com.example.demoreplay.entity.FileEntity;
import com.example.demoreplay.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setMediaType(detectMediaType(file));
//        fileEntity.setFileData(file.getBytes());
        fileRepository.save(fileEntity);
    }

    private String detectMediaType(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        return tika.detect(file.getBytes());
    }

    public FileEntity downloadFile(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
}

package noob.storagesystem.controller;

import lombok.RequiredArgsConstructor;
import noob.storagesystem.model.FileBody;
import noob.storagesystem.repository.FileDataRepository;
import noob.storagesystem.service.FileService;
import noob.storagesystem.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    //some changes

    //some again
    private final FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
            throws IOException {
        String uploadFile = fileService.uploadFileToFileSystem(file);
        return ResponseEntity.ok(uploadFile);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName)
            throws IOException {
        FileBody fileBody = fileService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileBody.getDataType()))
                .body(fileBody.getData());
    }
}
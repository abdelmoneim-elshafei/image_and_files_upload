package noob.storagesystem.service;

import lombok.RequiredArgsConstructor;
import noob.storagesystem.entity.FileData;
import noob.storagesystem.entity.ImageData;
import noob.storagesystem.model.FileBody;
import noob.storagesystem.repository.FileDataRepository;
import noob.storagesystem.util.ImageDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final static String DIR_PATH = "/home/abdelmoneim/Development/Java Spring/storage-system/src/Files/";
    private final FileDataRepository fileDataRepository;

    public String uploadFileToFileSystem(MultipartFile file) throws IOException {
        String file_path = DIR_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(file_path)
                .build());
        file.transferTo(new File(file_path));
        if (fileData != null) {
            return "File upload successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public FileBody downloadFile(String name) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(name);
        String filePath = fileData.get().getFilePath();
        return FileBody.builder()
                .data(Files.readAllBytes(new File(filePath).toPath()))
                .dataType(fileData.get().getType())
                .build();
    }
}

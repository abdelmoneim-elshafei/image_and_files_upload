package noob.storagesystem.controller;

import lombok.RequiredArgsConstructor;
import noob.storagesystem.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    //controller
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image)
            throws IOException {
        String uploadImage = imageService.uploadImage(image);
        return ResponseEntity.ok(uploadImage);
    }

    @GetMapping("/download/{imageName}")
    public ResponseEntity<?> downloadImage(@PathVariable  String imageName){
        byte[] image = imageService.downloadImage(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }
}

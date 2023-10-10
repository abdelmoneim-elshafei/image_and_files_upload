package noob.storagesystem.service;

import lombok.RequiredArgsConstructor;
import noob.storagesystem.entity.ImageData;
import noob.storagesystem.repository.ImageRepository;
import noob.storagesystem.util.ImageDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageDataUtil.compressImage(file.getBytes()))
                .build());
        if (imageData != null) {
            return "Image upload successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String imageName){
        Optional<ImageData> dbImageData =imageRepository.findByName(imageName);
        return ImageDataUtil.decompressImage(dbImageData.get().getImageData());

    }

}

package application.controller;

import application.model.ImageData;
import application.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * User: militer
 * Date: 10.06.2017.
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public void uploadImage(@RequestBody MultipartFile image) {
        try {
            String originalFilename = image.getOriginalFilename();
            int lastDotIndex = originalFilename.lastIndexOf('.');
            ImageData imageToStore = new ImageData(
                    originalFilename.substring(0, lastDotIndex),
                    originalFilename.substring(lastDotIndex + 1),
                    image.getBytes());
            imageService.uploadImage(imageToStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Got the file!");
    }

    @GetMapping(value="/{imageId}")
    public ImageData getImage(@PathVariable Integer imageId) {
        return imageService.getImage(imageId);
    }

    @GetMapping
    public List<ImageData> getImages() {
        return imageService.getAllImages();
    }
}
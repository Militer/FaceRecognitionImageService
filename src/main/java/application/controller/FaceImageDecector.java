package application.controller;

import application.model.ImageData;
import application.service.ImageService;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: militer
 * Date: 05.06.2017.
 */
@RestController
@RequestMapping("/faceDetector")
public class FaceImageDecector {
    private static final String HOSTED_IMAGES_DIRECTORY = "hosted";
    private static final String HOSTED_IMAGES_BASE = System.getProperty("java.io.tmpdir");
    private final CascadeClassifier cascadeClassifier;
    private final ImageService imageService;

    @Autowired
    public FaceImageDecector(CascadeClassifier cascadeClassifier, ImageService imageService) {
        this.cascadeClassifier = cascadeClassifier;
        this.imageService = imageService;
    }

    @GetMapping
    public Rect[] get(String imageName) {
        ImageData imageData = imageService.getImageByName(imageName);
        File directory = new File(HOSTED_IMAGES_BASE + "/" + imageData.getName());
        System.out.println("The following temporary directory: " + imageData.getName() + " was created with success: " + directory.mkdir());
        String imageFilename = imageData.getName() + "." + imageData.getExtension();
        String pathToImage = HOSTED_IMAGES_BASE + "/" + imageData.getName() + "/" + imageFilename;
        File image = new File(pathToImage);
        try {
            System.out.println("The following image: " + imageFilename + " was created with success: " + image.createNewFile());
            try (FileOutputStream fileOutputStream = new FileOutputStream(image)) {
                fileOutputStream.write(imageData.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detectFaceFromImage(image);
    }

    private Rect[] detectFaceFromImage(File image){
        Mat imageMat = Imgcodecs.imread(image.getAbsolutePath());

        MatOfRect faceDetections = new MatOfRect();
        cascadeClassifier.detectMultiScale(imageMat, faceDetections);

        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(imageMat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        return faceDetections.toArray();
    }
}

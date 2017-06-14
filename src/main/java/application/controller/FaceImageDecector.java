package application.controller;

import application.model.ImageData;
import application.service.ImageService;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: militer
 * Date: 05.06.2017.
 */
@RestController
@RequestMapping("/faceDetector")
public class FaceImageDecector {
    private final CascadeClassifier cascadeClassifier;
    private final ImageService imageService;

    @Autowired
    public FaceImageDecector(CascadeClassifier cascadeClassifier, ImageService imageService) {
        this.cascadeClassifier = cascadeClassifier;
        this.imageService = imageService;
    }

    @GetMapping(value = "/{imageId}")
    public ImageData get(@PathVariable(value = "imageId") Integer imageId) {
        ImageData imageData = imageService.getImage(imageId);
        imageData.setImage(detectFaceFromImage(imageData));
        return imageData;
    }

    private byte[] detectFaceFromImage(ImageData imageData){
        Mat imageMat = Imgcodecs.imdecode(new MatOfByte(imageData.getImage()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        MatOfRect faceDetections = new MatOfRect();
        cascadeClassifier.detectMultiScale(imageMat, faceDetections);
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(imageMat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 3);
        }
        MatOfByte result = new MatOfByte();
        Imgcodecs.imencode("." + imageData.getExtension(), imageMat, result);
        return result.toArray();
    }
}

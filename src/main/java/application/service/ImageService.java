package application.service;

import application.dao.ImageServiceDAO;
import application.model.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: militer
 * Date: 11.06.2017.
 */
@Service
public class ImageService {
    private final ImageServiceDAO imageServiceDAO;

    @Autowired
    public ImageService(ImageServiceDAO imageServiceDAO) {
        this.imageServiceDAO = imageServiceDAO;
    }

    public synchronized void uploadImage(ImageData imageData) {
        imageServiceDAO.addImage(imageData);
    }

    public ImageData getImage(int imageId) {
        return imageServiceDAO.getImage(imageId);
    }

    public ImageData getImageByName(String imageName) {
        return imageServiceDAO.getImageByName(imageName);
    }

    public List<ImageData> getAllImages() {
        return imageServiceDAO.getAllImages();
    }
}

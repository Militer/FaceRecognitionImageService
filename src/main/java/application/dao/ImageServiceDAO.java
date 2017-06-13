package application.dao;

import application.model.ImageData;

import java.util.List;

/**
 * User: militer
 * Date: 11.06.2017.
 */
public interface ImageServiceDAO {
    void addImage(ImageData imageData);

    ImageData getImage(int imageId);

    ImageData getImageByName(String imageName);

    List<ImageData> getAllImages();
}

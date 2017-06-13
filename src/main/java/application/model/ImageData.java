package application.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: militer
 * Date: 11.06.2017.
 */
@Entity
@Table(name = "image")
public class ImageData implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    public ImageData() {
    }

    public ImageData(String name, String extension, byte[] image) {
        this.name = name;
        this.extension = extension;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

package application.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: militer
 * Date: 02.04.2017.
 */
@Entity
@Table(name = "carEngine")
public class CarEngine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "power")
    private int power;

    @Column(name = "maximumSpeed")
    private int maximumSpeed;

    @Column(name = "acceleration")
    private int acceleration;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "price")
    private int price;

    public CarEngine() {
    }

    public CarEngine(String name, int power, int maximumSpeed, int acceleration, String imagePath, int price) {
        this.name = name;
        this.power = power;
        this.maximumSpeed = maximumSpeed;
        this.acceleration = acceleration;
        this.imagePath = imagePath;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package com.campusdual;

import com.campusdual.util.Utils;
import com.campusdual.util.Input;
import org.json.simple.JSONObject;

public class Car implements Comparable<Car> {

    public static final int MAX_VEL = 200;

    public static final String BRAND = "BRAND";

    public static final String MODEL = "MODEL";
    public static final String GARAGENAME = "GARAGENAME";
    private String brand;
    private String model;
    private String garageName = "";
    private int speedometer = 0;
    private double distance = 0.0;

    public Car() {
        this.brand = Input.string("Marca del coche: ");
        this.model = Input.string("Modelo del coche: ");
    }


    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car(String brand, String model, String garageName) {
        this.brand = brand;
        this.model = model;
        this.garageName = garageName;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;

    }

    public void setSpeedometer(int speedometer) {
        this.speedometer = speedometer;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSpeedometer() {
        return speedometer;
    }

    public double getDistance() {
        return distance;
    }

    /*@Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", garageName='" + garageName + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", garageName='" + garageName + '\'' +
                ", speedometer=" + speedometer +
                ", distance=" + distance +
                '}';
    }

    public void accel() {
        if (this.speedometer < Car.MAX_VEL) {
            speedometer += 10;
            updateDistance();
        }
    }

    public void decel() {
        if (this.speedometer > 0) {
            speedometer -= 10;
            updateDistance();
        }
    }

    public void aleatorizing() {
        int isAccelerating = Utils.getRandomNumberInRange(1, 3);
        if (isAccelerating != 2) {
            accel();
        } else {
            decel();
        }

    }

    public void updateDistance() {
        this.distance += this.speedometer * 16.667;
    }

    public JSONObject exportCar() {
        JSONObject json = new JSONObject();
        json.put(Car.BRAND, brand);
        json.put(Car.MODEL, model);
        json.put(Car.GARAGENAME, garageName);
        return json;
    }
    public static Car importCar(JSONObject obj) {
        String model = (String) obj.get(Car.MODEL);
        String brand = (String) obj.get(Car.BRAND);
        String garage = (String) obj.get(Car.GARAGENAME);
        return new Car(brand, model, garage);

    }

    @Override
    public int compareTo(Car o) {
        if (this.getDistance() > o.getDistance()) {
            return 1;
        } else if (this.getDistance() < o.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }


}

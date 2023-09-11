package com.campusdual;

import com.campusdual.util.Input;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Garage {


    public static final String NAME = "NAME";
    private String name;
    private List<Car> cars;

    public Garage() {
        this.name = Input.string("Introduzca el nombre de la escudería: ");
        this.cars = new ArrayList<>();
    }

    public Garage(String name) {
        this.name = name;
        this.cars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        car.setGarageName(this.getName());
    }

    public void removeCar(Car car){
        cars.remove(car);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }

    public JSONObject exportGarage() {
        JSONObject json = new JSONObject();
        json.put(Garage.NAME, name);
        return json;
    }
    public static Garage importGarage(JSONObject obj) {
        String name = (String) obj.get(Garage.NAME);
        return new Garage(name);
    }
}

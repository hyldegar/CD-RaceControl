package com.campusdual;

import com.campusdual.util.Input;

import java.util.List;

public abstract class AbstractRace {

    public static final String NAME= "NAME";

    private String name;
    private List<Garage> participatingGarages;
    private List<Car> participatingCars;
    private List<Car> podium;

    public AbstractRace(String name) {
        this.name = name;
        this.participatingGarages = null;
        this.participatingCars = null;
        this.podium = null;
    }

    public AbstractRace() {
        this.name= Input.string("Introduzca el nombre de la carrera ");
        this.participatingGarages = null;
        this.participatingCars = null;
        this.podium = null;
    }

    public AbstractRace(String name, List<Garage> participatingGarages) {

    }



    public abstract void startRace();
    public abstract void calculatePodium();

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Garage> getParticipatingGarages() {
        return participatingGarages;
    }

    public void setParticipatingGarages(List<Garage> participatingGarages) {
        this.participatingGarages = participatingGarages;
    }

    public List<Car> getParticipatingCars() {
        return participatingCars;
    }

    public void setParticipatingCars(List<Car> participatingCars) {
        this.participatingCars = participatingCars;
    }

    public List<Car> getPodium() {
        return podium;
    }

    public void setPodium(List<Car> podium) {
        this.podium = podium;
    }


}

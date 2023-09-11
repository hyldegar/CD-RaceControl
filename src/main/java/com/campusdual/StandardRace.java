package com.campusdual;


import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardRace extends AbstractRace{

    public static final String DURATION="Duration";
    private int durationHours;

    public StandardRace(){
        super();
        this.durationHours = 3;


    }
    public StandardRace(String name) {
        super();

    }
    public StandardRace(String name, int durationHours) {
        super(name);
        this.durationHours = durationHours;
    }

    @Override
    public void startRace() {
        if(this.getParticipatingGarages().size() ==1){
            this.setParticipatingCars(this.getParticipatingGarages().get(0).getCars());
        }else{
            List<Car> participatingCars = new ArrayList<>();

            for(Garage garage: this.getParticipatingGarages()){
                Random random = new Random();
                participatingCars.add(garage.getCars().get(random.nextInt(garage.getCars().size())));
            }
        }
        calculatePodium();
    }

    @Override
    public void calculatePodium() {

    }


    @Override
    public String toString() {
        return "StandardRace{" +
                "name='" + getName() + '\'' +
                ", durationHours=" + durationHours +
                ", participatingGarages=" + getParticipatingGarages() +
                ", participatingCars=" + getParticipatingCars() +
                ", podium=" + getPodium() +
                '}';
    }

    public JSONObject exportStandardRace() {
        JSONObject json = new JSONObject();
        json.put(StandardRace.NAME,this.getName());
        json.put(StandardRace.DURATION,this.durationHours);
        return json;
    }
    public static StandardRace importStandardRace(JSONObject obj) {
        String name = (String) obj.get(StandardRace.NAME);
        Long duration = (Long) obj.get(StandardRace.DURATION);
        return new StandardRace(name, duration.intValue());
    }




}

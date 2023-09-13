package com.campusdual;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EliminationRace extends AbstractRace {

    public static final String WARM = "WarmUp";

    public static final String ELIMINATION_INTERVAL = "EliminationInterval";
    private int warmUpDurationMinutes;
    private int eliminationIntervalMinutes;

    public EliminationRace() {
        super();
        this.warmUpDurationMinutes = 10;
        this.eliminationIntervalMinutes = 1;
    }

    public EliminationRace(String name, int warmUpDurationMinutes, int eliminationIntervalMinutes) {
        super(name);
        this.warmUpDurationMinutes = warmUpDurationMinutes;
        this.eliminationIntervalMinutes = eliminationIntervalMinutes;
    }



    @Override
    public void startRace() {
        List<Car> participatingCars = new ArrayList<>();
        if (getParticipatingGarages().size() == 1) {
            setParticipatingCars(getParticipatingGarages().get(0).getCars());
            System.out.println("fui por el if");
            participatingCars= this.getParticipatingCars();
        } else {
            for (Garage garage : getParticipatingGarages()) {
                Random random = new Random();
                participatingCars.add(garage.getCars().get(random.nextInt(garage.getCars().size())));
            }
        }
        List<Car> eliminatedCars = new ArrayList<>();

        // Calentamiento (Warm-up)
        System.out.println("Comenzando el calentamiento...");
        for(int i=0 ; i<this.warmUpDurationMinutes;i++) {
            warmUp(participatingCars);
        }
        System.out.println("Comienza la eliminatoria");
        int numberOfEliminations = participatingCars.size() - 4;

        for (int j = 0; j < numberOfEliminations; j++) {
            //warmUp(participatingCars); // Realizar calentamiento en cada intervalo
            eliminateCars(participatingCars, eliminatedCars);
        }
        participatingCars.sort(Collections.reverseOrder());
        List<Car> podium = participatingCars.subList(0, Math.min(participatingCars.size(), 3));
        setPodium(podium);
        for (Car car: participatingCars){
            car.setSpeedometer(0);
        }
    }

    private void warmUp(List<Car> cars) {
        for (Car car : cars) {
            car.aleatorizing();
        }
    }

    private List<Car> eliminateCars(List<Car> participatingCars, List<Car> eliminatedCars) {
        if (!participatingCars.isEmpty()) {
            // Ordenar los coches por distancia recorrida (de menor a mayor)
            participatingCars.sort(null);

            // Eliminar el coche que va en la última posición
            Car eliminatedCar = participatingCars.remove(participatingCars.size() - 1);
            eliminatedCars.add(eliminatedCar);
            System.out.println("Coche eliminado: " + eliminatedCar.getBrand() + " " + eliminatedCar.getModel());
        }
        return participatingCars;
    }



    @Override
    public String toString() {
        return "EliminationRace{" +
                "name='" + getName() + '\'' +
                "warmUpDurationMinutes=" + warmUpDurationMinutes +
                ", eliminationIntervalMinutes=" + eliminationIntervalMinutes +
                '}';
    }

    public JSONObject exportEliminationRace() {
        JSONObject json = new JSONObject();
        json.put(EliminationRace.NAME, this.getName());
        json.put(WARM, this.warmUpDurationMinutes);
        json.put(ELIMINATION_INTERVAL, this.eliminationIntervalMinutes);
        return json;
    }

    public static EliminationRace importEliminationRace(JSONObject obj) {
        String name = (String) obj.get(EliminationRace.NAME);
        Long warmup = (Long) obj.get(EliminationRace.WARM);
        Long elInterval = (Long) obj.get(EliminationRace.ELIMINATION_INTERVAL);
        return new EliminationRace(name, warmup.intValue(), elInterval.intValue());
    }
}

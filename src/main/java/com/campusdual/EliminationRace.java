package com.campusdual;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static EliminationRace importEliminationRace(JSONObject obj) {
        String name = (String) obj.get(EliminationRace.NAME);
        int warmup = (int) obj.get(EliminationRace.WARM);
        int elInterval = (int) obj.get(EliminationRace.ELIMINATION_INTERVAL);
        return new EliminationRace(name, warmup, elInterval);
    }

    @Override
    public void startRace() {
        List<Car> participatingCars = getParticipatingCars();
        List<Car> eliminatedCars = new ArrayList<>();

        // Calentamiento (Warm-up)
        System.out.println("Comenzando el calentamiento...");


    }

    private void eliminateCars(List<Car> participatingCars, List<Car> eliminatedCars) {
        // Ordenar los coches por distancia recorrida (de menor a mayor)
        participatingCars.sort(null);

        for (int i = 0; i < this.warmUpDurationMinutes; i++) {
            for (Car car : participatingCars) {
                car.aleatorizing();
            }
        }
        for (int j = 0; j < this.getParticipatingCars().size() - 1; j++) {
            for (Car car : participatingCars) {
                car.aleatorizing();
            }
        }
        participatingCars.sort(Collections.reverseOrder());
        // Eliminar el coche que va en la última posición
        if (!participatingCars.isEmpty()) {
            Car eliminatedCar = participatingCars.remove(participatingCars.size() - 1);
            eliminatedCars.add(eliminatedCar);
            System.out.println("Coche eliminado: " + eliminatedCar.getBrand() + " " + eliminatedCar.getModel());
        }

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
}

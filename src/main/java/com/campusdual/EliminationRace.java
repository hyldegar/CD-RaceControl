package com.campusdual;

import org.json.simple.JSONObject;

public class EliminationRace extends AbstractRace{

    public static final String WARM= "WarmUp";

    public static final String ELIMINATION_INTERVAL= "EliminationInterval";
    private int warmUpDurationMinutes;
    private int eliminationIntervalMinutes;

    public EliminationRace(){
        super();
        this.warmUpDurationMinutes = 10;
        this.eliminationIntervalMinutes=1;
    }
    public EliminationRace(String name, int warmUpDurationMinutes, int eliminationIntervalMinutes) {
        super(name);
        this.warmUpDurationMinutes = warmUpDurationMinutes;
        this.eliminationIntervalMinutes = eliminationIntervalMinutes;
    }

    @Override
    public void startRace() {

    }

    @Override
    public void calculatePodium() {

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
        json.put(EliminationRace.NAME,this.getName());
        json.put(WARM,this.warmUpDurationMinutes);
        json.put(ELIMINATION_INTERVAL,this.eliminationIntervalMinutes);
        return json;
    }
    public static EliminationRace importEliminationRace(JSONObject obj) {
        String name = (String) obj.get(EliminationRace.NAME);
        int warmup = (int) obj.get(EliminationRace.WARM);
        int elInterval = (int) obj.get(EliminationRace.ELIMINATION_INTERVAL);
        return new EliminationRace(name,warmup,elInterval);
    }
}

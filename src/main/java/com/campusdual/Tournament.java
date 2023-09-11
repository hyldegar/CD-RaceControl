package com.campusdual;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tournament {


    public static final String NAME = "Name";
    private String name;
    private List<StandardRace> racesStandard;

    private List<EliminationRace> racesElimination;

    private List<Garage> participatingGarages;

    private List<Car> competingCars;

    private List<Car> Top;

    public Tournament(String name) {
        this.name = name;
        this.racesStandard = new ArrayList<>();
        this.racesElimination = new ArrayList<>();
        this.competingCars = new ArrayList<>();
        this.participatingGarages = new ArrayList<>();
        this.Top = new ArrayList<>();
    }

    public static Tournament createStandarTournament(String nameTournament, List<StandardRace> allStandardRaces, List<EliminationRace> allEliminationRaces) {
        Random random = new Random();
        int standard = random.nextInt(11);
        Tournament tournament = new Tournament(nameTournament);
        tournament.racesStandard = tournament.aleatorizingStandardRaces(standard, allStandardRaces);
        tournament.racesElimination = tournament.aleatorizingEliminationRaces(10 - standard, allEliminationRaces);
        return tournament;

    }

    public static Tournament createPersonalizedTournament(String nameTournament, List<StandardRace> allStandardRaces, List<EliminationRace> allEliminationRaces, int number) {
        Random random = new Random();
        int standard = random.nextInt(number + 1);
        Tournament tournament = new Tournament(nameTournament);
        tournament.racesStandard = tournament.aleatorizingStandardRaces(standard, allStandardRaces);
        tournament.racesElimination = tournament.aleatorizingEliminationRaces(10 - standard, allEliminationRaces);
        return tournament;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StandardRace> getRacesStandard() {
        return racesStandard;
    }

    public void setRacesStandard(List<StandardRace> racesStandard) {
        this.racesStandard = racesStandard;
    }

    public List<EliminationRace> getRacesElimination() {
        return racesElimination;
    }

    public void setRacesElimination(List<EliminationRace> racesElimination) {
        this.racesElimination = racesElimination;
    }

    public List<Garage> getParticipatingGarages() {
        return participatingGarages;
    }

    public void setParticipatingGarages(List<Garage> participatingGarages) {
        this.participatingGarages = participatingGarages;
    }

    public List<Car> getCompetingCars() {
        return competingCars;
    }

    public void setCompetingCars(List<Car> competingCars) {
        this.competingCars = competingCars;
    }

    public List<Car> getTop() {
        return Top;
    }

    public void setTop(List<Car> top) {
        Top = top;
    }


    public List<StandardRace> aleatorizingStandardRaces(int number, List<StandardRace> allStandardRaces) {

        List<StandardRace> selectedRaces = new ArrayList<>();
        Random random = new Random();
        if (number > allStandardRaces.size()) {
            number = allStandardRaces.size();
        }
        while (selectedRaces.size() < number) {
            int randomIndex = random.nextInt(allStandardRaces.size());
            StandardRace selectedRace = allStandardRaces.get(randomIndex);
            selectedRaces.add(selectedRace);
            allStandardRaces.remove(randomIndex);
        }

        return selectedRaces;
    }

    public List<EliminationRace> aleatorizingEliminationRaces(int number, List<EliminationRace> allEliminationRaces) {

        List<EliminationRace> selectedRaces = new ArrayList<>();
        Random random = new Random();
        if (number > allEliminationRaces.size()) {
            number = allEliminationRaces.size();
        }
        while (selectedRaces.size() < number) {
            int randomIndex = random.nextInt(allEliminationRaces.size());
            EliminationRace selectedRace = allEliminationRaces.get(randomIndex);
            selectedRaces.add(selectedRace);
            allEliminationRaces.remove(randomIndex);
        }

        return selectedRaces;
    }




    //Data Management
    public JSONObject exportTournament() {
        JSONObject json = new JSONObject();
        json.put(Tournament.NAME, this.getName());
        return json;
    }

    public static Tournament importTournament(JSONObject obj) {
        String name = (String) obj.get(Tournament.NAME);
        return new Tournament(name);
    }
}

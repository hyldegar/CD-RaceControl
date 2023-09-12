package com.campusdual;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Tournament {


    public static final String NAME = "Name";
    private String name;
    private List<StandardRace> racesStandard;

    private List<EliminationRace> racesElimination;

    private List<Garage> participatingGarages;

    private List<Car> competingCars;

    private Map<Car, Integer> top;


    public Tournament(String name) {
        this.name = name;
        this.racesStandard = new ArrayList<>();
        this.racesElimination = new ArrayList<>();
        this.competingCars = new ArrayList<>();
        this.participatingGarages = new ArrayList<>();
        this.top = new HashMap<>();
    }
    public Tournament(String name, List<StandardRace> standardRaces, List<EliminationRace> eliminationRaces) {
        this.name = name;
        if (standardRaces != null) {
            this.racesStandard = standardRaces;
        } else {
            this.racesStandard = new ArrayList<>();
        }

        if (eliminationRaces != null) {
            this.racesElimination = eliminationRaces;
        } else {
            this.racesElimination = new ArrayList<>();
        }

        this.competingCars = new ArrayList<>();
        this.participatingGarages = new ArrayList<>();
        this.top = new HashMap<>();
    }

    public static Tournament createStandarTournament(String nameTournament, List<StandardRace> allStandardRaces, List<EliminationRace> allEliminationRaces) {
        Random random = new Random();
        int number = 10; // Número total de carreras en el torneo
        int standard = random.nextInt(number + 1); // Genera un número aleatorio entre 0 y number
        int maxStandard = Math.min(number, 10); // Asegura que no tengas más de 10 carreras estándar
        standard = Math.min(standard, maxStandard); // Limita el número de carreras estándar al máximo posible
        Tournament tournament = new Tournament(nameTournament);
        tournament.racesStandard = tournament.aleatorizingStandardRaces(standard, allStandardRaces);
        tournament.racesElimination = tournament.aleatorizingEliminationRaces(number - standard, allEliminationRaces);
        return tournament;
    }

    public static Tournament createPersonalizedTournament(String nameTournament, List<StandardRace> allStandardRaces, List<EliminationRace> allEliminationRaces, int number) {
        Random random = new Random();
        int maxStandard = Math.min(number, 10); // Asegura que no tengas más de 10 carreras estándar
        int standard = random.nextInt(maxStandard + 1); // Genera un número aleatorio entre 0 y maxStandard
        Tournament tournament = new Tournament(nameTournament);
        tournament.racesStandard = tournament.aleatorizingStandardRaces(standard, allStandardRaces);
        tournament.racesElimination = tournament.aleatorizingEliminationRaces(number - standard, allEliminationRaces);
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

    public Map<Car, Integer> getTop() {
        return top;
    }

    public void setTop(Map<Car, Integer> top) {
        top = top;
    }

    public List<StandardRace> aleatorizingStandardRaces(int number, List<StandardRace> allStandardRaces) {
        List<StandardRace> selectedRaces = new ArrayList<>();
        List<StandardRace> copyAllStandardRaces = new ArrayList<>(allStandardRaces); // Copiar la lista original

        Random random = new Random();
        if (number > copyAllStandardRaces.size()) {
            number = copyAllStandardRaces.size();
        }

        while (selectedRaces.size() < number) {
            int randomIndex = random.nextInt(copyAllStandardRaces.size());
            StandardRace selectedRace = copyAllStandardRaces.get(randomIndex);
            selectedRaces.add(selectedRace);
        }

        return selectedRaces;
    }

    public List<EliminationRace> aleatorizingEliminationRaces(int number, List<EliminationRace> allEliminationRaces) {
        List<EliminationRace> selectedRaces = new ArrayList<>();
        List<EliminationRace> copyAllEliminationRaces = new ArrayList<>(allEliminationRaces); // Copiar la lista original

        Random random = new Random();
        if (number > copyAllEliminationRaces.size()) {
            number = copyAllEliminationRaces.size();
        }

        while (selectedRaces.size() < number) {
            int randomIndex = random.nextInt(copyAllEliminationRaces.size());
            EliminationRace selectedRace = copyAllEliminationRaces.get(randomIndex);
            selectedRaces.add(selectedRace);
        }

        return selectedRaces;
    }


    public void tournamentStart() {

        for (StandardRace race : racesStandard) {
            race.setParticipatingGarages(this.participatingGarages);
            System.out.println(this.participatingGarages);
            race.startRace();
            System.out.println(race.getPodium());
            for (Car car : race.getPodium()) {
                int score = 3 - race.getPodium().indexOf(car);
                updateTop(car,score);
            }
        }

        for (EliminationRace race : racesElimination) {
            race.setParticipatingGarages(this.participatingGarages);
            race.startRace();
            for (Car car : race.getPodium()) {
                int score = 3 - race.getPodium().indexOf(car);
                updateTop(car,score);
            }
        }
        System.out.println(top);
    }

    private void updateTop(Car carWinner, int points) {

            int currentPoints = top.getOrDefault(carWinner, 0); // Obtiene los puntos actuales del coche (0 si no existe)

            top.put(carWinner, currentPoints + points); // Actualiza los puntos del coche en el Top

    }


    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", racesStandard=" + racesStandard +
                ", racesElimination=" + racesElimination +
                ", participatingGarages=" + participatingGarages +
                ", competingCars=" + competingCars +
                ", top=" + top +
                '}';
    }

    //Data Management
    public JSONObject exportTournament() {
        JSONObject json = new JSONObject();
        //Con el fin de que las carreras que componen el torneo se conserven debo guardar las carreras
        json.put(Tournament.NAME, this.getName());
        JSONArray standardRacesArrayToExport = new JSONArray();
        for (StandardRace race : this.racesStandard) {
            JSONObject raceObj = race.exportStandardRace();
            standardRacesArrayToExport.add(raceObj);
        }
        json.put("StandardRaces", standardRacesArrayToExport);
        JSONArray eliminationRacesArrayToExport = new JSONArray();
        for (EliminationRace race : this.racesElimination) {
            JSONObject raceObj = race.exportEliminationRace();
            eliminationRacesArrayToExport.add(raceObj);
        }
        json.put("EliminationRaces", eliminationRacesArrayToExport);

        return json;
    }

    public static Tournament importTournament(JSONObject obj) {
        String name = (String) obj.get(NAME);

        Tournament tournament = new Tournament(name);

        JSONArray standardRacesArray = (JSONArray) obj.get("StandardRaces");
        if (standardRacesArray != null && !standardRacesArray.isEmpty()) {
            for (Object raceObj : standardRacesArray) {
                if (raceObj instanceof JSONObject) {
                    StandardRace standardRace = StandardRace.importStandardRace((JSONObject) raceObj);
                    tournament.getRacesStandard().add(standardRace);
                }
            }
        }

        JSONArray eliminationRacesArray = (JSONArray) obj.get("EliminationRaces");
        if (eliminationRacesArray != null && !eliminationRacesArray.isEmpty()) {
            for (Object raceObj : eliminationRacesArray) {
                if (raceObj instanceof JSONObject) {
                    EliminationRace eliminationRace = EliminationRace.importEliminationRace((JSONObject) raceObj);
                    tournament.getRacesElimination().add(eliminationRace);
                }
            }
        }

        return tournament;
    }
}

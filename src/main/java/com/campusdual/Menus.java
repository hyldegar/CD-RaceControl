package com.campusdual;

import com.campusdual.util.Utils;
import com.campusdual.util.Input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Menus {

    private static JSONArray raceControl = new JSONArray();
    private static JSONArray carsJSON = new JSONArray();
    private static JSONArray garagesJSON = new JSONArray();
    private static JSONArray standardRJSON = new JSONArray();
    private static JSONArray eliminationRJSON = new JSONArray();
    private static JSONArray tournamentsJSON = new JSONArray();

    private static List<Car> cars = new ArrayList<>();
    private static List<Garage> garages = new ArrayList<>();
    private static List<StandardRace> standardRaces = new ArrayList<>();
    private static List<EliminationRace> eliminationRaces = new ArrayList<>();
    private static List<Tournament> tournaments = new ArrayList<>();

    public void ShowMainMenu() {

        loadState();
        rechargeCarsInGarages();
        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Gestión Race Control:");
            System.out.println("1. Gestionar Coches y Escuderias");
            System.out.println("2. Gestionar Carreras");
            System.out.println("3. Gestionar Torneos");
            System.out.println("4. Simulacion");
            System.out.println("5. Listados");
            System.out.println("6. Guardar y salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    carAndGarageManagMenu();
                    break;
                case 2:
                    raceManagementMenu();
                    break;
                case 3:
                    tournamentManageMenu();
                    break;
                case 4:
                    simulation();
                    break;
                case 5:
                    lists();
                    break;
                case 6:
                    saveState();
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
    public static void carAndGarageManagMenu() {
        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Gestión de Coches y Grarajes:");
            System.out.println("1. Dar de Alta Coches");
            System.out.println("2. Dar de Alta Escuderias");
            System.out.println("3. Asignar Coches a Escuderias o cambiarlos de Escuderia");
            System.out.println("4. Dar de Baja Coches");
            System.out.println("5. Dar de Baja Escuderias");
            System.out.println("6. Volver atrás");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    cars.add(new Car());
                    break;
                case 2:
                    garages.add(new Garage());
                    break;
                case 3:
                    List<Garage> selectedGarages = Utils.showAndSelectFromList(garages, true);
                    List<Car> selectedCars = Utils.showAndSelectFromList(cars, true);

                    if (!selectedGarages.isEmpty() || !selectedCars.isEmpty()) {
                        Garage selectedGarage = selectedGarages.get(0);
                        Car selectedCar = selectedCars.get(0);

                        if (!selectedCar.getGarageName().isEmpty()) {
                            char selection = Input.character("Está seguro de cambiar el coche de Escudería?(Y/Any)");
                            if (selection == 'Y' || selection == 'y') {
                                selectedGarage.addCar(selectedCar);
                                selectedCar.setGarageName(selectedGarage.getName());
                                break;
                            } else {
                                break;
                            }
                        }
                        selectedGarage.addCar(selectedCar);
                        selectedCar.setGarageName(selectedGarage.getName());
                    } else {
                        System.out.println("No se seleccionaron coches o garajes disponibles.");
                    }
                    break;
                case 4:
                    List<Car> selectedCarsToRemove = Utils.showAndSelectFromList(cars, true);

                    if (!selectedCarsToRemove.isEmpty()) {
                        Car selectedCarToRemove = selectedCarsToRemove.get(0);
                        cars.remove(selectedCarToRemove);

                        for (Garage garage : garages) {
                            List<Car> garageCars = garage.getCars();
                            if (garageCars.contains(selectedCarToRemove)) {
                                garageCars.remove(selectedCarToRemove);
                                System.out.println("Coche eliminado del Escudería");
                                break;
                            }
                        }

                        System.out.println("Coche dado de baja correctamente");
                    } else {
                        System.out.println("No se seleccionó ningún coche para dar de baja.");
                    }
                    break;

                case 5:
                    List<Garage> selectedGaragesToRemove = Utils.showAndSelectFromList(garages, true);
                    if (!selectedGaragesToRemove.isEmpty()) {
                        Garage selectedGarageToRemove = selectedGaragesToRemove.get(0);
                        for (Car car : selectedGarageToRemove.getCars()) {
                            car.setGarageName("");
                        }
                        garages.remove(selectedGarageToRemove);
                        System.out.println("Escuderia Eliminada");
                    } else {
                        System.out.println("No se seleccionó ninguna escudería para dar de baja.");
                    }
                    break;
                case 6:
                    goback = true;
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void raceManagementMenu() {
        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Gestión de Carreras:");
            System.out.println("1. Dar de Alta Carreras Standar");
            System.out.println("2. Dar de Alta Carreras de Eliminación");
            System.out.println("3. Dar de Baja Carreras Estandar");
            System.out.println("4. Dar de Baja Carreras de Eliminación");
            System.out.println("5. Volver atrás");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    char selection = Input.character("Desea Personalizar la carrera? (y/n:other)");
                    if (selection == 'Y' || selection == 'y') {
                        String raceStName = Input.string("Introduzca el nombre de la carrera: ");
                        int duration = Input.integer("Introduzca la duración en horas: ");
                        standardRaces.add(new StandardRace(raceStName, duration));
                    } else {
                        standardRaces.add(new StandardRace());
                    }
                    break;
                case 2:
                    char select = Input.character("Desea Personalizar la carrera? (y/n:other)");
                    if (select == 'Y' || select == 'y') {
                        String raceElName = Input.string("Introduzca el nombre de la carrera: ");
                        int warmMinutes = Input.integer("Introduzca la duración en minutos del warmup: ");
                        int eliminationMinutes = Input.integer("Introduzaca el tiempo de eliminación, en minutos: ");
                        eliminationRaces.add(new EliminationRace(raceElName, warmMinutes, eliminationMinutes));
                    } else {
                        eliminationRaces.add(new EliminationRace());
                    }
                    break;
                case 3:
                    goback = true;
                    //Todo Dar baja de las carreras standar
                    return;
                case 4:
                    goback = true;
                    //Todo Dar baja de las carreras de eliminación
                    return;
                case 5:
                    goback = true;
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void tournamentManageMenu() {
        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Gestión de Torneos:");
            System.out.println("1. Torneo Estándar");
            System.out.println("2. Torneo Personalizado");
            System.out.println("3. Eliminar Torneo");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    String nameTournament = Input.string("Introduzca el nombre del Torneo: ");
                    tournaments.add(Tournament.createStandarTournament(nameTournament, standardRaces, eliminationRaces));
                    break;
                case 2:
                    String nameTournamentP = Input.string("Introduzca el nombre del Torneo: ");
                    int numberOfRaces = Input.integer("Cuantas carreras quiere añadir al Torneo?: ");
                    tournaments.add(Tournament.createPersonalizedTournament(nameTournamentP, standardRaces, eliminationRaces, numberOfRaces));
                    // Todo elegir cantidad de carreras de un tipo u otro,
                    // Todo incluso podemos personalizar con un listado y elegir cuando meterlas(mucho trabajo queda por este lado)
                    break;
                case 3:
                    // Dar de Baja Torneos
                    if (!tournaments.isEmpty()) {
                        System.out.println("Lista de Torneos Disponibles:");
                        Utils.showFromList(tournaments, false);

                        int tournamentIndex = Input.integer("Seleccione el índice del torneo que desea eliminar: ");
                        if (tournamentIndex >= 0 && tournamentIndex <= tournaments.size()) {
                            Tournament removedTournament = tournaments.remove(tournamentIndex-1);
                            System.out.println("Torneo '" + removedTournament.getName() + "' ha sido eliminado.");
                        } else {
                            System.out.println("Índice no válido. No se eliminó ningún torneo.");
                        }
                    } else {
                        System.out.println("No hay torneos disponibles para dar de baja.");
                    }
                    break;

                case 4:
                    goback = true;
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
    public static void simulation() {
        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Simulacion de Carreras y Torneos:");
            System.out.println("1. Carrera Estándar");
            System.out.println("2. Carrera Eliminación");
            System.out.println("3. Torneo");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    List<StandardRace> standar = Utils.showAndSelectFromList(standardRaces,true);
                    List<Garage> participatingGarages = Utils.showAndSelectFromList(garages,true,true);
                    standar.get(0).setParticipatingGarages(participatingGarages);
                    standar.get(0).startRace();
                    System.out.println("la carrera ha finalizado correctamente;");
                    List<Car> podiumst =  standar.get(0).getPodium();
                    for(Car car: podiumst){
                        System.out.println(car.toString());
                    }

                    break;
                case 2:
                    List<EliminationRace> elim = Utils.showAndSelectFromList(eliminationRaces,true);
                    List<Garage> participGarages = Utils.showAndSelectFromList(garages,true,true);
                    elim.get(0).setParticipatingGarages(participGarages);
                    elim.get(0).startRace();
                    System.out.println("la carrera ha finalizado correctamente;");
                    List<Car> podiumel =  elim.get(0).getPodium();
                    for(Car car: podiumel){
                        System.out.println(car.toString());
                    }
                    break;
                case 3:

                    List<Tournament> tournament = Utils.showAndSelectFromList(tournaments,true);
                    List<Garage> participatGarages = Utils.showAndSelectFromList(garages,true,true);
                    tournament.get(0).setParticipatingGarages(participatGarages);

                    tournament.get(0).tournamentStart();
                    System.out.println("Torneo Finalizado Correctamente");
                    goback = true;
                    return;
                case 4:
                    goback = true;
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
    public void lists() {

        boolean goback = false;
        while (!goback) {
            System.out.println("\nMenú de Listados:");
            System.out.println("1. Listado de Coches");
            System.out.println("2. Listado de Escuderías");
            System.out.println("3. Listado de Carreras Estándar");
            System.out.println("4. Listado de Carreras de Eliminación");
            System.out.println("5. Listado de Torneos");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    carsList();
                    break;
                case 2:
                    garagesList();
                    break;
                case 3:
                    standardRaceList();
                    break;
                case 4:
                    eliminationRacesList();
                    break;
                case 5:
                    tournamentList();
                    break;
                case 6:
                    goback = true;
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    //Data Management
    public static void exportJSONToFile(JSONArray obj) {
        try (FileWriter w = new FileWriter("RaceControl.json")) {
            w.write(obj.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONArray importFromJSONFile(String filename) {
        try (FileReader r = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(r);
        } catch (Exception e) {
            return null;
        }
    }

    public static void saveState() {
        // Limpiar las listas actuales antes de guardar los datos nuevos
        carsJSON.clear();
        garagesJSON.clear();
        standardRJSON.clear();
        eliminationRJSON.clear();
        tournamentsJSON.clear();

        //Cargamos las listas JSON con los datos
        for (Car car : cars) {
            carsJSON.add(car.exportCar());
        }
        for (Garage garage : garages) {
            garagesJSON.add(garage.exportGarage());
        }
        for (StandardRace standardRace : standardRaces) {
            standardRJSON.add(standardRace.exportStandardRace());
        }
        for (EliminationRace eliminationRace : eliminationRaces) {
            eliminationRJSON.add(eliminationRace.exportEliminationRace());
        }
        for (Tournament tournament : tournaments) {
            tournamentsJSON.add(tournament.exportTournament());
        }
        raceControl.add(carsJSON);
        raceControl.add(garagesJSON);
        raceControl.add(standardRJSON);
        raceControl.add(eliminationRJSON);
        raceControl.add(tournamentsJSON);

        exportJSONToFile(raceControl);
    }

    public static void loadState() {

        JSONArray loadedData = importFromJSONFile("RaceControl.json");

        if (loadedData != null) {
            // Limpiar las listas actuales antes de cargar los datos
            cars.clear();
            garages.clear();
            standardRaces.clear();
            eliminationRaces.clear();
            tournaments.clear();

            // Cargar datos desde el archivo JSON
            carsJSON = (JSONArray) loadedData.get(0);
            garagesJSON = (JSONArray) loadedData.get(1);
            standardRJSON = (JSONArray) loadedData.get(2);
            eliminationRJSON = (JSONArray) loadedData.get(3);
            tournamentsJSON = (JSONArray) loadedData.get(4);

            // Recorrer y cargar los objetos desde los JSONArrays
            for (Object carObj : carsJSON) {
                Car car = Car.importCar((JSONObject) carObj);
                cars.add(car);
            }

            for (Object garageObj : garagesJSON) {
                Garage garage = Garage.importGarage((JSONObject) garageObj);
                garages.add(garage);
            }

            for (Object standardRaceObj : standardRJSON) {
                StandardRace standardRace = StandardRace.importStandardRace((JSONObject) standardRaceObj);
                standardRaces.add(standardRace);
            }

            for (Object eliminationRaceObj : eliminationRJSON) {
                EliminationRace eliminationRace = EliminationRace.importEliminationRace((JSONObject) eliminationRaceObj);
                eliminationRaces.add(eliminationRace);
            }

            for (Object tournamentObj : tournamentsJSON) {
                Tournament tournament = Tournament.importTournament((JSONObject) tournamentObj);
                tournaments.add(tournament);
            }
        } else {
            System.out.println("No se pudo cargar el estado desde el archivo JSON.");
        }
    }

    public static void carsList() {
        Utils.showFromList(cars, true);
    }

    public static void tournamentList(){
        Utils.showFromList(tournaments,true);
    }

    public static void standardRaceList(){
        Utils.showFromList(standardRaces,true);
    }
    public static void eliminationRacesList(){
        Utils.showFromList(eliminationRaces,true);
    }
    public static void garagesList(){
        Utils.showFromList(garages,true);
    }

    public static void rechargeCarsInGarages() {
        for (Car car : cars) {
            for (Garage garage : garages) {
                if (car.getGarageName().equals(garage.getName())) {
                    garage.addCar(car);
                }
            }

        }
    }
    

}

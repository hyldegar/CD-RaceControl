package com.campusdual;

import com.campusdual.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class RaceControl {

    public static void main(String[] args) {
        Menus menu = new Menus();
        menu.ShowMainMenu();
       /* List<Car> cars = new ArrayList<>();
        List<Garage> garages = new ArrayList<>();
        List<StandardRace> standardRaces = new ArrayList<>();
        List<EliminationRace> eliminationRaces = new ArrayList<>();
        List<Tournament> tournaments = new ArrayList<>();*/


        //Car c = new Car();
        //System.out.println(c);

/*
        Car c1 = new Car("seat", "Ibiza");
        System.out.println(c1);
        Car c2 = new Car("seat", "Leon");
        System.out.println(c2);
        Car c3 = new Car("seat", "Arosa");

        Car c4 = new Car("Ford", "mustang");
        Car c5 = new Car("Ford", "Scort");
        Car c6 = new Car("Ford", "Fiesta");
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        cars.add(c5);
        cars.add(c6);
*/
        /*for (int i=0; i<12;i++){
            c1.aleatorizing();
            c2.aleatorizing();
        }
        System.out.println("velocidad tras 12 min: "+ c1.getSpeedometer()+ " distancia "+ c1.getDistance());
        System.out.println("velocidad tras 12 min: "+ c2.getSpeedometer()+ " distancia "+ c2.getDistance());

        System.out.println(c1.compareTo(c2));
*/
        /*
        Garage g1 = new Garage("Mundo Champiñón");
        Garage g2 = new Garage("Mundo DK");
        g1.addCar(c1);
        g1.addCar(c2);
        g1.addCar(c3);
        g2.addCar(c4);
        g2.addCar(c5);
        g2.addCar(c6);
        System.out.println(g1);
        garages.add(g1);
        garages.add(g2);*/
        //Car selectedCar = Utils.showAndSelectFromList(cars, true).get(0);
        //Garage selectedGarage = Utils.showAndSelectFromList(garages, true).get(0);
        //selectedGarage.addCar(selectedCar);
        //selectedCar.setGarageName(selectedGarage.getName());
        //System.out.println(g1);


/*
       StandardRace r1 = new StandardRace();
       System.out.println(r1.toString());
       r1.getParticipatingGarages().add(g1);
       System.out.println("se añadió el garaje");
       r1.startRace();
       System.out.println("la carrera ha finalizado correctamente;");
       List<Car> podium =  r1.getPodium();
        for(Car car: podium){
            System.out.println(car.toString());
        }

*/

/*
        EliminationRace e1 = new EliminationRace();
        System.out.println(e1.toString());

        e1.getParticipatingGarages().add(g1);
        e1.getParticipatingGarages().add(g2);
        System.out.println("se añadió el garaje");
        e1.startRace();
        System.out.println("la carrera ha finalizado correctamente");
        List<Car> podium =  e1.getPodium();
        for(Car car: podium){
            System.out.println(car.toString());
        }
*/
        //StandardRace s1 = new StandardRace();
    }

}

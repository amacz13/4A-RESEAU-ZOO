package fr.ensim.nicoaxel.server;

import java.util.ArrayList;

public class UserAnimals {
    ArrayList<Animal> animals = new ArrayList<>();
    String user = "";
    String color = ".";

    public UserAnimals(String user, String color){
        this.user = user;
        this.color = color;
    }

    public void clear(){
        animals.clear();
    }

    public void setAnimals(ArrayList<Animal> animals){
        this.animals = animals;
    }

    public void addAnimal(Animal a){
        animals.add(a);
    }

}

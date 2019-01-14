package fr.ensim.nicoaxel.server;

import java.util.ArrayList;

public class UserAnimals {
    ArrayList<Animal> animals = new ArrayList<>();
    String user = "";

    public UserAnimals(String user){
        this.user = user;
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

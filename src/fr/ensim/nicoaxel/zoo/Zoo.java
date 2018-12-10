package fr.ensim.nicoaxel.zoo;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Animal> animals;
    private int sizeX, sizeY;

    public Zoo(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal a) {
        animals.add(a);
    }

    public void changeDestAnimal(int i){
        animals.get(i).choixDest(sizeX, sizeY);
    }

    public void move(int i){
        for(Animal a : animals){
            a.move();
        }
    }

}

package fr.ensim.nicoaxel.zoo;
import javafx.scene.canvas.GraphicsContext;

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
        animals.get(i).choiceDest();
    }

    public void move(){
        for(Animal a : animals){
            a.action();
        }
    }

    public void render(GraphicsContext gc){
        for(Animal a : animals){
            a.renderAnimal(gc);
        }
    }

    public void action(GraphicsContext gc){
        this.move();
        this.render(gc);
    }

}

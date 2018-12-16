package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.animals.Lion;
import fr.ensim.nicoaxel.zoo.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static fr.ensim.nicoaxel.zoo.types.Espece.*;

public final class Zoo {
    private static final Logger log = LogManager.getRootLogger();

    private List<Animal> animals;
    private List<Animal> babies;
    private List<Obstacle> obstacles;
    private int sizeX, sizeY;

    public Zoo(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        animals = new ArrayList<>();
        babies = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    public int nbAnimal(){
        return animals.size();
    }

    public void addAnimal(Animal a) {
        animals.add(a);
    }

    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    public boolean hasObstacle(int x, int y) {
        for (Obstacle o : obstacles) {
            if (o.x() == x && o.y() == y) return true;
        }
        return false;
    }

    public Espece hasAnimal(Animal animal) {
        for (Animal a : animals) {
            if (a.x() == animal.x() && a.y() == animal.y() && !animal.equals(a) && a.sexDifferent(animal)) return a.getEspece();
        }
        return null;
    }


    public void move() {
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).action();
        }
        /*for(Animal a : animals){
            a.action();
        }*/
    }

    public void render(GraphicsContext gc) {
        for (Animal a : animals) {
            a.renderAnimal(gc);
        }
    }

    public void action(GraphicsContext gc) {
        this.move();
        this.render(gc);
    }

    public void generateBaby(int x, int y, Espece esp) {
       /* Animal a = null;
        switch (esp){
            case LION:
                a = new Lion(x, y);
                break;
            default :
                break;
        }
        addAnimal(a);*/

        for (Animal a : babies) {
            if (a.x() == x && a.y() == y) {
                return;
            }
        }

        log.info("Bébé créé");
        Animal a = new Lion(x, y);
        this.addAnimal(a);
        babies.add(a);
    }

}

package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.Obstacle;
import fr.ensim.nicoaxel.server.animals.Elephant;
import fr.ensim.nicoaxel.server.animals.Fox;
import fr.ensim.nicoaxel.server.animals.Lion;
import fr.ensim.nicoaxel.server.animals.Zebra;
import fr.ensim.nicoaxel.server.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class Zoo {
    private static final Logger log = LogManager.getRootLogger();

    public List<Animal> animals;
    private List<Animal> babies;
    private List<Obstacle> obstacles;
    private int sizeX, sizeY;

    public Zoo(int sizeX, int sizeY) {
        this.sizeX = Main.sizeX;
        this.sizeY = Main.sizeY;
        animals = new ArrayList<>();
        babies = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    public List<Obstacle> getObstacles(){
        return obstacles;
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
            log.debug("Moving animal "+i);
            animals.get(i).action();
        }
    }

    public void render(GraphicsContext gc) {
        for (Animal a : animals) {
            log.debug("Rendering animal "+a.getEspece().toString());
            a.renderAnimal(gc);
        }
    }

    public void action(GraphicsContext gc) {
        this.move();
        this.render(gc);
    }

    public void generateBaby(int x, int y, Espece esp) {
        for (Animal a : babies) {
            if (a.x() == x && a.y() == y) {
                return;
            }
        }

        log.debug("Bébé créé");
        Animal a;
        switch (esp) {
            case LION:
                a = new Lion("", x, y);
                break;
            case ELEPHANT:
                a = new Elephant("", x,y);
                break;
            case FOX:
                a = new Fox("", x,y);
                break;
            case ZEBRA:
                a = new Zebra("", x,y);
                break;
            default:
                a = new Fox("", x,y);
                break;
        }
        this.addAnimal(a);
        babies.add(a);
    }

    public String toSend(){
        return "[Zoo]"+sizeX+" "+sizeY;
    }

}

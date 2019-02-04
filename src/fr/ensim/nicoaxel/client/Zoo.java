package fr.ensim.nicoaxel.client;

import fr.ensim.nicoaxel.client.animals.Elephant;
import fr.ensim.nicoaxel.client.animals.Fox;
import fr.ensim.nicoaxel.client.animals.Lion;
import fr.ensim.nicoaxel.client.animals.Zebra;
import fr.ensim.nicoaxel.client.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static sun.audio.AudioPlayer.player;

public final class Zoo {
    private static final Logger log = LogManager.getRootLogger();
    private static final double LIMIT_DEATH = 0.05;

    public List<Animal> animals;
    private List<Animal> babies;
    public List<Animal> otherAnimals;
    private List<Obstacle> obstacles;
    private List<Corpse> corpse;
    private List<Grass> herbs;
    private int sizeX, sizeY;

    public Zoo(int sizeX, int sizeY) {
        this.sizeX = Main.sizeX;
        this.sizeY = Main.sizeY;
        animals = new ArrayList<>();
        babies = new ArrayList<>();
        corpse = new ArrayList<>();
        obstacles = new ArrayList<>();
        otherAnimals = new ArrayList<>();
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

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Corpse> getCorpse() {
        return corpse;
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
        for (Animal a : otherAnimals) {
            log.debug("Rendering animal "+a.getEspece().toString());
            a.renderAnimal(gc);
        }
    }

    /*public void renderOther(GraphicsContext gc) {
        for (Animal a : otherAnimals) {
            log.debug("Rendering other animal "+a.getEspece().toString());
            a.renderAnimal(gc);
        }
    }*/

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

        Thread t = new Thread(new Sound(Sound.BABY));
        t.start();
        log.debug("Bébé créé");
        Animal a;
        switch (esp) {
            case LION:
                a = new Lion(x, y);
                break;
            case ELEPHANT:
                a = new Elephant(x,y);
                break;
            case FOX:
                a = new Fox(x,y);
                break;
            case ZEBRA:
                a = new Zebra(x,y);
                break;
            default:
                a = new Fox(x,y);
                break;
        }
        this.addAnimal(a);
        babies.add(a);
    }

    public void kill(Animal animal) {
        corpse.add(new Corpse(animal.x(), animal.y()));
        animals.remove(animal);

    }

    public boolean hasCorpseNear(int x, int y) {
        for(Corpse c : corpse){
            if(Math.abs(c.getX()-x) <= 1 && Math.abs(c.getY()-y) <= 1 && Math.random()<LIMIT_DEATH){
                return true;
            }
        }
        return false;
    }

    public void addCorpse(int x, int y, int ttl, String color) {
        if(!inCorpseList(x, y)){
            corpse.add(new Corpse(x, y, ttl, color));
        }
    }

    private boolean inCorpseList(int x, int y) {
        for(Corpse c : corpse){
            if(c.getX() == x && c.getY() == y){
                return true;
            }
        }
        return false;
    }
}

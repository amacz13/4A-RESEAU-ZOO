package fr.ensim.nicoaxel.client;

import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public abstract class Animal {

    private static final Logger log = LogManager.getRootLogger();
    private static final double LIMIT = 8;

    private int speed;       //speed : lower is faster
    private int countSpeed;  //countSpeed is a counter for move animal
    private int immobile = -1;
    private int oldx, oldy;
    private int x, y;
    private int destX, destY;
    private char sex;
    private Espece espece;
    private Image image;
    private int reproductionTime;
    private int reproductionAge;
    private int age = 0;


    public Animal(int x, int y, Espece esp, int speed, int repro, int ageRepro) {

        this.x = x;
        this.y = y;
        destX = x;
        destY = y;

        espece = esp;
        this.speed = speed;
        reproductionTime = repro;
        reproductionAge = ageRepro;

        sex = new Random().nextBoolean() ? 'm' : 'f';
        image = setImage(espece);

    }


    public Animal(int x, int y, Espece esp, char sex, int speed, int repro, int ageRepro) {
        this(x, y, esp, speed, repro, ageRepro);
        this.sex = sex;
    }

    public Animal(int x, int y, Espece esp, char sex, int speed, int repro, int ageRepro, int destX, int destY) {
        this(x, y, esp, sex, speed, repro, ageRepro);
        this.destY = destY;
        this.destX = destX;
    }

    Espece getEspece() {
        return espece;
    }

    void choiceDest() {
        destX = (int) (Math.random() * (Main.sizeX - 3));
        destY = (int) (Math.random() * (Main.sizeY - 3));
        log.debug("New destination : " + destX + " / " + destY);
    }

    void action() {
        age++;
        log.debug("Animal grew up !");
        if (immobile > 0) {
            immobile--;
        } else if (immobile < 0) {
            this.oldx = x;
            this.oldy = y;
            this.move();

            if (Math.random() * 100 < LIMIT) {
                this.choiceDest();
            }
        } else if (immobile == 0 && age > reproductionAge) {
            immobile--;
            choiceDest();
            countSpeed = 0;

            if (espece.equals(Main.zoo.hasAnimal(this))) {
                Main.zoo.generateBaby(x, y, espece);
            } else {
                log.debug("Coupling without baby");
            }
        }

    }

    public void move() {
        log.debug("Animal starting moving !");
        if (countSpeed > 0) {
            countSpeed--;
        }
        if (countSpeed == 0) {
            countSpeed = speed;

            int difX = x - destX;
            int difY = y - destY;

            if (difX > difY) {

                if (difX < 0) {
                    x++;
                } else if (difX > 0) {
                    x--;
                } else if (difX == 0) {
                    log.debug("Arrived in en X");
                }
            } else {
                if (difY < 0) {
                    y++;
                } else if (difY > 0) {
                    y--;
                } else if (difY == 0) {
                    log.debug("Arrived in Y");
                }
            }


            log.debug("Obstacle ? ");
            if (Main.zoo.hasObstacle(x, y)) {
                log.debug("Obstacle detected !");
                x = oldx;
                y = oldy;
                choiceDest();
                countSpeed = 0;
                //move();
            }

            log.debug("Girlfriend ?");
            if (age > reproductionAge && espece.equals(Main.zoo.hasAnimal(this))) {
                immobile = reproductionTime;
                log.debug("Coupling start");
            }
        }
        log.debug("x : " + x + " / y : " + y);
    }

    private Image setImage(Espece esp) {
        switch (esp) {
            case LION:
                return new Image("/animals/lion.png");
            case ZEBRA:
                return new Image("/animals/zebra.png");
            case ELEPHANT:
                return new Image("/animals/elephant.png");
            case FOX:
                return new Image("/animals/fox.png");
            default:
                return new Image("/unknown.png");
        }
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    private char getSex() {
        return sex;
    }

    boolean sexDifferent(Animal a) {
        return !(a.getSex() == sex);
    }


    void renderAnimal(GraphicsContext gc) {
        Image grass = new Image("/tiles/grass.png");
        gc.drawImage(grass, oldx * 16, oldy * 16);
        gc.drawImage(image, x * 16, y * 16);
    }

    public String toSend(){
        return "[Animal]"+espece+" "+x+" "+y;
    }

}

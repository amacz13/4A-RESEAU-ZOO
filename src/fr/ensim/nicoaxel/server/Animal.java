package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.ImageLoader;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public abstract class Animal {

    private static final Logger log = LogManager.getRootLogger();
    private static final double LIMIT = 25;

    private String id = "";

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


    public Animal(String id, int x, int y, Espece esp, int speed, int repro, int ageRepro) {

        this.id = id;

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


    public Animal(String id, int x, int y, Espece esp, char sex, int speed, int repro, int ageRepro) {
        this(id, x, y, esp, speed, repro, ageRepro);
        this.sex = sex;
    }

    public Animal(String id, int x, int y, Espece esp, char sex, int speed, int repro, int ageRepro, int destX, int destY) {
        this(id, x, y, esp, sex, speed, repro, ageRepro);
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
                return ImageLoader.lion;
            case ZEBRA:
                return ImageLoader.zebra;
            case ELEPHANT:
                return ImageLoader.elephant;
            case FOX:
                return ImageLoader.fox;
            default:
                return ImageLoader.unknown;
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

    public Animal(){
        super();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    void renderAnimal(GraphicsContext gc) {
        Image grass = ImageLoader.grass;
        gc.drawImage(grass, oldx * 16, oldy * 16);
        gc.drawImage(image, x * 16, y * 16);
    }


   public String toSend(){
       return "[Animal] "+espece+" "+x+" "+y;
   }

}

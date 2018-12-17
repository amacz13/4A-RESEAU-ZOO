package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.ObjectType;
import fr.ensim.nicoaxel.zoo.utils.Coordinates;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {
    private static final Logger log = LogManager.getRootLogger();

    public static final int NB_OBJECTS = 60;

    public ObjectType type;
    private int x, y;
    public Image img;

    public static void generateLake(GraphicsContext gc, int x, int y, Zoo zoo){
        for (int i = 0; i < 6; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
            switch (randomNum) {
                case 0:
                    x++;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    x++;
                    y++;
                    break;
                case 3:
                    x--;
                    y--;
                    break;
                case 4:
                    x--;
                    break;
                case 5:
                    y--;
                    break;
            }
            zoo.addObstacle(new Obstacle(ObjectType.WATER,x,y,gc));
        }
    }

    public static void generateObstacles(Zoo zoo,GraphicsContext gc){

        for (int i = 0; i < NB_OBJECTS; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            Coordinates c = Coordinates.generateCoordinate(zoo);
            switch (randomNum) {
                case 0:
                    log.info("Generated Stone at x:"+c.x+" y:"+c.y);
                    zoo.addObstacle(new Obstacle(ObjectType.STONE,c.x,c.y,gc));
                    break;
                case 1:
                    log.info("Generated Lake at x:"+c.x+" y:"+c.y);
                    generateLake(gc,c.x,c.y,zoo);
                    //zoo.addObstacle(new Obstacle(ObjectType.WATER,c.x,c.y,gc));
                    break;
                case 2:
                    log.info("Generated Wood at x:"+c.x+" y:"+c.y);
                    zoo.addObstacle(new Obstacle(ObjectType.WOOD,c.x,c.y,gc));
                    break;
            }
        }
    }

    public int x(){return x;}
    public int y(){return y;}

    private static Image loadImage(ObjectType t){
        Image i;
        switch (t) {
            case WOOD:
                i = new Image("/objects/wood.png");
                break;
            case STONE:
                i = new Image("/objects/stone.png");
                break;
            case WATER:
                i = new Image("/objects/water.png");
                break;
            default:
                i = new Image("/unknown.png");
        }
        return i;
    }

    public Obstacle(ObjectType t, int a, int b, GraphicsContext gc) {
        this.type = t;
        this.x = a;
        this.y = b;
        this.img = loadImage(this.type);
        this.renderObject(gc);
    }

    public void renderObject(GraphicsContext gc){
        gc.drawImage(img, x * 16, y*16);
    }

}

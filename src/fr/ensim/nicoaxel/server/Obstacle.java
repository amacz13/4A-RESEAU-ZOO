package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.zoo.types.ObjectType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {
    private static final Logger log = LogManager.getRootLogger();

    public static final int NB_OBJECTS = 150;

    public ObjectType type;
    private int x, y;

    public static void generateLake(int x, int y, Zoo zoo){
        for (int i = 0; i < 16; i++) {
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
            zoo.addObstacle(new Obstacle(ObjectType.WATER,x,y));
        }
    }

    public static void generateObstacles(Zoo zoo){

        for (int i = 0; i < NB_OBJECTS; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
            Coordinates c = Coordinates.generateCoordinate(zoo);
            switch (randomNum) {
                case 0:
                case 1:
                case 2:
                case 3:
                    log.debug("Generated Stone at x:"+c.x+" y:"+c.y);
                    zoo.addObstacle(new Obstacle(ObjectType.STONE,c.x,c.y));
                    break;
                case 4:
                case 5:
                    log.debug("Generated Lake at x:"+c.x+" y:"+c.y);
                    generateLake(c.x,c.y,zoo);
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    log.debug("Generated Wood at x:"+c.x+" y:"+c.y);
                    zoo.addObstacle(new Obstacle(ObjectType.WOOD,c.x,c.y));
                    break;
            }
        }
    }

    public int x(){return x;}
    public int y(){return y;}



    public Obstacle(ObjectType t, int a, int b) {
        this.type = t;
        this.x = a;
        this.y = b;
    }


    public String toSend(){
        return "[Obstacle]"+type+" "+x+" "+y;
    }


}

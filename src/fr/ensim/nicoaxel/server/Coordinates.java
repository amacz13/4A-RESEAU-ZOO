package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.Zoo;

import java.util.concurrent.ThreadLocalRandom;

public class Coordinates {
    public int x;
    public int y;

    public static Coordinates generateCoordinate(Zoo z) {
        int a , b;
        do {
            a = ThreadLocalRandom.current().nextInt(0, Main.sizeX + 1);
            b = ThreadLocalRandom.current().nextInt(0, Main.sizeY + 1);
        } while (z.hasObstacle(a,b));
        return new Coordinates(z,a,b);
    }

    public Coordinates(Zoo z, int a, int b) {
        x = a;
        y = b;
    }
}

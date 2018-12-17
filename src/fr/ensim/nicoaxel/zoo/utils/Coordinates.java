package fr.ensim.nicoaxel.zoo.utils;

import fr.ensim.nicoaxel.zoo.Zoo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Coordinates {
    public int x;
    public int y;

    public static Coordinates generateCoordinate(Zoo z) {
        int a = ThreadLocalRandom.current().nextInt(0, 45 + 1);
        int b = ThreadLocalRandom.current().nextInt(0, 45 + 1);

        do {
            a = ThreadLocalRandom.current().nextInt(0, 45 + 1);
            b = ThreadLocalRandom.current().nextInt(0, 45 + 1);
        } while (z.hasObstacle(a,b));
        return new Coordinates(z,a,b);
    }

    public Coordinates(Zoo z, int a, int b) {
        x = a;
        y = b;
    }
}

package fr.ensim.nicoaxel.server.animals;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import fr.ensim.nicoaxel.server.Coordinates;

public class Zebra extends Animal {

    public Zebra(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.ZEBRA, 1,5,20);
    }

    public Zebra(int x, int y) {
        super(x, y, Espece.ZEBRA,1, 5,20 );
    }
    public Zebra(int x, int y, char sex) {
        super(x, y, Espece.ZEBRA, sex, 1, 5, 20);
    }
    public Zebra(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.ZEBRA, sex, 1,5,20, destX, destY);
    }
}

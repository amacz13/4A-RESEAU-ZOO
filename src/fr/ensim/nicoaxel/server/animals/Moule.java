package fr.ensim.nicoaxel.server.animals;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import fr.ensim.nicoaxel.server.Coordinates;

public class Moule extends Animal {

    public Moule(){
        super();
        //super("", Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.MOULE, 1,5,20);
    }

    public Moule(String id, int x, int y) {
        super(id, x, y, Espece.MOULE,1, 5,20 );
    }
    public Moule(String id, int x, int y, char sex) {
        super(id, x, y, Espece.MOULE, sex, 1, 5, 20);
    }
    public Moule(String id, int x, int y, char sex, int destX, int destY) {
        super(id, x, y, Espece.MOULE, sex, 1,5,20, destX, destY);
    }
}

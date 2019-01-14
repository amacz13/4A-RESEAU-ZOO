package fr.ensim.nicoaxel.server.animals;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import fr.ensim.nicoaxel.server.Coordinates;

public class Elephant extends Animal {

    public Elephant(){
        super();
        //super("", Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.ELEPHANT, 10,15,150);
    }

    public Elephant(String id, int x, int y) {
        super(id, x, y, Espece.ELEPHANT,10, 15,150 );
    }
    public Elephant(String id, int x, int y, char sex) {
        super(id, x, y, Espece.ELEPHANT, sex, 10, 15,150);
    }
    public Elephant(String id, int x, int y, char sex, int destX, int destY) {
        super(id, x, y, Espece.ELEPHANT, sex, 10,15,150, destX, destY);
    }
}
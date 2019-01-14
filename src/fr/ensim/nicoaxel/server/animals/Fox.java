package fr.ensim.nicoaxel.server.animals;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import fr.ensim.nicoaxel.server.Coordinates;

public class Fox extends Animal {

    public Fox(){
        super();
        //super("", Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.FOX, 1,5,5);
    }

    public Fox(String id, int x, int y) {
        super(id, x, y, Espece.FOX,1, 5,5 );
    }
    public Fox(String id, int x, int y, char sex) {
        super(id, x, y, Espece.FOX, sex, 1, 5,5);
    }
    public Fox(String id, int x, int y, char sex, int destX, int destY) {
        super(id, x, y, Espece.FOX, sex, 1,5,5, destX, destY);
    }
}

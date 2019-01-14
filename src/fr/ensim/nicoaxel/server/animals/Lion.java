package fr.ensim.nicoaxel.server.animals;

import fr.ensim.nicoaxel.server.Animal;
import fr.ensim.nicoaxel.server.Main;
import fr.ensim.nicoaxel.server.types.Espece;
import fr.ensim.nicoaxel.server.Coordinates;

public class Lion extends Animal {

    public Lion(){
        super();
        //super("", Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.LION, 1,5, 30);
    }

    public Lion(String id, int x, int y) {
        super(id, x, y, Espece.LION,1, 5,30 );
    }
    public Lion(String id, int x, int y, char sex) {
        super(id, x, y, Espece.LION, sex, 1, 5, 30);
    }
    public Lion(String id, int x, int y, char sex, int destX, int destY) {
        super(id, x, y, Espece.LION, sex, 1,5,30, destX, destY);
    }
}

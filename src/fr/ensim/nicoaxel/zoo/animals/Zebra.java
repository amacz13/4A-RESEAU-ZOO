package fr.ensim.nicoaxel.zoo.animals;

import fr.ensim.nicoaxel.zoo.Animal;
import fr.ensim.nicoaxel.zoo.Main;
import fr.ensim.nicoaxel.zoo.types.Espece;
import fr.ensim.nicoaxel.zoo.utils.Coordinates;

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

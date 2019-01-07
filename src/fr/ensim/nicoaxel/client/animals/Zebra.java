package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;
import fr.ensim.nicoaxel.client.Main;

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

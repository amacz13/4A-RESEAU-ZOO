package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;
import fr.ensim.nicoaxel.client.Main;

public class Elephant extends Animal {

    public Elephant(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.ELEPHANT, 10,15,150);
    }

    public Elephant(int x, int y) {
        super(x, y, Espece.ELEPHANT,10, 15,150 );
    }
    public Elephant(int x, int y, char sex) {
        super(x, y, Espece.ELEPHANT, sex, 10, 15,150);
    }
    public Elephant(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.ELEPHANT, sex, 10,15,150, destX, destY);
    }
}
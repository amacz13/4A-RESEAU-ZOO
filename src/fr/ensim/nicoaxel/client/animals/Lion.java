package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;
import fr.ensim.nicoaxel.client.Main;

public class Lion extends Animal {

    public Lion(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.LION, 1,5, 30);
    }

    public Lion(int x, int y) {
        super(x, y, Espece.LION,1, 5,30 );
    }
    public Lion(int x, int y, char sex) {
        super(x, y, Espece.LION, sex, 1, 5, 30);
    }
    public Lion(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.LION, sex, 1,5,30, destX, destY);
    }
}

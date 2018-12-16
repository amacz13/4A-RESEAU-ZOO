package fr.ensim.nicoaxel.zoo.animals;

import fr.ensim.nicoaxel.zoo.Animal;
import fr.ensim.nicoaxel.zoo.Main;
import fr.ensim.nicoaxel.zoo.types.Espece;
import fr.ensim.nicoaxel.zoo.utils.Coordinates;

public class Lion extends Animal {

    public Lion(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.LION, 1,5);
    }

    public Lion(int x, int y) {
        super(x, y, Espece.LION,1, 5 );
    }
    public Lion(int x, int y, char sex) {
        super(x, y, Espece.LION, sex, 1, 5);
    }
    public Lion(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.LION, sex, 1,5, destX, destY);
    }
}

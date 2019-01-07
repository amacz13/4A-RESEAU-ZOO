package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;
import fr.ensim.nicoaxel.client.Main;

public class Fox extends Animal {

    public Fox(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.FOX, 1,5,5);
    }

    public Fox(int x, int y) {
        super(x, y, Espece.FOX,1, 5,5 );
    }
    public Fox(int x, int y, char sex) {
        super(x, y, Espece.FOX, sex, 1, 5,5);
    }
    public Fox(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.FOX, sex, 1,5,5, destX, destY);
    }
}

package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.Main;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;

public class Moule extends Animal {

    public Moule(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.MOULE, 7,2,5, "");
    }

    public Moule(int x, int y) {
        super(x, y, Espece.MOULE,7, 2,5, "" );
        NB_BABY = 7;
    }
    public Moule(int x, int y, String color) {
        super(x, y, Espece.MOULE,7, 2,5, color );
        NB_BABY = 7;
    }
    public Moule(int x, int y, char sex, String color) {
        super(x, y, Espece.MOULE, sex, 7, 2, 5, color);
        NB_BABY = 7;
    }
    public Moule(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.MOULE, sex, 7,2,5, destX, destY);
        NB_BABY = 7;
    }
}

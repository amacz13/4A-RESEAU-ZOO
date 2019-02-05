package fr.ensim.nicoaxel.client.animals;

import fr.ensim.nicoaxel.client.Animal;
import fr.ensim.nicoaxel.client.Main;
import fr.ensim.nicoaxel.client.types.Espece;
import fr.ensim.nicoaxel.client.utils.Coordinates;

public class Unicorn extends Animal {

    public Unicorn(){
        super(Coordinates.generateCoordinate(Main.zoo).x, Coordinates.generateCoordinate(Main.zoo).y, Espece.UNICORN, 1,2,5, "");
    }

    public Unicorn(int x, int y) {
        super(x, y, Espece.UNICORN,1, 2,5, "" );
    }
    public Unicorn(int x, int y, String color) {
        super(x, y, Espece.UNICORN,1, 2,5, color );
    }
    public Unicorn(int x, int y, char sex, String color) {
        super(x, y, Espece.UNICORN, sex, 1, 2, 5, color);
    }
    public Unicorn(int x, int y, char sex, int destX, int destY) {
        super(x, y, Espece.UNICORN, sex, 1,2,5, destX, destY);
    }
}

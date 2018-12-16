package fr.ensim.nicoaxel.zoo.animals;

import fr.ensim.nicoaxel.zoo.Animal;
import fr.ensim.nicoaxel.zoo.types.Espece;

public class Lion extends Animal {

    public Lion(int x, int y) {
        super(x, y, Espece.LION, 'm', 1);
    }
}

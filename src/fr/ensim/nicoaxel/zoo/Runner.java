package fr.ensim.nicoaxel.zoo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Runner implements Runnable{

    private int c = 0;
    private GraphicsContext gc;
    private Zoo zoo;

    Runner(GraphicsContext g, Zoo z) {
        zoo = z;
        gc = g;
    }


    @Override
    public void run() {
        c++;
        //TODO log4j
        System.out.println("Hi "+c+" ("+c%30+")");

        //Tout le carnage qui suit va prochaine degager, tout partira dans la classe zoo, afin d'avoir un runner propre avec une seule méthode :)
        zoo.getAnimal(0).move();
        zoo.getAnimal(0).renderAnimal(gc);

        //Ca ca partira dans la classe animal
        if(c%30 == 3){
            zoo.getAnimal(0).choiceDest(40,40);
        }

    }
}

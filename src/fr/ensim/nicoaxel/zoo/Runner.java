package fr.ensim.nicoaxel.zoo;

import javafx.scene.canvas.GraphicsContext;

public class Runner implements Runnable{

    private int c = 0;
    private GraphicsContext gc;

    Runner(GraphicsContext g) {
        gc = g;
    }


    @Override
    public void run() {
        System.out.println("Hi "+c);
        Animal a = new Animal();
        //a.renderAnimal(gc);
    }
}

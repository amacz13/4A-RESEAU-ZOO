package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.ObjectType;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner implements Runnable{

    private static final Logger log = LogManager.getRootLogger();

    private int c = 0;
    private GraphicsContext gc;
    private Zoo zoo;

    Runner(GraphicsContext g, Zoo z) {
        zoo = z;
        gc = g;
    }


    @Override
    public void run() {
        log.info("Temps "+(c++)+" ("+Main.zoo.nbAnimal()+" animals)");

        zoo.action(gc);
    }
}

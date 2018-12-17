package fr.ensim.nicoaxel.zoo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer implements Runnable {

    GraphicsContext gc;
    Zoo zoo;

    Renderer (GraphicsContext g, Zoo z) {
        gc = g;
        zoo = z;
    }

    @Override
    public void run() {
        for (Animal a : zoo.animals) {
            Image image;
            switch (a.getEspece()) {
                case LION:
                    image = new Image("/animals/lion.png");
                    break;
                case ZEBRA:
                    image = new Image("/animals/zebra.png");
                    break;
                case ELEPHANT:
                    image = new Image("/animals/elephant.png");
                    break;
                case FOX:
                    image = new Image("/animals/fox.png");
                    break;
                default:
                    image = new Image("/unknown.png");
            }
            gc.drawImage(image, a.x() * 16, a.y() * 16);
        }
    }
}

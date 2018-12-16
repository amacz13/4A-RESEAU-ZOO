package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.ObjectType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Obstacle {

    public ObjectType type;
    public int x, y;
    public Image img;

    public static Image loadImage(ObjectType t){
        Image i;
        switch (t) {
            case WOOD:
                i = new Image("/unknown.png");
                break;
            case STONE:
                i = new Image("/objects/stone.png");
                break;
            case WATER:
                i = new Image("/unknown.png");
                break;
            default:
                i = new Image("/unknown.png");
        }
        return i;
    }

    public Obstacle(ObjectType t, int a, int b, GraphicsContext gc) {
        this.type = t;
        this.x = a;
        this.y = b;
        this.img = loadImage(this.type);
        this.renderObject(gc);
    }

    public void renderObject(GraphicsContext gc){
        gc.drawImage(img, x * 16, y*16);
    }

}

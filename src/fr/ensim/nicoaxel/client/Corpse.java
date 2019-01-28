package fr.ensim.nicoaxel.client;

import javafx.scene.image.Image;

public class Corpse {

    private int x, y;

    public Corpse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toSend(){
        return "[Corpse] "+x+" "+y;
    }


}

package fr.ensim.nicoaxel.client;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Corpse {

    private int x, y;
    private int timeToLife = 150;

    public Corpse(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Corpse(int x, int y, int ttl) {
        this.x = x;
        this.y = y;
        timeToLife = ttl;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toSend(){
        return "[Corpse] "+x+" "+y+" "+timeToLife;
    }


    public void draw(GraphicsContext gc) {
        if(timeToLife--<0){
            Main.removeCorpse(this);
        }else{
            gc.drawImage(ImageLoader.corpse, x * 16, y *16);
        }
    }
}

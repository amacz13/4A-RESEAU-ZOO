package fr.ensim.nicoaxel.client;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Corpse {

    private int x, y;
    private int timeToLife = 100;
    private String color =Main.color;

    public Corpse(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Corpse(int x, int y, int ttl, String color) {
        this.x = x;
        this.y = y;
        timeToLife = ttl;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toSend(){
        return "[Corpse] "+x+" "+y+" "+timeToLife+" "+color;
    }
    public String toSendServer(){
        return "[Corpse] "+x+" "+y+" "+timeToLife;
    }


    public void draw(GraphicsContext gc) {
        if(timeToLife--<0){
            Main.removeCorpse(this);
        }else{
            gc.drawImage(ImageLoader.modifImg(ImageLoader.corpse, color), x * 16, y *16);
        }
    }
}

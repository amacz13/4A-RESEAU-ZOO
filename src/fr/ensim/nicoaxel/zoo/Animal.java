package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animal {
    private int speed;       //speed : lower is faster
    private int countSpeed;  //countSpeed is a counter for move animal
    private int x, y;
    private int destX, destY;
    private char sexe;
    private Espece esp;

    public Animal() {
        speed = 5;
        x=5;
        y=5;
        sexe='m';
        esp = Espece.LION;
        choixDest();
    }

    public Animal(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public void choixDest(int maxX, int maxY) {
        destX = (int) (Math.random() * maxX);
        destY = (int) (Math.random() * maxY);
    }

    public void move() {
        if (countSpeed>0){
            countSpeed--;
        }
        if(countSpeed==0){
            countSpeed=speed;

            int difX = x - destX;
            int difY = y - destY;

            if(difX<0){
                x--;
            }else if(difX>0){
                x++;
            }

            if(difY<0){
                y--;
            }else if(difY>0){
                y++;
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void renderAnimal(GraphicsContext gc){
        String fileName;
        switch (esp) {
            case LION:
                fileName = "/animals/lion.png";
                break;
            default:
                fileName = "/animals/unknown.png";
        }
        Image image = new Image(fileName);
        gc.drawImage(image, x * 16, y*16);
    }

}

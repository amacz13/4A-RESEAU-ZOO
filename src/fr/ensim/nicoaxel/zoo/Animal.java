package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static fr.ensim.nicoaxel.zoo.types.Espece.LION;

public class Animal{
    private int speed;       //speed : lower is faster
    private int countSpeed;  //countSpeed is a counter for move animal
    private int x, y;
    private int destX, destY;
    private char sex;
    private Espece esp;

    public Animal() {
        this(5,5);
    }

    public Animal(int x, int y) {
        speed = 1;
        sex ='m';
        esp = LION;
        this.x = x;
        this.y = y;
        destX=x;
        destY=y;
    }

    public void choiceDest(int maxX, int maxY) {
        destX = (int) (Math.random() * (maxX-3)+2);
        destY = (int) (Math.random() * (maxY-3)+2);
        System.out.println("Nouvelle destination : "+destX+" / "+destY);
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
                x++;
            }else if(difX>0){
                x--;
            }else if(difX==0){
                //TODO log4j
                System.out.println("Arrivé en X");
            }

            if(difY<0){
                y++;
            }else if(difY>0){
                y--;
            }else if(difY==0){
                //TODO log4j
                System.out.println("Arrivé en Y");
            }
        }
System.out.println("x : "+x+" / y : "+y);
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

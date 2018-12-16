package fr.ensim.nicoaxel.zoo;

import fr.ensim.nicoaxel.zoo.types.Espece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import static fr.ensim.nicoaxel.zoo.types.Espece.LION;

public class Animal{

    private static final Logger log = LogManager.getRootLogger();
    private static final double LIMIT = 8;


    private int speed;       //speed : lower is faster
    private int countSpeed;  //countSpeed is a counter for move animal
    private int oldx, oldy;
    private int x, y;
    private int destX, destY;
    private char sex;
    private Espece espece;
    private Image image;


    public Animal(int x, int y) {
        this(x, y, LION, 'm', 1);
    }

    public Animal(int x, int y, Espece esp, char sex, int speed){

        this.x = x;
        this.y = y;
        destX=x;
        destY=y;

        espece = esp;
        this.speed = speed;
        this.sex = sex;
        image = setImage(espece);
    }

    public void choiceDest() {
        destX = (int) (Math.random() * (Main.sizeX-3));
        destY = (int) (Math.random() * (Main.sizeY-3));
        log.info("Nouvelle destination : "+destX+" / "+destY);
    }

    public void action(){
        this.oldx = x;
        this.oldy = y;
        this.move();


        if(Math.random()*100<LIMIT){
            this.choiceDest();
        }

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
                log.debug("Arrivé en X");
            }

            if(difY<0){
                y++;
            }else if(difY>0){
                y--;
            }else if(difY==0){
                log.debug("Arrivé en Y");
            }
        }
        log.debug("x : "+x+" / y : "+y);
    }

    public Image setImage(Espece esp){
        switch (esp) {
            case LION:
                return new Image("/animals/lion.png");
            default:
                return new Image("/animals/unknow.png");
        }
    }


    public void renderAnimal(GraphicsContext gc){
        Image grass = new Image("/tiles/grass16.png");
        gc.drawImage(grass, oldx * 16, oldy*16);
        gc.drawImage(image, x * 16, y*16);
    }

}

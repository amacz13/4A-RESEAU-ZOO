package fr.ensim.nicoaxel.zoo;

public class Animal {
    private int speed;       //speed : lower is faster
    private int countSpeed;  //countSpeed is a counter for move animal
    private int x, y;
    private int destX, destY;
    private char sex;

    public Animal() {
        speed = 2;
        countSpeed = speed ;
        x = 5;
        y = 5;
        sex = 'm';
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

}

package fr.ensim.nicoaxel.zoo;

public class Animal {
    private int vitesse;
    private int x, y;
    private int destX, destY;
    private char sexe;

    public Animal() {
        vitesse = 5;
        x=5;
        y=5;
        sexe='m';
        choixDest();
    }

    public Animal(int x, int y) {
        super();
        this.x=x;
        this.y=y;
    }

    private void choixDest() {
    }

    public void move() {


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

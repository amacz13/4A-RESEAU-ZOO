package fr.ensim.nicoaxel.zoo;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<Animal> animaux;
    private int tailleX, tailleY;

    public Zoo(int tailleX, int tailleY) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        animaux = new ArrayList<>();
    }

    public void addAnimal(Animal a) {
        animaux.add(a);
    }

    public void afficher() {
        System.out.println();
        boolean write = false;
        for (int x = 0; x < tailleX; x++) {
            for (int y = 0; y < tailleY; y++) {
                for (Animal a : animaux) {
                    if (x == a.getX() && y == a.getY()) {
                        System.out.print(" A ");
                        write = true;
                    }
                }
                if (!write) {
                    System.out.print(" - ");
                }
                write = false;
            }
            System.out.println("\n");
        }
    }

}

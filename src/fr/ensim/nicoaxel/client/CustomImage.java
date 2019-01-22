package fr.ensim.nicoaxel.client;

import javafx.scene.image.Image;

public class CustomImage extends Image implements Cloneable {

    public CustomImage(String url) {
        super(url);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

package fr.ensim.nicoaxel.client;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BackgroundSound implements Runnable {

    private String sound;
    private Player player;

    public BackgroundSound(String sound){
        this.sound = sound;
    }


    @Override
    public void run() {
        while (true) {
            try {
                player = new Player(new FileInputStream(sound));
                player.play();
                player.close();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

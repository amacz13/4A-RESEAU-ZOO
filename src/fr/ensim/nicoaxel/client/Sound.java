package fr.ensim.nicoaxel.client;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound implements Runnable {

    public static final String MUSIC = "Hang.mp3"; //For test : "music.mp3"
    public static final String DEATH = "death.mp3";
    public static final String DEATH_CORPSE = "death2.mp3";
    public static final String COUPLING = "coupling.mp3";
    public static final String BABY = "baby.mp3";

    private String sound;

    public Sound(String sound){
        this.sound = sound;
    }


    @Override
    public void run() {

        try {
            Player player = new Player(new FileInputStream(sound));
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

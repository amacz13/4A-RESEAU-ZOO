package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.Corpse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    private static final Logger log = LogManager.getRootLogger();
    public static Zoo zoo;

    public static int sizeX = 45, sizeY = 45;

    private static ArrayList<UserAnimals> listUserAnimal = new ArrayList<UserAnimals>();
    private static ArrayList<UserCorpse> listUserCorpse = new ArrayList<UserCorpse>();

    public static void main(String args[]) throws IOException {

        zoo = new Zoo(sizeX, sizeY);
        Obstacle.generateObstacles(zoo);

        ServerSocket ss = new ServerSocket(4321);
        log.info("Starting server...");
        log.info("Listening on port : 4321");
        do {
            Socket s = ss.accept();
            log.info("New connection from "+s.getInetAddress()+":"+s.getPort());

            Thread t = new Thread(new Service(s));
            t.start();
        } while (true);
    }



    public static ArrayList<UserAnimals> getlistUserAnimals(String user){
        ArrayList<UserAnimals> list = new ArrayList<>();
        for(UserAnimals ua : listUserAnimal){
            if(!ua.user.equals(user)){
                list.add(ua);
            }
        }
        return list;
    }

    public static ArrayList<UserCorpse> getlistUserCorpse(String user){
        ArrayList<UserCorpse> list = new ArrayList<>();
        for(UserCorpse ua : listUserCorpse){
            if(!ua.user.equals(user)){
                list.add(ua);
            }
        }
        return list;
    }


    public static ArrayList<Animal> getlistAnimals(String user){
        ArrayList<Animal> list = new ArrayList<>();
        for(UserAnimals ua : listUserAnimal){
            if(!ua.user.equals(user)){
                list.addAll(ua.animals);
            }
        }
        return list;
    }

    public static ArrayList<Corpse> getlistCorpse(String user){
        ArrayList<Corpse> list = new ArrayList<>();
        for(UserCorpse ua : listUserCorpse){
            if(!ua.user.equals(user)){
                list.addAll(ua.corpses);
            }
        }
        return list;
    }

    public static void removeAnimals(String user){
        ArrayList<Animal> list = new ArrayList<>();
        for(int i=0 ; i<listUserAnimal.size() ; i++){
            UserAnimals ua = listUserAnimal.get(i);
            if(ua.user.equals(user)){
                listUserAnimal.remove(ua);
            }
        }
    }
    public static void removeCorpse(String user){
        ArrayList<Animal> list = new ArrayList<>();
        for(int i=0 ; i<listUserCorpse.size() ; i++){
            UserCorpse ua = listUserCorpse.get(i);
            if(ua.user.equals(user)){
                listUserCorpse.remove(ua);
            }
        }
    }

    public static void addListAnimals(UserAnimals ua){
        listUserAnimal.add(ua);
    }
    public static void addListCorpse(UserCorpse ua){
        listUserCorpse.add(ua);
    }

    public static int getNbAnimals() {
        int nb = 0;
        for(UserAnimals ua : listUserAnimal){
            nb += ua.animals.size();
        }
        return nb;
    }
/*
    public UserAnimals getListAnimals(String user){
        for(UserAnimals ua : listUserAnimal) {
            if (ua.user.equals(user)) {
                return ua;
            }
        }
        return null;
    }
*/
}

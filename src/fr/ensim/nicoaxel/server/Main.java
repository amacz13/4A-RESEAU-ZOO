package fr.ensim.nicoaxel.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    private static final Logger log = LogManager.getRootLogger();
    public static Zoo zoo;

    public static int sizeX = 45, sizeY = 45;

    private static ArrayList<UserAnimals> listUserAnimal = new ArrayList<UserAnimals>();

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


    public static ArrayList<Animal> getlistAnimals(String user){
        ArrayList<Animal> list = new ArrayList<>();
        for(UserAnimals ua : listUserAnimal){
            if(!ua.user.equals(user)){
                list.addAll(ua.animals);
            }
        }
        return list;
    }

    public static ArrayList<Animal> removeAnimals(String user){
        ArrayList<Animal> list = new ArrayList<>();
        for(int i=0 ; i<listUserAnimal.size() ; i++){
            UserAnimals ua = listUserAnimal.get(i);
            if(ua.user.equals(user)){
                listUserAnimal.remove(ua);
            }
        }
        return list;
    }

    public static void addListAnimals(UserAnimals ua){
        listUserAnimal.add(ua);
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

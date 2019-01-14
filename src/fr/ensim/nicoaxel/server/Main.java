package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.server.Zoo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final Logger log = LogManager.getRootLogger();
    public static Zoo zoo;

    public static int sizeX = 45, sizeY = 45;

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

}

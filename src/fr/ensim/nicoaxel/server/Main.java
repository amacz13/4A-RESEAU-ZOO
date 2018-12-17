package fr.ensim.nicoaxel.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final Logger log = LogManager.getRootLogger();

    public static void main(String args[]) throws IOException {
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

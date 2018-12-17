package fr.ensim.nicoaxel.server;

import java.net.*;

public class Server {
    public static final int PORT = 1111;

    public static void main(String[] arguments) {
        try {
            // squelette de serveur
            ServerSocket socketAttente;
            socketAttente = new ServerSocket(PORT);
            System.out.println("Attente de connexions, serveur prêt");
            do {
                // établissement d’une connexion (attente bloquante)
                Socket s = socketAttente.accept();
                // la communication est désormais possible, création du service
                Thread t = new Thread(new Service(s));
                // on démarre l’exécution concurrente du service
                t.start();
                // encore!
            } while (true);
            // socketAttente.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
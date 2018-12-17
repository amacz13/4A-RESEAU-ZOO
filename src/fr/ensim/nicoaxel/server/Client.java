package fr.ensim.nicoaxel.server;

import java.net.*;
import java.io.*;

public class Client {
    public static final int PORT = 1111;

    public static void main(String[] arguments) {
        try {
            System.out.println("Je vais essayer de se connecter...");
            Socket service = new Socket("localhost", PORT);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
            pw.println(arguments[0]);
            pw.flush();
            BufferedReader bf = new BufferedReader(new InputStreamReader(service.getInputStream()));
            String message = bf.readLine();
            System.out.println("Je viens de recevoir le message : " + message);
            pw.close();
            bf.close();
            service.close();
        } catch (Exception e) {
            System.err.println("Erreur sérieuse : " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
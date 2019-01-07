package fr.ensim.nicoaxel.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class Service implements Runnable {
    private Socket maSocket;

    Service(Socket s) {
        maSocket = s;
    }

    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));
            String qui = bf.readLine();
            System.out.println(qui + " : vient de se connecter");
            Thread.sleep(500);

            String msg = "";
            PrintWriter pw;
            do{
                pw = new PrintWriter(new OutputStreamWriter(maSocket.getOutputStream()));
                pw.println(qui + " : "+ msg);

                pw.println("[Zoo]"+Main.sizeX+" "+Main.sizeY);
                pw.flush();

                for(Obstacle o : Main.zoo.getObstacles()){

                    pw.println(o.toSend());
                    pw.flush();
                }

            }while (!msg.equals("STOP"));
            pw.close();
            bf.close();
            maSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
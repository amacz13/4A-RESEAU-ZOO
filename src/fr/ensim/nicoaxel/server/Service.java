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
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(maSocket.getOutputStream()));

            String qui = bf.readLine();

            if(qui.equals("Axel")){
                System.out.println(qui + " vient manger");
            }else{
                System.out.println(qui + " : vient de se connecter");
            }

            Thread.sleep(500);

            String msg = "";

            pw.println(Main.zoo.toSend());

            for(Obstacle o : Main.zoo.getObstacles()){
                pw.println(o.toSend());
            }

            pw.println("STOP");
            pw.flush();


            do{
                pw.println("\uD83D\uDD95");
                pw.flush();
                /*System.out.println("Nouveau tour !");

                for(Animal a : Main.zoo.animals){
                    pw.println(a.toSend());
                }*/
                msg = bf.readLine();
                System.out.println(msg);
            }while (!msg.equals("STOP"));
            System.out.println(qui+" est partit manger");
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
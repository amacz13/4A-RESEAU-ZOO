package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.types.ObjectType;
import fr.ensim.nicoaxel.server.animals.Elephant;
import fr.ensim.nicoaxel.server.animals.Fox;
import fr.ensim.nicoaxel.server.animals.Lion;
import fr.ensim.nicoaxel.server.animals.Zebra;
import fr.ensim.nicoaxel.server.types.Espece;

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

            UserAnimals ua = new UserAnimals(qui);
            Main.addListAnimals(ua);

            do{
                msg = bf.readLine();
                //System.out.println("Received : "+msg);

                if(msg.equals("STOP")){
                    //System.out.println("ARRET !");
                    break;
                }
                if (msg.equals("STARTANIMALS")) {
                    System.out.println(qui + " : #DEBUT TOUR");
                    ua.clear();
                }
                if(msg.startsWith("[Animal]")){
                    System.out.println(qui + " : Animal : "+msg.split(" ")[1]);
                    Animal a = new Lion();
                    switch (msg.split(" ")[1]) {
                        case "LION":
                            a = new Lion();
                            a.setEspece(Espece.LION);
                            break;
                        case "ZEBRA":
                            a = new Zebra();
                            a.setEspece(Espece.ZEBRA);
                            break;
                        case "ELEPHANT":
                            a = new Elephant();
                            a.setEspece(Espece.ELEPHANT);
                            break;
                        case "FOX":
                            a = new Fox();
                            a.setEspece(Espece.FOX);
                            break;
                    }
                    a.setX(Integer.parseInt(msg.split(" ")[2]));
                    a.setY(Integer.parseInt(msg.split(" ")[3]));
                    ua.addAnimal(a);
                }

                if(msg.equals("STOPANIMALS")){
                    pw.println("STARTANIMALSOTHER");
                    pw.flush();
                    for (Animal a : Main.getlistAnimals(qui)) {
                        pw.println(a.toSend());
                        pw.flush();
                    }
                    pw.println("ENDANIMALSOTHER");
                    pw.flush();
                    System.out.println(qui +" :  #FIN TOUR");

                }
/*
                if(!msg.equals("STOP")) {
                    if (msg.equals("STARTANIMALS")) {
                        ua.clear();
                    }else{
                        while (!msg.equals("STOPANIMALS")) {
                            Animal a = new Lion();
                            switch (msg.split(" ")[1]) {
                                case "LION":
                                    a = new Lion();
                                    break;
                                case "ZEBRA":
                                    a = new Zebra();
                                    break;
                                case "ELEPHANT":
                                    a = new Elephant();
                                    break;
                                case "FOX":
                                    a = new Fox();
                                    break;
                            }
                            a.setX(Integer.parseInt(msg.split(" ")[2]));
                            a.setY(Integer.parseInt(msg.split(" ")[3]));
                            ua.addAnimal(a);
                            msg = bf.readLine();

                        }
                    }
                    for (Animal a : Main.getlistAnimals(qui)) {
                        pw.println(a.toSend());
                    }
                }
                System.out.println("#FIN TOUR");
*/
            }while (!msg.equals("STOP"));


            System.out.println(qui+" est partit manger");
            pw.close();
            bf.close();
            maSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
            e.printStackTrace();
            System.out.println("IL EST PARTI COMME UN JOHNATION");
        }
    }
}
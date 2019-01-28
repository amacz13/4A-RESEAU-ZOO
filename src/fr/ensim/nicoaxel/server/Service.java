package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.types.ObjectType;
import fr.ensim.nicoaxel.server.animals.Elephant;
import fr.ensim.nicoaxel.server.animals.Fox;
import fr.ensim.nicoaxel.server.animals.Lion;
import fr.ensim.nicoaxel.server.animals.Zebra;
import fr.ensim.nicoaxel.server.types.Espece;

import java.io.*;
import java.net.Socket;

class Service implements Runnable {
    private Socket maSocket;

    Service(Socket s) {
        maSocket = s;
    }
    private String qui = "";
    public void run() {
        BufferedReader bf = null;
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new OutputStreamWriter(maSocket.getOutputStream()));
            bf = new BufferedReader(new InputStreamReader(maSocket.getInputStream()));

            qui = bf.readLine()+" ("+ maSocket.getPort()+")";
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
                if(msg != null){
                    if(msg.equals("STOP")){
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
                        for (Animal a : Main.getlistAnimals("")) {
                            System.out.println("SEND TO "+qui+" : "+a.toSend());
                            pw.println(a.toSend());
                            pw.flush();
                        }
                        pw.println("ENDANIMALSOTHER");
                        pw.flush();
                        System.out.println(qui +" :  #FIN TOUR");

                    }

                }else{
                    System.out.println("Message vide :(");
                }

                System.out.println("#FIN TOUR");

            }while (msg != null &&!msg.equals("STOP"));
            Main.removeAnimals(qui);

            System.out.println(qui+" est partit manger");
        } catch (Exception e) {
            Main.removeAnimals(qui);
            System.out.println(qui+" a rage quit le zoo");
        }finally {
            Main.removeAnimals(qui);
            pw.close();
            try {
                bf.close();
                maSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
package fr.ensim.nicoaxel.server;

import fr.ensim.nicoaxel.client.Corpse;
import fr.ensim.nicoaxel.client.types.ObjectType;
import fr.ensim.nicoaxel.server.animals.Elephant;
import fr.ensim.nicoaxel.server.animals.Fox;
import fr.ensim.nicoaxel.server.animals.Lion;
import fr.ensim.nicoaxel.server.animals.Zebra;
import fr.ensim.nicoaxel.server.types.Espece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class Service implements Runnable {
    private Socket maSocket;
    private static final Logger log = LogManager.getRootLogger();

    Service(Socket s) {
        maSocket = s;
    }
    private String qui = "";
    private String color = "";
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

            color = UserColor.getFreeColor();
            System.out.println("Color send : "+color);
            pw.println(color);
            pw.flush();
            String msg = "";

            pw.println(Main.zoo.toSend());

            for(Obstacle o : Main.zoo.getObstacles()){
                pw.println(o.toSend());
            }

            pw.println("STOP");
            pw.flush();

            UserAnimals ua = new UserAnimals(qui, color);
            UserCorpse uc = new UserCorpse(qui, color);
            Main.addListAnimals(ua);
            Main.addListCorpse(uc);

            do{
                msg = bf.readLine();
                log.info(qui+ " : "+msg);
                if(msg != null){
                    if(msg.equals("STOP")){
                        break;
                    }
                    if (msg.equals("STARTANIMALS")) {
                        System.out.println(qui + " : #DEBUT TOUR");
                        ua.clear();
                        uc.clear();
                    }

                    if(msg.startsWith("[Animal]")){
                            //System.out.println(qui + " : Animal : " + msg.split(" ")[1]);
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
                          //  msg = bf.readLine();
                    }

                    if(msg.startsWith("[Corpse]")){
                        //log.info("Corpse received :");
                        //System.out.println(qui + " : Corpse : "+msg.split(" ")[1]);
                        Corpse c = new Corpse(Integer.parseInt(msg.split(" ")[1]), Integer.parseInt(msg.split(" ")[2]), Integer.parseInt(msg.split(" ")[3]),msg.split(" ")[4]);
                        //log.info(c);
                        uc.addAnimal(c);
                    }

                    if(msg.equals("STOPANIMALS")){

                        ArrayList<UserAnimals> listUA = Main.getlistUserAnimals("");
                        for(int i=0 ; i<listUA.size() ; i++){
                            UserAnimals ual = listUA.get(i);
                            if(ual != null) {
                                for (int j = 0; j < ual.animals.size(); j++) {
                                    Animal a = ual.animals.get(j);
                                    if (a != null) {
                                        log.info("SEND other animals TO " + qui + " : " + a.toSend() + " " + ual.color);
                                        pw.println(a.toSend() + " " + ual.color);
                                        pw.flush();
                                    }
                                }
                            }
                        }
                        log.info("Sending animals done !");
                        pw.println("ENDANIMALSOTHER");
                        pw.flush();
                        listUA.clear();

                        ArrayList<UserCorpse> listUC = Main.getlistUserCorpse("");
                       // for (UserCorpse ual : Main.getlistUserCorpse("")) {
                        for (int i=0 ; i<listUC.size() ; i++) {
                            UserCorpse ucl = listUC.get(i);
                            if(ucl != null) {
                                for (int j=0 ; j<ucl.corpses.size() ; j++) {
                                    Corpse a = ucl.corpses.get(j);
                                    if (a != null) {
                                        log.info("SEND other corpses TO " + qui + " : " + a.toSendServer() + " " + ucl.color);
                                        pw.println(a.toSendServer() + " " + ucl.color);
                                        pw.flush();
                                    }
                                }
                            }
                        }
                        log.info("Sending corpses done !");
                        pw.println("ENDCORPSEOTHER");
                        pw.flush();/**/
                        listUC.clear();
                        System.out.println(qui +" : #FIN TOUR");

                    }

                }else{
                    System.out.println("Message vide :(");
                }


            }while (msg != null &&!msg.equals("STOP"));
            Main.removeAnimals(qui);
            Main.removeCorpse(qui);
            UserColor.liberate(color);

            System.out.println(qui+" est partit manger");
        } catch (Exception e) {
            Main.removeAnimals(qui);
            Main.removeCorpse(qui);
            UserColor.liberate(color);
            System.out.println(qui+" a rage quit le zoo");
        }finally {
            Main.removeAnimals(qui);
            Main.removeCorpse(qui);
            UserColor.liberate(color);
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
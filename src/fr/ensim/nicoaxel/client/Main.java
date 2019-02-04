package fr.ensim.nicoaxel.client;

import fr.ensim.nicoaxel.client.animals.Elephant;
import fr.ensim.nicoaxel.client.animals.Fox;
import fr.ensim.nicoaxel.client.animals.Lion;
import fr.ensim.nicoaxel.client.animals.Zebra;
import fr.ensim.nicoaxel.client.types.ObjectType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    private static final Logger log = LogManager.getRootLogger();

    public static Zoo zoo;
    public static int sizeX = 40, sizeY = 40;
    public static Socket service;

    public static String pseudo = "Fantomas";
    public static String color = ".";

    Pane root;
    Stage pstage;

    public static void main(String args[]) {
        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws CloneNotSupportedException, MalformedURLException {
        pstage = primaryStage;
        MenuBar menuBar = new MenuBar();

        Menu menuZoo = new Menu("Zoo");

        MenuItem connectItem = new MenuItem("Connexion à un serveur");
        MenuItem pseudoItem = new MenuItem("Changer de pseudo");
        MenuItem aboutItem = new MenuItem("A propos de...");

        connectItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("localhost:4321");
                dialog.setTitle("Connexion au serveur");
                dialog.setHeaderText("Connexion au serveur");
                dialog.setContentText("Hôte & Port : ");

                Optional<String> result = dialog.showAndWait();
                if (!result.isPresent()) return;

                System.out.println("Server : " + result.get());

                String host = result.get().split(":")[0];
                String port = result.get().split(":")[1];
                mainApp(root,pstage,host,Integer.parseInt(port));
            }
        });

        aboutItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("About !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("A propos du Zoo");
                alert.setHeaderText("Zoo de Nicolas & Axel");
                alert.setContentText("ENSIM - Janvier 2019");
                alert.showAndWait();
            }
        });

        pseudoItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Choix d'un pseudo");
                dialog.setHeaderText("Choix d'un pseudo");
                dialog.setContentText("Pseudo choisi : ");

                Optional<String> result = dialog.showAndWait();
                if (!result.isPresent()) return;

                System.out.println("Pseudo : " + result.get());

                pseudo = result.get();
            }
        });
        menuZoo.getItems().addAll(connectItem, pseudoItem, aboutItem);
        menuBar.getMenus().addAll(menuZoo);
        final String os = System.getProperty("os.name");

        root = new Pane();

        ImageLoader il = new ImageLoader();
        try {
            il.loadImages();
            il.loadSounds();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter p = new PrintWriter(sw);
            e.printStackTrace(p);
            String exception = sw.toString();
            showException("Téléchargement des images impossible", exception);
        }
        //TODO uncomment for iShit
        /*
        if (os != null && os.startsWith("Mac")) {
            menuBar.useSystemMenuBarProperty().set(true);
            com.apple.eawt.Application app = com.apple.eawt.Application.getApplication();
            app.setDockIconBadge("Zoo");
            //app.
            app.setDockIconImage(Toolkit.getDefaultToolkit().getImage("lion.png"));
        }*/
        root.getChildren().add(menuBar);

        pstage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        pstage.setTitle("Zoo");
        pstage.getIcons().add((CustomImage) ImageLoader.lion);
        pstage.setResizable(false);
        Scene scene = new Scene(root, (16) * sizeX, (16) * sizeY);
        primaryStage.setScene(scene);
        pstage.show();
        System.out.println("Loading finished !");
    }

    public void mainApp(Pane root, Stage primaryStage, String host, int port){


        Thread t = new Thread(new BackgroundSound(Sound.MUSIC));
        t.start();


        try {
            service = new Socket(host, port);
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter p = new PrintWriter(sw);
            e.printStackTrace(p);
            String exception = sw.toString();
            showException("Connexion au serveur impossible",exception);
        }
        PrintWriter pw = null;
        BufferedReader bf = null;
        String line = "";
        try {
            pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
            bf = new BufferedReader(new InputStreamReader(service.getInputStream()));
            pw.println(pseudo);
            pw.flush();

            color = bf.readLine();
            line = bf.readLine();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter p = new PrintWriter(sw);
            e.printStackTrace(p);
            String exception = sw.toString();
            showException("Communication avec le serveur impossible",exception);
        }
        String content = line.split("]")[1];
        sizeX = Integer.parseInt(content.split(" ")[0]);
        sizeY = Integer.parseInt(content.split(" ")[1]);
        zoo = new Zoo(sizeX, sizeY);
        Canvas canvas = new Canvas(16 * sizeX, 16 * sizeY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        do {
            try {
                line = bf.readLine();
                content = line.split("]")[1];
                zoo.addObstacle(new Obstacle(ObjectType.valueOf(content.split(" ")[0]), Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), gc));
            }catch (Exception e) {
                log.info("Data transmisssion finished !");
            }
        } while (!line.equals("STOP"));

        // Draw the Image
        drawAllGrass(gc);

        root.getChildren().add(canvas);
        PrintWriter finalPw = pw;
        BufferedReader finalBf = bf;
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    finalPw.write("STOP");
                    finalPw.flush();
                    finalPw.close();
                    finalBf.close();
                    service.close();
                    log.info("Socket Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.exit();
                System.exit(0);
            }
        });

        for(int i = 0 ; i<20 ; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            switch (randomNum) {
                case 0:
                    zoo.addAnimal(new Lion());
                    log.debug("Creating Lion #"+i);
                    break;
                case 1:
                    zoo.addAnimal(new Zebra());
                    log.debug("Creating Zebra #"+i);
                    break;
                case 2:
                    zoo.addAnimal(new Fox());
                    log.debug("Creating Fox #"+i);
                    break;
                case 3:
                    zoo.addAnimal(new Elephant());
                    log.debug("Creating Elephant #"+i);
                    break;
            }
        }

        log.info("Nb obstacle : "+zoo.getObstacles().size());
        drawObstacles(gc);

        sendMyObject(pw);

        PrintWriter finalPw1 = pw;
        BufferedReader finalBf1 = bf;
        BufferedReader finalBf2 = bf;
        Timeline runner = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            int c = 0;
            @Override
            public void handle(ActionEvent event) {
                log.info(pseudo+" - NEW ROUND - ");
                log.info("Round "+(c++)+" ("+Main.zoo.nbAnimal()+" animals)");

                log.info("Draw background");
                drawAllGrass(gc);
                drawObstacles(gc);
                Main.zoo.action(gc);

                log.info("Send my animals");
                sendMyObject(finalPw1);

                log.info("Reception of other animals");
                zoo.otherAnimals.clear();
                String line = "";
                do {
                    try {
                        line = finalBf1.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("Message received : "+line);

                    if("ENDANIMALSOTHER".equals(line)){
                        log.info("Data transmisssion finished !");
                    }else if(line == null) {
                        log.fatal("Problem in animals reception !!");
                        log.fatal("last line received : "+line);
                    }else if (line.startsWith("[Animal]")){
                        try {
                            String content = line.split("]")[1];
                            content = content.substring(1);
                            switch (content.split(" ")[0]) {
                                case "LION":
                                    log.info("Received Lion");
                                    zoo.otherAnimals.add(new Lion(Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), content.split(" ")[3]));
                                    break;
                                case "ELEPHANT":
                                    log.info("Received Elephant");
                                    zoo.otherAnimals.add(new Elephant(Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), content.split(" ")[3]));
                                    break;
                                case "FOX":
                                    log.info("Received Fox");
                                    zoo.otherAnimals.add(new Fox(Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), content.split(" ")[3]));
                                    break;
                                case "ZEBRA":
                                    log.info("Received Zebra");
                                    zoo.otherAnimals.add(new Zebra(Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), content.split(" ")[3]));
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("Impossible to create animal");
                        }
                    }else{
                        log.info("Unknow message type : "+line);
                    }
                } while (!"ENDANIMALSOTHER".equals(line));


                log.info("Reception of other corpses");
                do {
                    try {
                        line = finalBf1.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("Message received : " + line);

                    if ("ENDCORPSEOTHER".equals(line)) {
                        log.info("Data transmisssion finished !");
                    } else if (line == null) {
                        log.fatal("Problem in CORPSE reception!!");
                        log.fatal("last line received : " + line);
                    } else if (line.startsWith("[Corpse]")) {
                        try {
                            String content = line.split("]")[1];
                            content = content.substring(1);
                            log.info("Content : " + content);
                            log.info("Received Corpse");
                            zoo.addCorpse(Integer.parseInt(content.split(" ")[0]), Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), content.split(" ")[3]);

                            //zoo.addObstacle(new Obstacle(ObjectType.valueOf(content.split(" ")[0]), Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), gc));
                        } catch (Exception e) {
                            log.info("Impossible to create corpse");
                        }
                    } else {
                        log.info("Unknow message type : " + line);
                    }
                }while (!"ENDCORPSEOTHER".equals(line));

            }

        }));
        runner.setCycleCount(Timeline.INDEFINITE);
        runner.play();
    }

    public void sendMyObject(PrintWriter pw){
        pw.println("STARTANIMALS");
        pw.flush();
        try {
            for (int i = 0; i < zoo.getAnimals().size(); i++) {
                pw.println(zoo.getAnimals().get(i).toSend());
                pw.flush();
            }
            //pw.println("STOPANIMALS");
            //pw.flush();

            pw.println("STARTCORPSES");
            pw.flush();
            for (int i = 0; i < zoo.getCorpse().size(); i++) {
                pw.println(zoo.getCorpse().get(i).toSend());
                pw.flush();
            }
        }catch (Exception e){
            log.info("Sending failed");
        }finally {
            pw.println("STOPCORPSE");
            pw.flush();
            pw.println("STOPANIMALS");
            pw.flush();
        }
    }

    public void drawObstacles(GraphicsContext gc){
        for(int i = 0 ; i<zoo.getObstacles().size() ; i++){
            gc.drawImage(zoo.getObstacles().get(i).img, zoo.getObstacles().get(i).x() * 16, zoo.getObstacles().get(i).y() *16);
          //  log.info("Drawing "+zoo.getObstacles().get(i).img.toString()+ " @ "+zoo.getObstacles().get(i).x()+" / "+zoo.getObstacles().get(i).y());
        }
        try {
            for (int i = 0; i < zoo.getCorpse().size(); i++) {
                log.info("Corpse " + i + " / " + zoo.getCorpse().size());
                zoo.getCorpse().get(i).draw(gc);
                log.info("Drawing corpse @ " + zoo.getCorpse().get(i).getX() + " / " + zoo.getCorpse().get(i).getY());
            }
        }catch (Exception e){
            log.info("Minor bug in corpses list");
        }
    }

    public void drawAllGrass(GraphicsContext gc){
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                Random r = new Random();
                gc.drawImage(ImageLoader.grass, i * 16, j * 16);
            }
        }
    }

    public void showException(String errorMsg, String details){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Une erreur est survenue");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(errorMsg);
        Label label = new Label("Le détail de l'erreur : ");
        TextArea textArea = new TextArea(details);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

        Platform.exit();
        System.exit(0);
    }

    public static void removeCorpse(Corpse c) {
        zoo.getCorpse().remove(c);
    }
}

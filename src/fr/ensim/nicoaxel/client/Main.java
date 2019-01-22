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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    private static final Logger log = LogManager.getRootLogger();

    public static Zoo zoo;
    public static int sizeX = 40, sizeY = 40;
    public static Socket service;

    public static void main(String args[]) {

        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException, CloneNotSupportedException {
        ImageLoader il = new ImageLoader();
        il.loadImages();
        //Network data reception
        service = new Socket("192.168.43.19", 4321);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
        BufferedReader bf = new BufferedReader(new InputStreamReader(service.getInputStream()));
        pw.println("Axel");
        pw.flush();
        String line = "";
        line = bf.readLine();
        String header = line.split("]")[0];
        String content = line.split("]")[1];
        sizeX = Integer.parseInt(content.split(" ")[0]);
        sizeY = Integer.parseInt(content.split(" ")[1]);
        zoo = new Zoo(sizeX, sizeY);
        Canvas canvas = new Canvas(16 * sizeX, 16 * sizeY);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        do {
            line = bf.readLine();
            try {
                content = line.split("]")[1];
                zoo.addObstacle(new Obstacle(ObjectType.valueOf(content.split(" ")[0]), Integer.parseInt(content.split(" ")[1]), Integer.parseInt(content.split(" ")[2]), gc));
            }catch (Exception e) {
                log.info("Data transmisssion finished !");
            }
        } while (!line.equals("STOP"));

        // Get the graphics context of the canvas


        // Load the Image
        String imagePath = "/res/tiles/grass.png";
        Image image = il.grass;
        // Draw the Image

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                gc.drawImage(image, i * 16, j * 16);
            }
        }
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, (16) * sizeX, (16) * sizeY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoo");
        primaryStage.getIcons().add((CustomImage) ImageLoader.lion.clone());
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                try {
                    pw.write("STOP");
                    pw.flush();
                    pw.close();
                    bf.close();
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
        for(int i = 0 ; i<zoo.getObstacles().size() ; i++){
            gc.drawImage(zoo.getObstacles().get(i).img, zoo.getObstacles().get(i).x() * 16, zoo.getObstacles().get(i).y() *16);
            log.info("Drawing "+zoo.getObstacles().get(i).img.toString()+ " @ "+zoo.getObstacles().get(i).x()+" / "+zoo.getObstacles().get(i).y());
        }

        pw.println("STARTANIMALS");
        pw.flush();
        for(int i = 0 ; i<zoo.getAnimals().size() ; i++){
            pw.println(zoo.getAnimals().get(i).toSend());
            pw.flush();
        }

        pw.println("STOPANIMALS");
        pw.flush();

        Timeline runner = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            int c = 0;
            @Override
            public void handle(ActionEvent event) {
                log.info("Temps "+(c++)+" ("+Main.zoo.nbAnimal()+" animals)");
                Main.zoo.action(gc);
                /*pw.println("STARTANIMALS");
                pw.flush();
                for(int i = 0 ; i<zoo.getAnimals().size() ; i++){
                    //log.info(zoo.getAnimals().get(i).toSend());
                    pw.println(zoo.getAnimals().get(i).toSend());
                    pw.flush();
                }
                pw.println("STOPANIMALS");
                pw.flush();*/
            }
        }));
        runner.setCycleCount(Timeline.INDEFINITE);
        runner.play();
    }
}

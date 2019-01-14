package fr.ensim.nicoaxel.client;

import fr.ensim.nicoaxel.client.animals.Elephant;
import fr.ensim.nicoaxel.client.animals.Fox;
import fr.ensim.nicoaxel.client.animals.Lion;
import fr.ensim.nicoaxel.client.animals.Zebra;
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

    public static void main(String args[]) throws IOException {
        service = new Socket("192.168.43.19", 4321);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
        BufferedReader bf = new BufferedReader(new InputStreamReader(service.getInputStream()));
        pw.println("Axel");
        pw.flush();
        String line = "";
        do {
            line = bf.readLine();
            try {
                String header = line.split("]")[0];
                String content = line.split("]")[1];
                switch (header) {
                    case "[Zoo":
                        sizeX = Integer.parseInt(content.split(" ")[0]);
                        sizeY = Integer.parseInt(content.split(" ")[1]);
                        break;
                    case "[Obstacle":
                        break;
                }
            } catch (Exception e) {

            }
            System.out.println(line);

        } while (!line.equals("STOP"));
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(16 * sizeX, 16 * sizeY);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Load the Image
        String imagePath = "/tiles/grass.png";
        Image image = new Image(imagePath);
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
        primaryStage.getIcons().add(new Image("/animals/lion.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        zoo = new Zoo(sizeX, sizeY);

        for(int i = 0 ; i<100 ; i++){
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


        Timeline runner = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

            int c = 0;
            @Override
            public void handle(ActionEvent event) {
                log.info("Temps "+(c++)+" ("+Main.zoo.nbAnimal()+" animals)");
                Main.zoo.action(gc);
            }
        }));
        runner.setCycleCount(Timeline.INDEFINITE);
        runner.play();

    }
}

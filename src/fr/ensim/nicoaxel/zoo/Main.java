package fr.ensim.nicoaxel.zoo;


import fr.ensim.nicoaxel.zoo.animals.Elephant;
import fr.ensim.nicoaxel.zoo.animals.Fox;
import fr.ensim.nicoaxel.zoo.animals.Lion;
import fr.ensim.nicoaxel.zoo.animals.Zebra;
import fr.ensim.nicoaxel.zoo.types.ObjectType;
import fr.ensim.nicoaxel.zoo.utils.Coordinates;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    private static final Logger log = LogManager.getRootLogger();

    public static Zoo zoo;
    public static int sizeX = 56, sizeY = 56;

    public static void main(String[] args) {
        Application.launch(args);
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
        Scene scene = new Scene(root, (16) * sizeX - 10, (16) * sizeY -10);
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

        Obstacle.generateObstacles(zoo, gc);
        Coordinates c = Coordinates.generateCoordinate(zoo);
        Coordinates c2 = Coordinates.generateCoordinate(zoo);

        for(int i = 0 ; i<45 ; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            switch (randomNum) {
                case 0:
                    zoo.addAnimal(new Lion());
                    break;
                case 1:
                    zoo.addAnimal(new Zebra());
                    break;
                case 2:
                    zoo.addAnimal(new Fox());
                    break;
                case 3:
                    zoo.addAnimal(new Elephant());
                    break;
            }
        }

        Runner r = new Runner(gc, zoo);
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(500);
        exec.scheduleAtFixedRate(r, 0, 100, TimeUnit.MILLISECONDS);

    }
}

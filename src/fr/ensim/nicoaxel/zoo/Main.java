package fr.ensim.nicoaxel.zoo;


import fr.ensim.nicoaxel.zoo.animals.Lion;
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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static Zoo zoo;
    static int sizeX = 45, sizeY = 45;

    public static void main(String[] args) {
        zoo = new Zoo(sizeX, sizeY);
        //zoo.move(0);


        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(16 * sizeX, 16 * sizeY);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Load the Image
        String imagePath = "/tiles/grass16.png";
        Image image = new Image(imagePath);
        // Draw the Image

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                gc.drawImage(image, i * 16, j * 16);
            }
        }


        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 16 * sizeX, 16 * sizeY);
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

        Obstacle.generateObstacles(zoo, gc);
        Coordinates c = Coordinates.generateCoordinate(zoo);
        Coordinates c2 = Coordinates.generateCoordinate(zoo);

        for(int i = 0 ; i<25 ; i++){
            zoo.addAnimal(new Lion());
        }

        Runner r = new Runner(gc, zoo);
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(r, 0, 300, TimeUnit.MILLISECONDS);

    }
}

package fr.ensim.nicoaxel.zoo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static GraphicsContext gc;
    public static Zoo zoo;
    public static int sizeX = 45, sizeY = 45;

    public static void main(String[] args) throws InterruptedException {
        zoo = new Zoo(sizeX,sizeY);
        zoo.addAnimal(new Animal(15,15));
        //zoo.move(0);



        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(16*sizeX, 16*sizeY);

        // Get the graphics context of the canvas
        gc = canvas.getGraphicsContext2D();
        // Load the Image
        String imagePath = "/tiles/grass16.png";
        Image image = new Image(imagePath);
        // Draw the Image

        for (int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++) {
                gc.drawImage(image, i * 16, j*16);
            }
        }



        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 16*sizeX, 16*sizeY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoo");
        primaryStage.setResizable(false);
        primaryStage.show();



        Runner r = new Runner(gc, zoo);
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(r, 0, 500, TimeUnit.MILLISECONDS);

    }
}

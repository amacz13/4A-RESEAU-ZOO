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

    public static void main(String[] args) throws InterruptedException {
        Zoo zoo = new Zoo(6,15);
        zoo.addAnimal(new Animal());
        zoo.afficher();

        Thread.sleep(200);
        zoo.addAnimal(new Animal(2,2));
        zoo.afficher();

        Runner r = new Runner();

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(r, 0, 100, TimeUnit.MILLISECONDS);

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(576, 576);

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Load the Image
        String imagePath = "/tiles/grass16.png";
        Image image = new Image(imagePath);
        // Draw the Image

        for (int i = 0; i < 45; i++){
            for (int j = 0; j < 45; j++) {
                gc.drawImage(image, i * 16, j*16);
            }
        }

        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 560, 560);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zoo");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

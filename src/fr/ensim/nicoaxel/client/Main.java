package fr.ensim.nicoaxel.client;

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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {
    public static int sizeX = 40, sizeY = 40;

    public static void main(String args[]) throws IOException {
        Socket service = new Socket("192.168.43.19", 4321);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
        pw.println("Axel");
        pw.flush();
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
    }
}

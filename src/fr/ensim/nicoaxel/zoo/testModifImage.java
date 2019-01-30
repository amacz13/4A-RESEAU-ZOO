package fr.ensim.nicoaxel.zoo;

        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.image.*;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;
        import javafx.scene.paint.Color;

public class testModifImage extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create Image and ImageView objects
        Image image = new Image("https://zoodemiunicorn.azurewebsites.net/res/animals/lion.png");
        ImageView imageView = new ImageView();
    /*   // imageView.setImage(image);

        // Obtain PixelReader
        PixelReader pixelReader = image.getPixelReader();

        // Create WritableImage
        WritableImage wImage = new WritableImage(pixelReader,(int)image.getWidth(),(int)image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();


        int bx = (int) (image.getWidth()/2);
        int by = (int) (image.getHeight()/4)-1;


        for(int x=-2;x<2;x++) {
            for(int y=-2;y<2;y++) {
                pixelWriter.setColor(bx+x,by+y,Color.RED);
            }
        }
*/

        // Display image on screen
        imageView.setImage(modifImg(image, Color.RED));
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Image Write Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Image modifImg(Image img, Color color){

        PixelReader pixelReader = img.getPixelReader();
        WritableImage wImage = new WritableImage(pixelReader,(int)img.getWidth(),(int)img.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();


        int bx = (int) (img.getWidth()/2);
        int by = (int) (img.getHeight()/4)-1;


        for(int x=-2;x<2;x++) {
            for(int y=-2;y<2;y++) {
                pixelWriter.setColor(bx+x,by+y, color);
            }
        }


        Image img2 = wImage;
        return img2;
    }
}
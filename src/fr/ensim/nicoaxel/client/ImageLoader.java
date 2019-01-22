package fr.ensim.nicoaxel.client;

import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageLoader implements Cloneable{

    private static final Logger log = LogManager.getRootLogger();

    public static CustomImage grass;
    public static CustomImage water;
    public static CustomImage stone;
    public static CustomImage wood;
    public static CustomImage lion;
    public static CustomImage zebra;
    public static CustomImage elephant;
    public static CustomImage fox;
    public static CustomImage unknown;

    public static void loadImages() throws IOException {
        log.info("Downloading picture of grass...");
        InputStream in = new URL("https://zoodemiunicorn.azurewebsites.net/res/tiles/grass.png").openStream();
        Files.copy(in, Paths.get("grass.png"), StandardCopyOption.REPLACE_EXISTING);
        grass = new CustomImage("file:"+Paths.get("grass.png").toAbsolutePath().toString());
        log.info("Downloading picture of lion...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/lion.png").openStream();
        Files.copy(in, Paths.get("lion.png"), StandardCopyOption.REPLACE_EXISTING);
        lion = new CustomImage("file:"+Paths.get("lion.png").toAbsolutePath().toString());
        log.info("Downloading picture of zebra...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/zebra.png").openStream();
        Files.copy(in, Paths.get("zebra.png"), StandardCopyOption.REPLACE_EXISTING);
        zebra = new CustomImage("file:"+Paths.get("zebra.png").toAbsolutePath().toString());
        log.info("Downloading picture of elephant...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/elephant.png").openStream();
        Files.copy(in, Paths.get("elephant.png"), StandardCopyOption.REPLACE_EXISTING);
        elephant = new CustomImage("file:"+Paths.get("elephant.png").toAbsolutePath().toString());
        log.info("Downloading picture of fox...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/fox.png").openStream();
        Files.copy(in, Paths.get("fox.png"), StandardCopyOption.REPLACE_EXISTING);
        fox = new CustomImage("file:"+Paths.get("fox.png").toAbsolutePath().toString());
        log.info("Downloading picture of unknown...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/unknown.png").openStream();
        Files.copy(in, Paths.get("unknown.png"), StandardCopyOption.REPLACE_EXISTING);
        unknown = new CustomImage("file:"+Paths.get("unknown.png").toAbsolutePath().toString());
        log.info("Downloading picture of water...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/water.png").openStream();
        Files.copy(in, Paths.get("water.png"), StandardCopyOption.REPLACE_EXISTING);
        water = new CustomImage("file:"+Paths.get("water.png").toAbsolutePath().toString());
        log.info("Downloading picture of stone...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/stone.png").openStream();
        Files.copy(in, Paths.get("stone.png"), StandardCopyOption.REPLACE_EXISTING);
        stone = new CustomImage("file:"+Paths.get("stone.png").toAbsolutePath().toString());
        log.info("Downloading picture of wood...");
        in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/wood.png").openStream();
        Files.copy(in, Paths.get("wood.png"), StandardCopyOption.REPLACE_EXISTING);
        wood = new CustomImage("file:"+Paths.get("wood.png").toAbsolutePath().toString());
        log.info("Downloading finished !");
    }
}

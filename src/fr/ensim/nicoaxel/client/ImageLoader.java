package fr.ensim.nicoaxel.client;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageLoader implements Cloneable {

    private static final Logger log = LogManager.getRootLogger();

    public static Image grass;
    public static Image water;
    public static Image stone;
    public static Image wood;
    public static Image lion;
    public static Image zebra;
    public static Image elephant;
    public static Image fox;
    public static Image unicorn;
    public static Image moule;
    public static Image unknown;
    public static Image sand;
    public static Image corpse;

    public static FileInputStream music;
    public static FileInputStream death;
    public static FileInputStream death2;
    public static FileInputStream coupling;
    public static FileInputStream baby;

    public static void loadImages() throws IOException {
        InputStream in = new URL("https://zoodemiunicorn.azurewebsites.net/res/tiles/grass.png").openStream();
        Files.copy(in, Paths.get("grass.png"), StandardCopyOption.REPLACE_EXISTING);
        grass = new CustomImage("file:" + Paths.get("grass.png").toAbsolutePath().toString());


        log.info("Downloading picture of lion...");
        if (isInRepo("lion.png")) {
            log.info("Ressource lion is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/lion.png").openStream();
            Files.copy(in, Paths.get("lion.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        lion = new CustomImage("file:" + Paths.get("lion.png").toAbsolutePath().toString());

        log.info("Downloading picture of zebra...");
        if (isInRepo("zebra.png")) {
            log.info("Ressource zebra is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/zebra.png").openStream();
            Files.copy(in, Paths.get("zebra.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        zebra = new CustomImage("file:" + Paths.get("zebra.png").toAbsolutePath().toString());

        log.info("Downloading picture of elephant...");
        if (isInRepo("elephant.png")) {
            log.info("Ressource elephant is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/elephant.png").openStream();
            Files.copy(in, Paths.get("elephant.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        elephant = new CustomImage("file:" + Paths.get("elephant.png").toAbsolutePath().toString());


        log.info("Downloading picture of moule...");
        if (isInRepo("moule.png")) {
            log.info("Ressource moule is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/moule.png").openStream();
            Files.copy(in, Paths.get("moule.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        moule = new CustomImage("file:" + Paths.get("moule.png").toAbsolutePath().toString());


        log.info("Downloading picture of unicorn...");
        if (isInRepo("unicorn.png")) {
            log.info("Ressource unicorn is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/unicorn.png").openStream();
            Files.copy(in, Paths.get("unicorn.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        unicorn = new CustomImage("file:" + Paths.get("unicorn.png").toAbsolutePath().toString());

        log.info("Downloading picture of fox...");
        if (isInRepo("fox.png")) {
            log.info("Ressource fox is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/animals/fox.png").openStream();
            Files.copy(in, Paths.get("fox.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        fox = new CustomImage("file:" + Paths.get("fox.png").toAbsolutePath().toString());

        log.info("Downloading picture of unknown...");
        if (isInRepo("unknown.png")) {
            log.info("Ressource unknown is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/unknown.png").openStream();
            Files.copy(in, Paths.get("unknown.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        unknown = new CustomImage("file:" + Paths.get("unknown.png").toAbsolutePath().toString());

        log.info("Downloading picture of water...");
        if (isInRepo("water.png")) {
            log.info("Ressource water is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/water.png").openStream();
            Files.copy(in, Paths.get("water.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        water = new CustomImage("file:" + Paths.get("water.png").toAbsolutePath().toString());

        log.info("Downloading picture of stone...");
        if (isInRepo("stone.png")) {
            log.info("Ressource stone is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/stone.png").openStream();
            Files.copy(in, Paths.get("stone.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        stone = new CustomImage("file:" + Paths.get("stone.png").toAbsolutePath().toString());

        log.info("Downloading picture of wood...");
        if (isInRepo("wood.png")) {
            log.info("Ressource wood is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/objects/wood.png").openStream();
            Files.copy(in, Paths.get("wood.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        wood = new CustomImage("file:" + Paths.get("wood.png").toAbsolutePath().toString());

        log.info("Downloading picture of sand...");
        if (isInRepo("sand.png")) {
            log.info("Ressource sand is already download");
        } else {
            in = new URL("https://zoodemiunicorn.azurewebsites.net/res/tiles/sand.png").openStream();
            Files.copy(in, Paths.get("sand.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        sand = new CustomImage("file:" + Paths.get("sand.png").toAbsolutePath().toString());

        log.info("Downloading picture of corpse...");
        if (isInRepo("corpse.png")) {
            log.info("Ressource corpse is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/corpse2.png").openStream();
            Files.copy(in, Paths.get("corpse.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        corpse = new CustomImage("file:" + Paths.get("corpse.png").toAbsolutePath().toString());

        log.info("Downloading wallpaper picture...");
        if (isInRepo("wallpaper.png")) {
            log.info("Ressource wallpaper is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/wallpaper.png").openStream();
            Files.copy(in, Paths.get("wallpaper.png"), StandardCopyOption.REPLACE_EXISTING);
        }
        //Download sounds
        log.info("Downloading finished !");
    }

    public static void loadSounds() throws IOException {


        InputStream in;

        log.info("Downloading sound of music...");
        if (isInRepo("music.mp3")) {
            log.info("Ressource music is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/oui.mp3").openStream();
            Files.copy(in, Paths.get("music.mp3"), StandardCopyOption.REPLACE_EXISTING);
            music = new FileInputStream("music.mp3");/**/
        }

        log.info("Downloading sound of death...");
        if (isInRepo("death.mp3")) {
            log.info("Ressource death is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/death.mp3").openStream();
            Files.copy(in, Paths.get("death.mp3"), StandardCopyOption.REPLACE_EXISTING);
            death = new FileInputStream("death.mp3");
        }

        log.info("Downloading sound of death (by corpse)...");
        if (isInRepo("death2.mp3")) {
            log.info("Ressource death (by corpse) is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/death2.mp3").openStream();
            Files.copy(in, Paths.get("death2.mp3"), StandardCopyOption.REPLACE_EXISTING);
            death2 = new FileInputStream("death2.mp3");
        }

        log.info("Downloading sound of coupling...");
        if (isInRepo("coupling.mp3")) {
            log.info("Ressource coupling is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/sex.mp3").openStream();
            Files.copy(in, Paths.get("coupling.mp3"), StandardCopyOption.REPLACE_EXISTING);
            coupling = new FileInputStream("coupling.mp3");
        }

        log.info("Downloading sound of baby...");
        if (isInRepo("baby.mp3")) {
            log.info("Ressource baby is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/baby.mp3").openStream();
            Files.copy(in, Paths.get("baby.mp3"), StandardCopyOption.REPLACE_EXISTING);
            baby = new FileInputStream("baby.mp3");
        }

        log.info("Downloading sound of introduction song...");
        if (isInRepo("first_song.mp3")) {
            log.info("Ressource song is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/first_song.mp3").openStream();
            Files.copy(in, Paths.get("first_song.mp3"), StandardCopyOption.REPLACE_EXISTING);
            baby = new FileInputStream("first_song.mp3");
        }

        log.info("Downloading sound of hang music...");
        if (isInRepo("Hang.mp3")) {
            log.info("Ressource hang is already download");
        } else {
            in = new URL("http://perso.univ-lemans.fr/~i152300/ZOO/Hang.mp3").openStream();
            Files.copy(in, Paths.get("Hang.mp3"), StandardCopyOption.REPLACE_EXISTING);
            baby = new FileInputStream("Hang.mp3");
        }
        log.info("Downloading finished !");
    }


    public static Image modifImg(Image img, String colorStr) {

        Color color = getColorFromString(colorStr);

        PixelReader pixelReader = img.getPixelReader();
        WritableImage wImage = new WritableImage(pixelReader, (int) img.getWidth(), (int) img.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();


        int bx = (int) (img.getWidth() / 2);
        int by = (int) (img.getHeight() / 4) - 1;


        for (int x = -2; x < 2; x++) {
            for (int y = -2; y < 2; y++) {
                pixelWriter.setColor(bx + x, by + y, color);
            }
        }


        return (Image) wImage;
    }

    // RED BLUE BLACK GREEN MAGENTA ORANGE WHITE CYAN

    private static Color getColorFromString(String colorStr) {
        switch (colorStr) {
            case "RED":
                return Color.RED;
            case "BLUE":
                return Color.BLUE;
            case "BLACK":
                return Color.BLACK;
            case "GREEN":
                return Color.GREEN;
            case "MAGENTA":
                return Color.MAGENTA;
            case "ORANGE":
                return Color.ORANGE;
            case "WHITE":
                return Color.WHITE;
            case "CYAN":
                return Color.CYAN;
            default:
                return Color.GHOSTWHITE;
        }
    }


    private static File repertory = new File("./");

    private static boolean isInRepo(String myFile) {
        for (String file : repertory.list()) {
            if (file.equals(myFile)) {
                return true;
            }
        }
        return false;
    }

}

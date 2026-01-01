package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private static ResourceLoader Loader;
    private final Map<String, Image> imageCache = new HashMap<>();

    private ResourceLoader() {
    }

    public static ResourceLoader getInstance() {
        if (Loader == null) {
            Loader = new ResourceLoader();
        }
        return Loader;
    }

    public Image getImage(String imagePath) {
        if (imagePath == null) {
            return null;
        }

        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        } else {
            Image img = null;
            try {
                // Load image from resources (works in JAR and IDE)
                InputStream stream = getClass().getResourceAsStream(imagePath);
                if (stream != null) {
                    img = ImageIO.read(stream);
                    stream.close();
                    imageCache.put(imagePath, img);
                } else {
                    System.err.println("Could not find resource: " + imagePath);
                }
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }

            return img;
        }
    }
}
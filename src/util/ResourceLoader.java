package util;

import javax.swing.*;
import java.awt.*;
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

        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        } else {
            Image img;
            try {
                img = new ImageIcon(imagePath).getImage();
                imageCache.put(imagePath, img);
            } catch (Exception e) {
                img = null;
            }

            return img;
        }
    }
}

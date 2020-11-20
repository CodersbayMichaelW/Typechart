package Utility;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Utilities {

    public Utilities() {
    }

    // ------------- Help for making Color into a String -------------
    public String makeColorStringForDB(ColorPicker cp) throws SQLException {
        Color color = cp.getValue();
        return ((int) (color.getRed() * 255) + ", " + (int) (color.getGreen() * 255) + ", " + (int) (color.getBlue() * 255));
    }

    public String makeColorOpacityString(ColorPicker cp) {
        return ("" + (Math.round(cp.getValue().getOpacity() * 100.0) / 100.0));
    }

    // ---------------------------------------------------------------------------------------------------------------------
// ------------- load images into an Array from aa folder -------------
    public ArrayList<String> readAllImagesInBackgroundFolder() {
        // FilePath
//        URL url = getClass().getResource("/image/background/");
        File dir = new File(getClass().getResource("image/background/").getPath());

//        System.out.println(getClass().getResource("/image/background/"));
//        System.out.println(dir.getPath());
//        System.out.println(dir.isDirectory());

        // array of supported extensions (use a List if you prefer)
        String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp", "jpg"  // and other formats if you want
        };
        // filter to identify images based on their extensions
        FilenameFilter IMAGE_FILTER = (dir1, name) -> {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        };
        // ArrayList where the imageNames are stored
        ArrayList<String> images = new ArrayList<String>();

        // Code Execution
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : Objects.requireNonNull(dir.listFiles(IMAGE_FILTER))) {
                try {
                    // do something with your image here
                    images.add(f.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return images;
    }
}

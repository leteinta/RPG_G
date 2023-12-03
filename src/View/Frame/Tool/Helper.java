package src.View.Frame.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Helper {
    public static Image resizeImage(Image originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2.dispose();
        return resizedImage;
    }

    public static ImageIcon getImageIcon(String relativePath){
        return new ImageIcon(Objects.requireNonNull(Helper.class.getResource("../Images/"+relativePath)));
    }
}

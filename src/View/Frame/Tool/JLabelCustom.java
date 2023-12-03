package src.View.Frame.Tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JLabelCustom extends JLabel {
    public JLabelCustom(ImageIcon icon, int horizontalAlignment, int top, int left, int bottom, int right){
        super(icon, horizontalAlignment);
        setBorder(new EmptyBorder(top, left, bottom, right));
    }

    public JLabelCustom(String text, int horizontalAlignment, int top, int left, int bottom, int right){
        super(text, horizontalAlignment);
        setBorder(new EmptyBorder(top, left, bottom, right));
    }

    public JLabelCustom(String text, int horizontalAlignment, int top, int left, int bottom, int right, int fontSize){
        this(text, horizontalAlignment, top, left, bottom, right);
        setFont(new Font(getFont().getFontName(), getFont().getStyle(), fontSize));
    }

    public JLabelCustom(String text, int horizontalAlignment, int top, int left, int bottom, int right, int fontSize, Color foreground){
        this(text, horizontalAlignment, top, left, bottom, right, fontSize);
        setForeground(foreground);
    }
}

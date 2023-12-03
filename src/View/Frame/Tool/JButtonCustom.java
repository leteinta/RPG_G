package src.View.Frame.Tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JButtonCustom extends JButton {
    private Color defaultBackgroundColor = UIManager.getColor("Button.background");
    private Color backgroundColorHover = Color.BLACK;

    public JButtonCustom(String text, int top, int left, int bottom, int right){
        super(text);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(top, left, bottom, right));
        getModel().addChangeListener(e -> {
            if (getModel().isRollover()) {
                setBackground(this.backgroundColorHover);
            } else {
                setBackground(this.defaultBackgroundColor);
            }
        });
    }

    public JButtonCustom(String text, int top, int left, int bottom, int right, int fontSize){
        this(text, top, left, bottom, right);
        setFont(new Font(getFont().getFontName(), getFont().getStyle(), fontSize));
    }

    public void setWidth(int width){
        setMaximumSize(new Dimension(width, getMaximumSize().height));
        setPreferredSize(new Dimension(width, getPreferredSize().height));
    }

    public void setColor(Color backgroundColor, Color backgroundColorHover, Color foregroundColor){
        this.defaultBackgroundColor = backgroundColor;
        this.backgroundColorHover = backgroundColorHover;
        setBackground(backgroundColor);
        setForeground(foregroundColor);
    }
}

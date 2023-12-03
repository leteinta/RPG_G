package src.View.Frame.Tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JPanelCustom extends JPanel {
    public JPanelCustom(LayoutManager layout, int top, int left, int bottom, int right) {
        super(layout);
        setBorder(new EmptyBorder(top, left, bottom, right));
    }

    public JPanelCustom(LayoutManager layout, int top, int left, int bottom, int right, Color color) {
        this(layout, top, left, bottom, right);
        setBackground(color);
    }
}

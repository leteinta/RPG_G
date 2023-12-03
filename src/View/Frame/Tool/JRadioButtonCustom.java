package src.View.Frame.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JRadioButtonCustom extends JPanelCustom {
    private boolean selected = false;
    private final String value;

    public JRadioButtonCustom(JRadioGroupCustom radioGroup, String value, String text, String relativeIconPath) {
        super(new BorderLayout(), 0, 10, 0, 10);
        this.value = value;
        radioGroup.addRadioButton(this);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(Helper.getImageIcon(relativeIconPath), JLabel.CENTER);
        add(iconLabel, BorderLayout.NORTH);

        JLabel label = new JLabelCustom(text, JLabel.CENTER, 0, 0, 10, 0);
        add(label, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selected = true;
                setBackground(Color.lightGray);
                radioGroup.setButtonSelected(value);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!selected){
                    setBackground(new Color(216, 216, 216));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!selected) {
                    setBackground(null);
                }
            }
        });
    }

    public String getValue() {
        return value;
    }

    public void deselect(){
        this.selected = false;
        setBackground(null);
    }
}

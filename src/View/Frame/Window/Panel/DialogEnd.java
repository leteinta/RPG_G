package src.View.Frame.Window.Panel;

import src.Service.Translator;
import src.View.Frame.Tool.Helper;
import src.View.Frame.Tool.JButtonCustom;
import src.View.Frame.Tool.JLabelCustom;
import src.View.Frame.Tool.JPanelCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class DialogEnd extends JDialog{
    Translator translator = Translator.getInstance();
    public DialogEnd(String iconPath, String message) {
        JDialog dialog = this;
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../Images/logo.png")));
        setIconImage(logoIcon.getImage());
        setTitle(translator.getLanguage().getText("frame-dialog-end-title"));

        JPanel panel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = GridBagConstraints.CENTER;
        ImageIcon resizedIcon = new ImageIcon(Helper.resizeImage(Helper.getImageIcon(iconPath).getImage(), 60, 75));
        JLabel imageLabel = new JLabel(resizedIcon);
        centerPanel.add(imageLabel, gbc);
        JLabel label = new JLabelCustom(message, JLabel.CENTER, 10, 10, 10, 10, 14);
        centerPanel.add(label, gbc);
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanelCustom(new FlowLayout(), 10, 10, 10, 10);
        JButtonCustom button = new JButtonCustom(translator.getLanguage().getText("frame-dialog-button"), 10, 50, 10, 50, 16);
        button.setColor(Color.BLACK, new Color(93, 93, 93), Color.WHITE);
        button.addActionListener(e -> {
            dialog.dispose();
            System.exit(0);
        });
        bottomPanel.add(button);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setSize(500, 200);
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
}

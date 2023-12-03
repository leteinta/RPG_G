package src.View.Frame.Window;

import src.Service.Translator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class AbstractWindow extends JFrame{
    protected static final Translator translator = Translator.getInstance();

    public AbstractWindow(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
    }

    protected void build(){
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../Images/logo.png")));
        setIconImage(logoIcon.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(this.buildTopPanel(), BorderLayout.NORTH);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = GridBagConstraints.CENTER;
        mainPanel.add(this.buildCenterPanel(gbc), BorderLayout.CENTER);
        mainPanel.add(this.buildBottomPanel(), BorderLayout.SOUTH);
        add(mainPanel);

        setVisible(true);
    }

    protected abstract JPanel buildTopPanel();
    protected abstract JPanel buildCenterPanel(GridBagConstraints gbc);
    protected abstract JPanel buildBottomPanel();
}

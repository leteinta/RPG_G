package src.View.Frame.Window;

import src.Model.Pawn.AbstractPlayer;
import src.Service.FactionFactory;
import src.View.Frame.Tool.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartWindow extends AbstractWindow {
    public static JTextField namePlayer;
    public static JRadioGroupCustom radioGroupFaction;
    private static JButtonCustom playButton;

    public StartWindow() {
        super(translator.getLanguage().getText("frame-start-title"), 500, 650);
        this.build();
    }

    private void updateButtonPlay() {
        playButton.setEnabled(namePlayer.getText().trim().length() > 0 && radioGroupFaction.getValue() != null);
    }

    @Override
    protected JPanel buildTopPanel(){
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel imageTopPanel = new JPanelCustom(new FlowLayout(), 0, 0, 0, 0, Color.BLACK);
        imageTopPanel.add(new JLabel(Helper.getImageIcon("borcele.png")));
        topPanel.add(imageTopPanel, BorderLayout.NORTH);

        JLabel textLabel = new JLabelCustom(translator.getLanguage().getText("frame-start-text"), JLabel.CENTER, 10, 0, 10, 0, 18);
        topPanel.add(textLabel, BorderLayout.SOUTH);

        return topPanel;
    }

    @Override
    protected JPanel buildCenterPanel(GridBagConstraints gbc){
        JPanel centerPanel = new JPanel(new GridBagLayout());

        JPanel infoPanel = new JPanel(new BorderLayout());
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel(translator.getLanguage().getText("choice-name"));
        namePlayer = new JTextField(15);
        namePlayer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonPlay();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonPlay();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonPlay();
            }
        });
        namePanel.add(nameLabel);
        namePanel.add(namePlayer);
        infoPanel.add(namePanel, BorderLayout.NORTH);

        JPanel factionPanel = new JPanel();
        radioGroupFaction = new JRadioGroupCustom();
        for(String faction: FactionFactory.getFactions()){
            JRadioButtonCustom radioButton = new JRadioButtonCustom(radioGroupFaction, faction, translator.getLanguage().getText("pawn-"+faction),"Factions/"+faction+".png");
            radioButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateButtonPlay();
                }
            });
            factionPanel.add(radioButton);
        }
        infoPanel.add(factionPanel, BorderLayout.CENTER);

        centerPanel.add(infoPanel, gbc);
        return centerPanel;
    }

    @Override
    protected JPanel buildBottomPanel(){
        JPanel bottomPanel = new JPanelCustom(new FlowLayout(), 10, 10, 10, 10, Color.BLACK);
        playButton = new JButtonCustom(translator.getLanguage().getText("frame-start-button"), 10, 50, 10, 50, 20);
        playButton.setColor(Color.white, new Color(211, 211, 211), Color.BLACK);
        playButton.setEnabled(false);
        playButton.addActionListener(e -> {
            try {
                AbstractPlayer player = FactionFactory.createFaction(radioGroupFaction.getValue(), namePlayer.getText());
                new MainWindow(player);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            setVisible(false);
        });
        bottomPanel.add(playButton);
        return bottomPanel;
    }
}

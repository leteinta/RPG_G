package src.View.Frame.Window;

import src.Model.Coordinate;
import src.Model.Level.AbstractLevel;
import src.Model.Level.Level1;
import src.Model.Pawn.AbstractPlayer;
import src.Service.NotifyObserver;
import src.View.Frame.NotifyObserverFrame;
import src.View.Frame.Tool.*;
import src.View.Frame.Window.Panel.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainWindow extends AbstractWindow {
    private AbstractLevel level;
    private PlayerPanel playerPanel;
    private JPanel centerPanel;
    private MapPanel mapPanel;
    private StorePanel storePanel;
    private InventoryPanel inventoryPanel;


    public MainWindow(AbstractPlayer player) {
        super("", 1000,680);
        try {
            this.level = new Level1(player);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.build();
        this.buildTitle();
    }

    public void buildTitle(){
        setTitle(translator.getLanguage().getText("frame-main-title", level.getNumber()));
    }

    @Override
    protected JPanel buildTopPanel(){
        AbstractPlayer player = level.getMap().getPlayer();
        JPanel topPanel = new JPanelCustom(new FlowLayout(FlowLayout.LEFT), 0, 0, 0, 0, Color.BLACK);
        ImageIcon resizedIcon = new ImageIcon(Helper.resizeImage(Helper.getImageIcon("Factions/"+player.getClassName()+".png").getImage(), 60, 75));
        JLabel imageLabel = new JLabel(resizedIcon);
        topPanel.add(imageLabel);

        playerPanel = new PlayerPanel(player);
        topPanel.add(playerPanel);
        return topPanel;
    }

    @Override
    protected JPanel buildCenterPanel(GridBagConstraints gbc) {
        centerPanel = new JPanel(new GridBagLayout());
        mapPanel = new MapPanel(this, this.level.getMap(), 40);
        centerPanel.add(mapPanel, gbc);
        storePanel = new StorePanel(this, level.getMap().getPlayer());
        storePanel.setVisible(false);
        centerPanel.add(storePanel, gbc);
        inventoryPanel = new InventoryPanel(this, level.getMap().getPlayer());
        centerPanel.add(inventoryPanel, gbc);
        return centerPanel;
    }

    @Override
    protected JPanel buildBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(this.buildMoveButton());
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(this.buildTargetButton());
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(this.buildStoreButton());
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(this.buildInventoryButton());
        buttonPanel.add(buttonBox);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        NotifyObserverFrame.observerArea.setEditable(false);
        NotifyObserverFrame.observerArea.setBackground(Color.BLACK);
        NotifyObserverFrame.observerArea.setForeground(Color.WHITE);
        NotifyObserverFrame.observerArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane(NotifyObserverFrame.observerArea);
        scrollPane.setPreferredSize(new Dimension(Short.MAX_VALUE, 120));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        return bottomPanel;
    }

    private JButtonCustom buildButton(String button){
        JButtonCustom buttonCustom = new JButtonCustom(translator.getLanguage().getText("action-"+button), 15, 10, 15, 10, 14);
        buttonCustom.setWidth(150);
        buttonCustom.setColor(Color.BLACK, new Color(93, 93, 93), Color.WHITE);
        return buttonCustom;
    }
    private JButtonCustom buildMoveButton(){
        JButtonCustom moveButton = this.buildButton("move");
        moveButton.addActionListener(e -> {
            showMap();
            List<Coordinate> coordinates = level.getMap().getCoordinatesForMove(level.getMap().getPlayer(), true);
            if(coordinates.size() > 0){
                mapPanel.updatePossibleCoordinates(coordinates, true);
            }else{
                NotifyObserver.notify("nothing-coordinate");
            }
        });
        return moveButton;
    }

    private JButtonCustom buildTargetButton(){
        JButtonCustom targetButton = this.buildButton("target");
        targetButton.addActionListener(e -> {
            showMap();
            List<Coordinate> coordinates = level.getMap().getCoordinatesForTarget(level.getMap().getPlayer());
            if(coordinates.size() > 0){
                mapPanel.updatePossibleCoordinates(coordinates, false);
            }else{
                NotifyObserver.notify("nothing-coordinate");
            }
        });
        return targetButton;
    }

    public void endOfRound(){
        showMap();
        try {
            level.getMap().playMonsters();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        level.getMap().getPlayer().endOfRound();
        mapPanel.repaint();
        this.updatePlayerPanel();
        if (level.getMap().getPlayer().isDestroyed()) {
            gameOver();
        }
    }

    private JButtonCustom buildStoreButton(){
        JButtonCustom button = this.buildButton("store");
        button.addActionListener(e -> showStore());
        return button;

    }

    private JButtonCustom buildInventoryButton(){
        JButtonCustom button = this.buildButton("inventory");
        button.addActionListener(e -> showInventory());
        return button;
    }

    public void updatePlayerPanel(){
        playerPanel.build();
    }

    public void updateStore(){
        storePanel.updateButtons(level.getMap().getPlayer());
    }

    public void gameOver(){
        new DialogEnd("skull.png", translator.getLanguage().getText("frame-dialog-end-lost"));
    }

    public void exitReached(){
        NotifyObserver.notify("level-win", level.getNumber());
        AbstractPlayer player = level.getMap().getPlayer();
        try {
            level = level.nextLevel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(level == null){
            new DialogEnd("Factions/"+player.getClassName()+".png", translator.getLanguage().getText("frame-dialog-end-win"));
        }else{
            mapPanel.setMap(this.level.getMap());
            centerPanel.revalidate();
            this.buildTitle();
        }
    }

    public void showMap(){
        mapPanel.removePossibleCoordinates();
        mapPanel.setVisible(true);
        storePanel.setVisible(false);
        inventoryPanel.setVisible(false);
    }

    private void showStore(){
        mapPanel.setVisible(false);
        storePanel.setVisible(true);
        inventoryPanel.setVisible(false);
    }

    private void showInventory(){
        mapPanel.setVisible(false);
        storePanel.setVisible(false);
        inventoryPanel.build();
        inventoryPanel.setVisible(true);
    }
}

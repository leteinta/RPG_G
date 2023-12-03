package src.View.Frame.Window.Panel;

import src.Model.*;
import src.Model.Pawn.AbstractPlayer;
import src.Model.Pawn.Monsters.AbstractMonster;
import src.Model.Pawn.Obstacles.AbstractObstacle;
import src.View.Frame.Tool.Helper;
import src.View.Frame.Window.MainWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {
    private final int cellSize;
    private Map map;
    private List<Coordinate> possibleCoordinates = new ArrayList<>();
    private Boolean isMove;

    public MapPanel(MainWindow mainWindow, Map map, int cellSize) {
        this.cellSize = cellSize;
        this.map = map;
        setPreferredSize(new Dimension(this.map.getGrid()[0].length*this.cellSize, this.map.getGrid().length*this.cellSize));
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e){
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;

            for (Coordinate coordinate: possibleCoordinates){
                if(coordinate.toString().equals(new Coordinate(x, y).toString())){
                    try {
                        if(isMove){
                            getMap().moveOrganism(getMap().getPlayer(), possibleCoordinates, coordinate);
                            if(getMap().exitReached()){
                                mainWindow.exitReached();
                            }
                        }else{
                            getMap().targetPawn(getMap().getPlayer(), possibleCoordinates, coordinate);
                            if(getMap().getPlayer().isDestroyed()){
                                mainWindow.gameOver();
                            }
                        }
                        mainWindow.updatePlayerPanel();
                        mainWindow.updateStore();
                        removePossibleCoordinates();
                        mainWindow.endOfRound();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (Coordinate coordinate: possibleCoordinates){
                    int x = coordinate.getX() * cellSize;
                    int y = coordinate.getY() * cellSize;
                    Rectangle rectangle = new Rectangle(x, y, cellSize, cellSize);
                    if (rectangle.contains(e.getX(), e.getY())) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        return;
                    }
                }
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public void setMap(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(this.map.getGrid()[0].length*this.cellSize, this.map.getGrid().length*this.cellSize));
    }

    public Map getMap() {
        return map;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < map.getGrid().length; i++) {
            for (int j = 0; j < map.getGrid()[i].length; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                if (map.getGrid()[i][j] instanceof AbstractPlayer player) {
                    g.drawImage(Helper.getImageIcon("Factions/"+player.getClassName()+".png").getImage(), x, y, cellSize, cellSize,this);
                }else if (map.getGrid()[i][j] instanceof AbstractObstacle obstacle) {
                    g.drawImage(Helper.getImageIcon("Obstacles/"+obstacle.getClassName()+".png").getImage(), x, y, cellSize, cellSize,this);
                    this.drawHealthPoint(g, x, y, obstacle.getHealthPoint());
                }else if (map.getGrid()[i][j] instanceof AbstractMonster monster) {
                    g.drawImage(Helper.getImageIcon("Monsters/"+monster.getClassName()+".png").getImage(), x, y, cellSize, cellSize,this);
                    this.drawHealthPoint(g, x, y, monster.getHealthPoint());
                }else if (map.getGrid()[i][j] instanceof Wall) {
                    g.drawImage(Helper.getImageIcon("wall.png").getImage(), x, y, cellSize, cellSize,this);
                }else if (map.getGrid()[i][j] instanceof Exit) {
                    g.drawImage(Helper.getImageIcon("exit.png").getImage(), x, y, cellSize, cellSize,this);
                }
            }
        }

        for (Coordinate coordinate : possibleCoordinates) {
            int x = coordinate.getX() * cellSize;
            int y = coordinate.getY() * cellSize;
            if (isMove){
                g.setColor(new Color(0, 255, 0, 100));
                g.fillRect(x+1, y+1, cellSize, cellSize);
            } else {
                g.setColor(new Color(255, 0, 0, 100));
                g.fillRect(x+1, y+1, cellSize, cellSize);
            }
        }
    }

    public void updatePossibleCoordinates(List<Coordinate> possibleCoordinates, Boolean isMove) {
        this.possibleCoordinates = possibleCoordinates;
        this.isMove = isMove;
        repaint();
    }

    public void removePossibleCoordinates() {
        this.updatePossibleCoordinates(new ArrayList<>(), null);
    }

    private void drawHealthPoint(Graphics g, int x, int y, int healthPoint){
        g.setColor(new Color(255, 255, 255, 150));
        g.fillRect(x+1, y+28, 38, 11);
        g.setColor(new Color(160, 0, 0));
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString(String.valueOf(healthPoint), x+2, y+37);
    }
}

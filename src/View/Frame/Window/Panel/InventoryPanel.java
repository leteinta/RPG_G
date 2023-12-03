package src.View.Frame.Window.Panel;

import src.Model.Equipment.AbstractEquipment;
import src.Model.Equipment.Spells.AbstractSpell;
import src.Model.Equipment.Weapons.AbstractMeleeWeapon;
import src.Model.Equipment.Weapons.AbstractProjectileWeapon;
import src.Model.Pawn.AbstractPlayer;
import src.Service.NotifyObserver;
import src.Service.Translator;
import src.View.Frame.Tool.JButtonCustom;
import src.View.Frame.Tool.JPanelCustom;
import src.View.Frame.Window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private final Translator translator = Translator.getInstance();
    private final MainWindow mainWindow;
    private final AbstractPlayer player;
    public InventoryPanel(MainWindow mainWindow, AbstractPlayer player) {
        this.mainWindow = mainWindow;
        this.player = player;
        setVisible(false);
        setPreferredSize(new Dimension(600, 300));
        this.build();
    }

    public void build(){
        removeAll();
        JPanel panel = new JPanelCustom(new GridBagLayout(), 5, 5, 5, 5);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        for (int i=0; i<player.getEquipments().size(); i++) {
            AbstractEquipment equipment = player.getEquipments().get(i);
            JPanel equipmentPanel = new JPanelCustom(new BorderLayout(), 5, 5, 5, 5);
            String equipmentText;
            if(equipment instanceof AbstractSpell spell){
                equipmentText = translator.getLanguage().getText("inventory-item-spell", spell.getName(), spell.getDamage(), spell.getRange(), spell.getMagicPointConsumption(), spell.getPrice());
            }else if(equipment instanceof AbstractProjectileWeapon projectileWeapon){
                equipmentText = translator.getLanguage().getText("inventory-item-projectile-weapon", projectileWeapon.getName(), projectileWeapon.getDamage(), projectileWeapon.getRange(), projectileWeapon.getAmmo(), projectileWeapon.getAmmo(), projectileWeapon.getPrice());
            }else if(equipment instanceof AbstractMeleeWeapon meleeWeapon){
                equipmentText = translator.getLanguage().getText("inventory-item-melee-weapon", meleeWeapon.getName(), meleeWeapon.getDamage(), meleeWeapon.getRange(), meleeWeapon.getResistance(), meleeWeapon.getResistanceMax(), meleeWeapon.getPrice());
            }else{
                equipmentText = translator.getLanguage().getText("inventory-item", equipment.getName(), equipment.getDamage(), equipment.getRange(), equipment.getPrice());
            }
            JLabel label = new JLabel(equipmentText);
            equipmentPanel.add(label, BorderLayout.WEST);
            JButtonCustom button = new JButtonCustom(translator.getLanguage().getText("frame-inventory-button"), 5, 5, 5, 5);
            button.setColor(Color.BLACK, new Color(93, 93, 93), Color.WHITE);
            button.addActionListener(e -> {
                player.setEquipmentSelected(equipment);
                NotifyObserver.notify(translator.getLanguage().getText("frame-inventory-selected", equipment.getName()));
                mainWindow.updatePlayerPanel();
            });
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setBackground(null);
            buttonPanel.add(button);
            equipmentPanel.add(buttonPanel, BorderLayout.EAST);
            if(i%2 == 0){
                equipmentPanel.setBackground(new Color(200, 200, 200));
            }
            gbc.gridy = i;
            equipmentPanel.setPreferredSize(new Dimension(570, 40));
            panel.add(equipmentPanel, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(600, 290));
        add(scrollPane);
        revalidate();
    }
}

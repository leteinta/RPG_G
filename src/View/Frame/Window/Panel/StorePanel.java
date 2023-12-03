package src.View.Frame.Window.Panel;

import src.Model.Equipment.AbstractEquipment;
import src.Model.Equipment.Spells.AbstractSpell;
import src.Model.Equipment.Weapons.AbstractMeleeWeapon;
import src.Model.Equipment.Weapons.AbstractProjectileWeapon;
import src.Model.Pawn.AbstractPlayer;
import src.Model.Store;
import src.Service.Translator;
import src.View.Frame.Tool.JButtonCustom;
import src.View.Frame.Tool.JPanelCustom;
import src.View.Frame.Window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StorePanel extends JPanel {
    private final Store store = new Store();
    private final List<JButton> buttons = new ArrayList<>();

    public StorePanel(MainWindow mainWindow, AbstractPlayer player) {
        setVisible(false);
        setPreferredSize(new Dimension(600, 300));
        Translator translator = Translator.getInstance();

        JPanel panel = new JPanelCustom(new GridBagLayout(), 5, 5, 5, 5);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        for (int i=0; i<store.getEquipments().size(); i++) {
            AbstractEquipment equipment = store.getEquipments().get(i);
            JPanel equipmentPanel = new JPanelCustom(new BorderLayout(), 5, 5, 5, 5);
            String equipmentText;
            if(equipment instanceof AbstractSpell spell){
                equipmentText = translator.getLanguage().getText("store-item-spell", spell.getName(), spell.getDamage(), spell.getRange(), spell.getMagicPointConsumption(), spell.getPrice());
            }else if(equipment instanceof AbstractProjectileWeapon projectileWeapon){
                equipmentText = translator.getLanguage().getText("store-item-projectile-weapon", projectileWeapon.getName(), projectileWeapon.getDamage(), projectileWeapon.getRange(), projectileWeapon.getAmmo(), projectileWeapon.getAmmo(), projectileWeapon.getPrice());
            }else if(equipment instanceof AbstractMeleeWeapon meleeWeapon){
                equipmentText = translator.getLanguage().getText("store-item-melee-weapon", meleeWeapon.getName(), meleeWeapon.getDamage(), meleeWeapon.getRange(), meleeWeapon.getResistance(), meleeWeapon.getResistanceMax(), meleeWeapon.getPrice());
            }else{
                equipmentText = translator.getLanguage().getText("store-item", equipment.getName(), equipment.getDamage(), equipment.getRange(), equipment.getPrice());
            }
            JLabel label = new JLabel(equipmentText);
            equipmentPanel.add(label, BorderLayout.WEST);
            JButtonCustom button = new JButtonCustom(translator.getLanguage().getText("frame-store-buy"), 5, 5, 5, 5);
            button.setColor(Color.BLACK, new Color(93, 93, 93), Color.WHITE);
            button.setEnabled(player.getMoney() >= equipment.getPrice());
            button.addActionListener(e -> {
                if(player.buyEquipment(equipment)){
                    mainWindow.updatePlayerPanel();
                    updateButtons(player);
                }
            });
            buttons.add(button);
            equipmentPanel.add(button, BorderLayout.EAST);
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
    }

    public void updateButtons(AbstractPlayer player){
        for (int i=0; i<buttons.size(); i++) {
            buttons.get(i).setEnabled(player.getMoney() >= store.getEquipments().get(i).getPrice());
        }
    }
}

package src.View.Frame.Window.Panel;

import src.Model.Equipment.Spells.AbstractSpell;
import src.Model.Equipment.Weapons.AbstractMeleeWeapon;
import src.Model.Equipment.Weapons.AbstractProjectileWeapon;
import src.Model.Pawn.AbstractPlayer;
import src.Service.Translator;
import src.View.Frame.Tool.JLabelCustom;
import src.View.Frame.Tool.JPanelCustom;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerPanel extends JPanelCustom {
    protected static final Translator translator = Translator.getInstance();
    private final AbstractPlayer player;

    public PlayerPanel(AbstractPlayer player) {
        super(new BorderLayout(), 0, 0, 0, 0, Color.BLACK);
        this.player = player;
        this.build();
    }

    public void build(){
        removeAll();
        JPanel infoHeadPanel = new JPanelCustom(new FlowLayout(FlowLayout.LEFT, 0, 0), 0, 0, 0, 0, Color.BLACK);
        JLabel infoHeadLabel = new JLabelCustom(player.getName()+" ("+translator.getLanguage().getText("player-level", player.getLevel())+")", JLabel.LEFT, 0, 0, 0, 10, 18, Color.WHITE);
        infoHeadPanel.add(infoHeadLabel);

        add(infoHeadPanel, BorderLayout.NORTH);

        String equipment;
        if(player.getEquipmentSelected() instanceof AbstractSpell spell){
            equipment = translator.getLanguage().getText("inventory-item-spell", spell.getName(), spell.getDamage(), spell.getRange(), spell.getMagicPointConsumption());
        }else if(player.getEquipmentSelected() instanceof AbstractProjectileWeapon projectileWeapon){
            equipment = translator.getLanguage().getText("inventory-item-projectile-weapon", projectileWeapon.getName(), projectileWeapon.getDamage(), projectileWeapon.getRange(), projectileWeapon.getAmmo(), projectileWeapon.getAmmoMax());
        }else if(player.getEquipmentSelected() instanceof AbstractMeleeWeapon meleeWeapon){
            equipment = translator.getLanguage().getText("inventory-item-melee-weapon", meleeWeapon.getName(), meleeWeapon.getDamage(), meleeWeapon.getRange(), meleeWeapon.getResistance(), meleeWeapon.getResistanceMax());
        }else{
            equipment = translator.getLanguage().getText("inventory-item", player.getEquipmentSelected().getName(), player.getEquipmentSelected().getDamage(), player.getEquipmentSelected().getRange());
        }
        JLabel infoEquipmentLabel = new JLabelCustom(translator.getLanguage().getText("frame-player-equipment", equipment), JLabel.LEFT, 0, 0, 0, 0, 12, Color.WHITE);
        add(infoEquipmentLabel, BorderLayout.CENTER);

        JPanel infosPanel = new JPanelCustom(new FlowLayout(FlowLayout.LEFT, 0, 0), 0, 0, 0, 0, Color.BLACK);
        java.util.List<String> infos = List.of(
                translator.getLanguage().getText("player-health-point", player.getHealthPoint(), player.getHealthPointMax()),
                translator.getLanguage().getText("player-magic-point", player.getMagicPoint(), player.getMagicPointMax()),
                translator.getLanguage().getText("player-strength", player.getStrength()),
                translator.getLanguage().getText("player-experience", player.getExperiencePoint(), player.getExperiencePointMax()),
                translator.getLanguage().getText("player-move-speed", player.getMoveSpeed()),
                translator.getLanguage().getText("player-money", player.getMoney())
        );
        for(String info: infos) {
            JLabel infoLabel = new JLabelCustom(info, JLabel.LEFT, 0, 0, 0, 10, 12, Color.WHITE);
            infosPanel.add(infoLabel);
        }
        add(infosPanel, BorderLayout.SOUTH);
        revalidate();
    }
}
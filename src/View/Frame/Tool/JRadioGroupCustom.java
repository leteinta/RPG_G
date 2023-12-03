package src.View.Frame.Tool;

import java.util.*;

public class JRadioGroupCustom {
    private String value;
    private final Map<String, JRadioButtonCustom> radioButtons = new HashMap<>();

    public void addRadioButton(JRadioButtonCustom radioButton){
        this.radioButtons.put(radioButton.getValue(), radioButton);
    }

    public String getValue(){
        return this.value;
    }

    public void setButtonSelected(String value){
        if(this.value != null){
            this.radioButtons.get(this.value).deselect();
        }
        this.value = value;
    }
}

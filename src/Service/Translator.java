package src.Service;

import src.i18n.AbstractLanguage;
import src.i18n.Fr;

public class Translator {
    private static Translator instance;
    private AbstractLanguage language;

    public static Translator getInstance() {
        if(instance == null){
            instance = new Translator();
        }
        return instance;
    }

    private Translator() {}


    public AbstractLanguage getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        switch (language) {
            case "fr" -> this.language = new Fr();
            default -> this.language = null;
        }
    }
}

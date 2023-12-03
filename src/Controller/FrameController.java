package src.Controller;

import src.Service.NotifyObserver;
import src.Service.Translator;
import src.View.Frame.NotifyObserverFrame;
import src.View.Frame.Window.StartWindow;

public class FrameController{

    public static void main(String[] args) {
        Translator.getInstance().setLanguage("fr");
        NotifyObserver.initInstance(new NotifyObserverFrame());
        new StartWindow();
    }
}

package src.View.Terminal;

import src.View.INotifyObserverView;

public class NotifyObserverTerminal implements INotifyObserverView {
    @Override
    public void notify(String text) {
        System.out.println(text);
    }
}

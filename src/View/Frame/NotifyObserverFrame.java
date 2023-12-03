package src.View.Frame;

import src.View.INotifyObserverView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NotifyObserverFrame implements INotifyObserverView {
    public static final JTextArea observerArea = new JTextArea();
    private final List<String> messageHistory = new ArrayList<>();

    @Override
    public void notify(String text) {
        messageHistory.add(text);
        observerArea.setText("");
        for (String message : messageHistory) {
            observerArea.append(message + "\n");
        }
        observerArea.setCaretPosition(observerArea.getDocument().getLength());
    }
}

package src.Service;


import src.View.INotifyObserverView;

public class NotifyObserver {
    private static NotifyObserver instance;
    private final INotifyObserverView notifyObserverView;
    private final Translator translator;

    public static void initInstance(INotifyObserverView notifyObserverView){
        instance = new NotifyObserver(notifyObserverView);
    }

    private NotifyObserver(INotifyObserverView notifyObserverView) {
        this.notifyObserverView = notifyObserverView;
        this.translator = Translator.getInstance();
    }

    public static void notify(String key){
        instance.notifyObserverView.notify(instance.translator.getLanguage().getText(key));
    }

    public static void notify(String key, Object... params){
        String text = instance.translator.getLanguage().getText(key, params);
        instance.notifyObserverView.notify(text);
    }
}

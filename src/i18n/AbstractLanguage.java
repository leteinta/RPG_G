package src.i18n;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLanguage {
    protected Map<String, String> text = new HashMap<>();

    public String getText(String key) {
        return this.text.getOrDefault(key, key);
    }

    public String getText(String key, Object... params) {
        return String.format(this.text.getOrDefault(key, key), params);
    }
}

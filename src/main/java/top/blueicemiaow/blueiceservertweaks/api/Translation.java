package top.blueicemiaow.blueiceservertweaks.api;

import java.util.HashMap;

public record Translation(HashMap<String, String> map) {
    public Translation() {
        this(new HashMap<>());
    }

    public Translation(String[] languages, String[] values) {
        this();
        for (int i = 0; i < Math.min(languages.length, values.length); i++) {
            this.map.put(languages[i], values[i]);
        }
    }

    public static final String defaultLanguage = "en_us";
    public static final String defaultValue = "defaultValue";

    public String get(String language) {
        if (map.containsKey(language)) {
            return map.get(language);
        }
        if (map.containsKey(defaultLanguage)) {
            return map.get(defaultLanguage);
        }
        return defaultValue;
    }
}

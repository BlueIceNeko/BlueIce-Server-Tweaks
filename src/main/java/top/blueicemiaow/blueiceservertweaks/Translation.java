package top.blueicemiaow.blueiceservertweaks;

import java.util.HashMap;

public class Translation {
	public Translation(String[] languages, String[] values) {
		this.map = new HashMap<String, String>();
		if (languages.length > 0 && values.length > 0 && languages.length == values.length) {
			for (int i = 0; i < languages.length; i++) {
				this.map.put(languages[i], values[i]);
			}
		}
		if (!this.map.containsKey(defaultLanguage)) {
			this.map.put(defaultLanguage, "value");
		}
	}

	public static final String defaultLanguage = "en_us";
	public static String serverLanguage = "en_us";

	private HashMap<String, String> map;

	public String get(String language) {
		if (map.containsKey(language)) {
			return map.get(language);
		}
		return map.get(defaultLanguage);
	}

	public String get() {
		return get(serverLanguage);
	}
}

package top.blueicemiaow.blueiceservertweaks;

import java.util.Arrays;
import java.util.ArrayList;

public class Lists {
	private static String[] commandList = {"goto", "compass", "random", "crafting", "k", "fly", "head", "free", "gamma", "hat", "painting", "name", "map"};

	public static ArrayList<String> getCommandList() {
		return getList(commandList);
	}

	public static void addCommand(String command) {
		commandList = add(commandList, command);
	}

	private static String[] potionList = {
		"minecraft:night_vision",
		"minecraft:long_night_vision",
		"minecraft:invisibility",
		"minecraft:long_invisibility",
		"minecraft:leaping",
		"minecraft:long_leaping",
		"minecraft:fire_resistance",
		"minecraft:long_fire_resistance",
		"minecraft:swiftness",
		"minecraft:long_swiftness",
		"minecraft:slowness",
		"minecraft:long_slowness",
		"minecraft:water_breathing",
		"minecraft:long_water_breathing",
		"minecraft:healing",
		"minecraft:harming",
		"minecraft:poison",
		"minecraft:long_poison",
		"minecraft:regeneration",
		"minecraft:long_regeneration",
		"minecraft:strength",
		"minecraft:long_strength",
		"minecraft:weakness",
		"minecraft:long_weakness",
		"minecraft:slow_falling",
		"minecraft:long_slow_falling",
		"minecraft:luck"
	};
	private static Integer[] idList = {16, 16, 14, 14, 8, 8, 12, 12, 1, 1, 2, 2, 13, 13, 6, 7, 19, 19, 10, 10, 5, 5, 18, 18, 28, 28, 26};
	private static Integer[] durationList = {3600, 9600, 3600, 9600, 3600, 9600, 3600, 9600, 3600, 9600, 1800, 4800, 3600, 9600, 1, 1, 900, 1800, 900, 1800, 3600, 9600, 1800, 4800, 1800, 4800, 6000};

	public static ArrayList<String> getPotionList() {
		return getList(potionList);
	}

	public static ArrayList<Integer> getIdList() {
		return getList(idList);
	}

	public static ArrayList<Integer> getDurationList() {
		return getList(durationList);
	}

	public static void addStew(String potion, Integer id, Integer duration) {
		potionList = add(potionList, potion);
		idList = add(idList, id);
		durationList = add(durationList, duration);
	}

	private static String[] keyList = {"key"};
	private static Translation[] translationList = {getDefaultTranslation()};

	public static ArrayList<String> getKeyList() {
		return getList(keyList);
	}

	public static ArrayList<Translation> getTranslationList() {
		return getList(translationList);
	}

	public static Translation getTranslation(String key) {
		for (int i = 0; i < keyList.length; i++) {
			if (key.equals(keyList[i])) {
				return translationList[i];
			}
		}
		return translationList[0];
	}

	public static Translation getDefaultTranslation() {
		String[] en_us = {"en_us"};
		String[] value = {"value"};
		return new Translation(en_us, value);
	}

	public static void addTranslation(String key, Translation translation) {
		keyList = add(keyList, key);
		translationList = add(translationList, translation);
	}

	public static <T> ArrayList<T> getList(T[] array) {
		return new ArrayList<T>(Arrays.asList(array));
	}

	public static <T> boolean contains(T[] array, T data) {
		for (T t : array) {
			if (data.equals(t)) {
				return true;
			}
		}
		return false;
	}

	public static String[] add(String[] array, String data) {
		int length = array.length;
		String[] newArray = new String[length + 1];
		for (int i = 0; i < length; i++) {
			newArray[i] = array[i];
		}
		newArray[length] = data;
		return newArray;
	}

	public static Integer[] add(Integer[] array, Integer data) {
		int length = array.length;
		Integer[] newArray = new Integer[length + 1];
		for (int i = 0; i < length; i++) {
			newArray[i] = array[i];
		}
		newArray[length] = data;
		return newArray;
	}

	public static Translation[] add(Translation[] array, Translation data) {
		int length = array.length;
		Translation[] newArray = new Translation[length + 1];
		for (int i = 0; i < length; i++) {
			newArray[i] = array[i];
		}
		newArray[length] = data;
		return newArray;
	}

	public static String[] add(String[] array, String data, boolean check) {
		if (check && contains(array, data)) {
			return array;
		}
		return add(array, data);
	}

	public static Integer[] add(Integer[] array, Integer data, boolean check) {
		if (check && contains(array, data)) {
			return array;
		}
		return add(array, data);
	}

	public static Translation[] add(Translation[] array, Translation data, boolean check) {
		if (check && contains(array, data)) {
			return array;
		}
		return add(array, data);
	}
}

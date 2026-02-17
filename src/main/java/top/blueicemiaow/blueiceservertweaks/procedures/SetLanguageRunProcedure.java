package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;

import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import java.util.HashMap;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.IOException;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

import com.google.gson.Gson;

public class SetLanguageRunProcedure {
	public SetLanguageRunProcedure() {
		try {
			Path path = Paths.get(defaultLanguageFile);
			if (Files.exists(path)) {
				String serverLanguageJson;
				if (Files.isReadable(path)) {
					serverLanguageJson = new String(Files.readAllBytes(path));
				} else {
					serverLanguageJson = defaultLanguageJson;
				}
				Gson gson = new Gson();
				HashMap<String, String> language = gson.fromJson(serverLanguageJson, HashMap.class);
				Translation.serverLanguage = language.get("language");
			} else {
				Files.createFile(path);
				Files.write(path, defaultLanguageJson.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String defaultLanguageJson = "{\"language\": \"en_us\"}";
	public static final String defaultLanguageFile = "server_language.json";

	public static void execute(CommandContext<CommandSourceStack> arguments) {
		String language;
		try {
			language = MessageArgument.getMessage(arguments, "language").getString();
			Translation.serverLanguage = language;
			try {
				Path path = Paths.get(defaultLanguageFile);
				if (Files.exists(path)) {
					if (Files.isWritable(path)) {
						Files.write(path, ("{\"language\": \"" + language + "\"}").getBytes());
					}
				} else {
					Files.createFile(path);
					Files.write(path, defaultLanguageJson.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
	}
}

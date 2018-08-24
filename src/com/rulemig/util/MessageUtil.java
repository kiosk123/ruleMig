package com.rulemig.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MessageUtil {
	private final static Map<String, String> messageMap = new HashMap<>();
	
	public static void init(String propsPath) {
		Path path = Paths.get(propsPath);
		File file = path.toFile();
		if (!file.isFile() || !file.exists()) {
			
		}
	}
}

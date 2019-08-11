package com.fingermatch.utils;

import java.util.Base64;

import org.apache.logging.log4j.util.Strings;

public class UtilBase64Image {
	public static String encoder(byte imageData[]) {
		if (null != imageData && imageData.length > 0) {
			// Converting an Image byte array into Base64 String
			return Base64.getEncoder().encodeToString(imageData);
		}
		return null;
	}

	public static byte[] decoder(String base64Image) {
		if (null != base64Image && Strings.isNotEmpty(base64Image)) {
			// Converting a Base64 String into Image byte array
			return Base64.getDecoder().decode(base64Image);
		}
		return null;
	}
}

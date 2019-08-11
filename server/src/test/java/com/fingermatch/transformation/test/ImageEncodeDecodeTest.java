package com.fingermatch.transformation.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.fingermatch.utils.UtilBase64Image;
import com.machinezoo.noexception.Exceptions;

public class ImageEncodeDecodeTest {
	@Test
	public void encodeProbe() {
		String name = "probe.png";
		writeFile(UtilBase64Image.encoder(load(name)), name);
	}

	@Test
	public void encodeMatching() {
		String name = "matching.png";
		writeFile(UtilBase64Image.encoder(load(name)), name);
	}

	@Test
	public void encodeNonMatching() {
		String name = "nonmatching.png";
		writeFile(UtilBase64Image.encoder(load(name)), name);
	}

	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (InputStream input = FingerprintTemplateTest.class.getClassLoader().getResourceAsStream(name)) {
				return IOUtils.toByteArray(input);
			}
		});
	}

	private static void writeFile(String content, String name) {
		try (OutputStream out = new FileOutputStream("src/test/resources/" + name + "StringImageBase64Encoding.txt")) {

			out.write(content.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

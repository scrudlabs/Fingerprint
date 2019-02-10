package com.fingermatch.transformation.test;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.fingermatch.utils.UtilBase64Image;
import com.machinezoo.noexception.Exceptions;

public class ImageEncodeDecodeTest {
	@Test
	public void encodeProbe() {
		byte[] image = load("probe.png");
		System.out.println(UtilBase64Image.encoder(image));
	}
	
	@Test
	public void encodeMatching() {
		byte[] image = load("matching.png");
		System.out.println(UtilBase64Image.encoder(image));
	}
	
	@Test
	public void encodeNonMatching() {
		byte[] image = load("nonmatching.png");
		System.out.println(UtilBase64Image.encoder(image));
	}

	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (InputStream input = FingerprintTemplateTest.class.getClassLoader().getResourceAsStream(name)) {
				return IOUtils.toByteArray(input);
			}
		});
	}
}

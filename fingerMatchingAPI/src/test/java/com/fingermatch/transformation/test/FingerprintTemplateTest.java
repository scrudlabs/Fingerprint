
package com.fingermatch.transformation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.fingermatch.transformation.Cell;
import com.fingermatch.transformation.DoubleMap;
import com.fingermatch.transformation.FingerprintMatcher;
import com.fingermatch.transformation.FingerprintTemplate;
import com.fingermatch.transformation.ImmutableTemplate;
import com.fingermatch.transformation.Minutia;
import com.fingermatch.transformation.MinutiaType;
import com.fingermatch.transformation.TemplateBuilder;
import com.machinezoo.noexception.Exceptions;

public class FingerprintTemplateTest {
	private static FingerprintTemplate t = new FingerprintTemplate();

	public static FingerprintTemplate probe() {
		return new FingerprintTemplate().create(load("probe.png"));
	}

	public static FingerprintTemplate matching() {
		return new FingerprintTemplate().create(load("matching.png"));
	}

	public static FingerprintTemplate nonmatching() {
		return new FingerprintTemplate().create(load("nonmatching.png"));
	}

	public static FingerprintTemplate probeIso() {
		return new FingerprintTemplate().convert(load("iso-probe.dat"));
	}

	public static FingerprintTemplate matchingIso() {
		return new FingerprintTemplate().convert(load("iso-matching.dat"));
	}

	public static FingerprintTemplate nonmatchingIso() {
		return new FingerprintTemplate().convert(load("iso-nonmatching.dat"));
	}

	@Test
	public void constructor() {
		new FingerprintTemplate().create(load("probe.png"));
	}

	@Test
	public void decodeImage_png() {
		decodeImage_validate(TemplateBuilder.decodeImage(load("probe.png")));
	}

	@Test
	public void decodeImage_jpeg() {
		decodeImage_validate(TemplateBuilder.decodeImage(load("probe.jpeg")));
	}

	@Test
	public void decodeImage_bmp() {
		decodeImage_validate(TemplateBuilder.decodeImage(load("probe.bmp")));
	}

	@Test
	public void decodeImage_tiff() {
		decodeImage_validate(TemplateBuilder.decodeImage(load("probe.tiff")));
	}

	@Test
	public void decodeImage_wsq() {
		decodeImage_validate(TemplateBuilder.decodeImage(load("wsq-original.wsq")),
				TemplateBuilder.decodeImage(load("wsq-converted.png")));
	}

	private void decodeImage_validate(DoubleMap map) {
		decodeImage_validate(map, TemplateBuilder.decodeImage(load("probe.png")));
	}

	private void decodeImage_validate(DoubleMap map, DoubleMap reference) {
		assertEquals(reference.width, map.width);
		assertEquals(reference.height, map.height);
		double delta = 0, max = -1, min = 1;
		for (int x = 0; x < map.width; ++x) {
			for (int y = 0; y < map.height; ++y) {
				delta += Math.abs(map.get(x, y) - reference.get(x, y));
				max = Math.max(max, map.get(x, y));
				min = Math.min(min, map.get(x, y));
			}
		}
		assertTrue(max > 0.75);
		assertTrue(min < 0.1);
		assertTrue(delta / (map.width * map.height) < 0.01);
	}

	@Test
	public void json_roundTrip() {
		TemplateBuilder tb = new TemplateBuilder();
		tb.size = new Cell(800, 600);
		tb.minutiae = new Minutia[] { new Minutia(new Cell(100, 200), Math.PI, MinutiaType.BIFURCATION),
				new Minutia(new Cell(300, 400), 0.5 * Math.PI, MinutiaType.ENDING) };
		t.immutable = new ImmutableTemplate(tb);
		t = new FingerprintTemplate().deserialize(t.serialize());
		assertEquals(2, t.immutable.minutiae.length);
		Minutia a = t.immutable.minutiae[0];
		Minutia b = t.immutable.minutiae[1];
		assertEquals(new Cell(100, 200), a.position);
		assertEquals(Math.PI, a.direction, 0.0000001);
		assertEquals(MinutiaType.BIFURCATION, a.type);
		assertEquals(new Cell(300, 400), b.position);
		assertEquals(0.5 * Math.PI, b.direction, 0.0000001);
		assertEquals(MinutiaType.ENDING, b.type);
	}

	@Test
	public void randomScaleMatch() throws Exception {
		FingerprintMatcher matcher = new FingerprintMatcher().index(probe());
		DoubleMap original = TemplateBuilder.decodeImage(load("matching.png"));
		int clipX = original.width / 10;
		int clipY = original.height / 10;
		Random random = new Random(0);
		for (int i = 0; i < 10; ++i) {
			DoubleMap clipped = clip(original, random.nextInt(clipY), random.nextInt(clipX), random.nextInt(clipY),
					random.nextInt(clipX));
			double dpi = 500 + 2 * (random.nextDouble() - 0.5) * 200;
			DoubleMap scaled = TemplateBuilder.scaleImage(clipped, 500 * 1 / (dpi / 500));
			byte[] reencoded = encodeImage(scaled);
			FingerprintTemplate candidate = new FingerprintTemplate().dpi(dpi).create(reencoded);
			double score = matcher.match(candidate);
			assertTrue("Score " + score + " @ DPI " + dpi, score >= 40);
		}
	}

	private static DoubleMap clip(DoubleMap input, int top, int right, int bottom, int left) {
		DoubleMap output = new DoubleMap(input.width - left - right, input.height - top - bottom);
		for (int y = 0; y < output.height; ++y)
			for (int x = 0; x < output.width; ++x)
				output.set(x, y, input.get(left + x, top + y));
		return output;
	}

	private static byte[] encodeImage(DoubleMap map) throws IOException {
		int[] pixels = new int[map.width * map.height];
		for (int y = 0; y < map.height; ++y)
			for (int x = 0; x < map.width; ++x) {
				int color = (int) Math.round(255 * ((1 - map.get(x, y))));
				pixels[y * map.width + x] = (color << 16) | (color << 8) | color;
			}
		BufferedImage buffered = new BufferedImage(map.width, map.height, BufferedImage.TYPE_INT_RGB);
		buffered.setRGB(0, 0, map.width, map.height, pixels, 0, map.width);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ImageIO.write(buffered, "BMP", stream);
		return stream.toByteArray();
	}

	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (InputStream input = FingerprintTemplateTest.class.getClassLoader().getResourceAsStream(name)) {
				return IOUtils.toByteArray(input);
			}
		});
	}
}

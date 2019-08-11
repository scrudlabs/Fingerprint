package com.fingermatch.service;

import org.springframework.stereotype.Service;

import com.fingermatch.transformation.FingerprintMatcher;
import com.fingermatch.transformation.FingerprintTemplate;

@Service()
public class MatchingService {

	public boolean match(byte[] receivedImage, byte[] dbImage) {
		FingerprintTemplate receivedImageTemplate = new FingerprintTemplate().dpi(500).create(receivedImage);
		FingerprintTemplate dbImageTemplate = new FingerprintTemplate().dpi(500).create(dbImage);
		return new FingerprintMatcher().index(dbImageTemplate).match(receivedImageTemplate) >= 40;
	}
}

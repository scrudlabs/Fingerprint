package com.fingermatch.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fingermatch.domain.UserElectronicId;
import com.fingermatch.dto.UserElectronicIdDTO;
import com.fingermatch.service.MatchingService;
import com.fingermatch.service.UserInfosService;
import com.fingermatch.utils.UtilBase64Image;

/**
 * REST controller for matching two userelectronicId images.
 */
@RestController
@RequestMapping("/api")
public class FingerPrintMatcherRest {

	private final Logger log = LoggerFactory.getLogger(FingerPrintMatcherRest.class);

	@Autowired
	private UserInfosService userInfosService;

	@Autowired
	private MatchingService matchingService;

	@PostMapping(path = "/storeUserFingerPrintInformations", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> storeFingerPrintInDatabase(@Valid @RequestBody UserElectronicIdDTO userElectronicId) {
		if (null != userElectronicId) {
			boolean found = false;
			for (UserElectronicId dbUserElectronicId : userInfosService.getAllUserElectronicIds()) {
				if (matchingService.match(UtilBase64Image.decoder(userElectronicId.getFingerPrintImage()),
						dbUserElectronicId.getFingerPrintImage())) {
					found = true;
					break;
				}
			}
			if (!found) {
				userInfosService.createUserElectronicId(userElectronicId.getFingerPrintImage(),
						userElectronicId.getPassportImage());
				return new ResponseEntity<>("New UserElectronicId successfully stored", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("UserElectronicId already exists", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("NO CONTENT", HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/getUserInformations", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> getUserInfoWithFingerPrintImage(@Valid @RequestBody String userFingerPrint) {

		// TODO
		return new ResponseEntity<>("FingerPrint image successfully stored", HttpStatus.OK);
	}

}

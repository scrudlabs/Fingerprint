package com.fingermatch.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fingermatch.domain.UserElectronicId;
import com.fingermatch.repository.InfosRepository;
import com.fingermatch.utils.UtilBase64Image;

@Service()
@Transactional
public class UserInfosService {

	private final Logger log = LoggerFactory.getLogger(UserInfosService.class);

	@Autowired
	private InfosRepository infosRepository;

	public UserInfosService() {
	}

	public UserElectronicId createUserElectronicId(String fingerPrintImage, String passportImage) {

		UserElectronicId newUserElectronicId = new UserElectronicId();
		newUserElectronicId.setFingerPrintImage(UtilBase64Image.decoder(fingerPrintImage));
		newUserElectronicId.setPassportImage(UtilBase64Image.decoder(passportImage));
		newUserElectronicId.setCreatedDate((new Date()).toInstant());
		infosRepository.save(newUserElectronicId);
		log.debug("New UserElectronicId Information created.");
		return newUserElectronicId;
	}

	@Transactional(readOnly = true)
	public List<UserElectronicId> getAllUserElectronicIds() {
		return infosRepository.findAll();
	}

}

package com.fingermatch.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fingermatch.dto.UserElectronicIdDTO;
import com.fingermatch.utils.UtilBase64Image;

@RestController
@RequestMapping("/home")
public class WebappController {

	private String message = "Hello World";
	
	@Autowired
	FingerPrintMatcherRest restService;

	@RequestMapping("/index")
	public ModelAndView welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return new ModelAndView("index");
	}

	@RequestMapping("/upload")
	public ModelAndView upload(ModelMap model, @RequestParam MultipartFile myFile) throws IOException {
		
		ResponseEntity<?> reponse = restService.storeFingerPrintInDatabase(new UserElectronicIdDTO(UtilBase64Image.encoder(myFile.getBytes()), "toto"));
		ModelAndView mav = new ModelAndView("uploadResult");
		mav.addObject("result", reponse.getBody());
		return mav;
	}

}

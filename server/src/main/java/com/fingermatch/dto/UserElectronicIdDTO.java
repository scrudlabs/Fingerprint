package com.fingermatch.dto;

public class UserElectronicIdDTO {

	private String fingerPrintImage;

	private String passportImage;

	public UserElectronicIdDTO() {
	}

	public UserElectronicIdDTO(String fingerPrintImagecoder, String passportImage) {
		this.fingerPrintImage = fingerPrintImagecoder;
		this.passportImage = passportImage;
	}

	/**
	 * @return the fingerPrintImage
	 */
	public String getFingerPrintImage() {
		return fingerPrintImage;
	}

	/**
	 * @param fingerPrintImage the fingerPrintImage to set
	 */
	public void setFingerPrintImage(String fingerPrintImage) {
		this.fingerPrintImage = fingerPrintImage;
	}

	/**
	 * @return the passportImage
	 */
	public String getPassportImage() {
		return passportImage;
	}

	/**
	 * @param passportImage the passportImage to set
	 */
	public void setPassportImage(String passportImage) {
		this.passportImage = passportImage;
	}

}
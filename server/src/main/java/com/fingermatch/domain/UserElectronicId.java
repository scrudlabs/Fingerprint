package com.fingermatch.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_infos")
public class UserElectronicId {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_infos_seq")
	private Long id;

	@NotNull
	@Lob
	@Column(name = "finger_print_image", columnDefinition="bytea")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] fingerPrintImage;

	@NotNull
	@Lob
	@Column(name = "passport_image", columnDefinition="bytea")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] passportImage;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	@JsonIgnore
	private Instant createdDate;

	public UserElectronicId() {
	}

	/**
	 * @return the fingerPrintImage
	 */
	public byte[] getFingerPrintImage() {
		return fingerPrintImage;
	}

	/**
	 * @param fingerPrintImage the fingerPrintImage to set
	 */
	public void setFingerPrintImage(byte[] fingerPrintImage) {
		this.fingerPrintImage = fingerPrintImage;
	}

	/**
	 * @return the passportImage
	 */
	public byte[] getPassportImage() {
		return passportImage;
	}

	/**
	 * @param passportImage the passportImage to set
	 */
	public void setPassportImage(byte[] passportImage) {
		this.passportImage = passportImage;
	}

	/**
	 * @return the createdDate
	 */
	public Instant getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

}
package com.joshuaism.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preferences")
public class HomeDto {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(length=20)
	private String marsApiRoverData;
	private Integer marsSol;
	private Boolean fhaz;
	private Boolean rhaz;
	private Boolean mast;
	private Boolean chemcam;
	private Boolean mahli;
	private Boolean mardi;
	private Boolean navcam;
	private Boolean pancam;
	private Boolean minites;
	
	public HomeDto() {
		setMarsApiRoverData("Curiosity");
		setMarsSol(0);
	}
	
	public String getMarsApiRoverData() {
		return marsApiRoverData;
	}
	public void setMarsApiRoverData(String marsApiRoverData) {
		this.marsApiRoverData = marsApiRoverData;
	}
	public Integer getMarsSol() {
		return marsSol;
	}
	public void setMarsSol(Integer marsSol) {
		this.marsSol = marsSol;
	}
	public Boolean getFhaz() {
		return fhaz;
	}
	public void setFhaz(Boolean fhaz) {
		this.fhaz = fhaz;
	}
	public Boolean getRhaz() {
		return rhaz;
	}
	public void setRhaz(Boolean rhaz) {
		this.rhaz = rhaz;
	}
	public Boolean getMast() {
		return mast;
	}
	public void setMast(Boolean mast) {
		this.mast = mast;
	}
	public Boolean getChemcam() {
		return chemcam;
	}
	public void setChemcam(Boolean chemcam) {
		this.chemcam = chemcam;
	}
	public Boolean getMahli() {
		return mahli;
	}
	public void setMahli(Boolean mahli) {
		this.mahli = mahli;
	}
	public Boolean getMardi() {
		return mardi;
	}
	public void setMardi(Boolean mardi) {
		this.mardi = mardi;
	}
	public Boolean getNavcam() {
		return navcam;
	}
	public void setNavcam(Boolean navcam) {
		this.navcam = navcam;
	}
	public Boolean getMinites() {
		return minites;
	}
	public void setMinites(Boolean minites) {
		this.minites = minites;
	}
	public Boolean getPancam() {
		return pancam;
	}
	public void setPancam(Boolean pancam) {
		this.pancam = pancam;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "HomeDto [userId=" + userId + ", marsApiRoverData=" + marsApiRoverData + ", marsSol="
				+ marsSol + ", fhaz=" + fhaz + ", rhaz=" + rhaz + ", mast=" + mast + ", chemcam=" + chemcam + ", mahli="
				+ mahli + ", mardi=" + mardi + ", navcam=" + navcam + ", pancam=" + pancam + ", minites=" + minites
				+ "]";
	}
	
	

}

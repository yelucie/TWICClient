package com.beans;

import lombok.Data;

@Data
public class Ville {
	private String codeCommune;
	private String nomCommune;
	private String codePostal;
	private String libelleAcheminement;
	private String ligne;
	private String latitude;
	private String longitude;
	private String meteo;
	private Double temperature;

	public String toString() {
		return String.format("INSEE %1$s - %2$s %3$s", getCodeCommune(), getCodePostal(), getNomCommune());
	}
}

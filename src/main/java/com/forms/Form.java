package com.forms;

import com.beans.Ville;
import com.services.VilleBlo;

import jakarta.servlet.http.HttpServletRequest;

public class Form {

	private Ville ville;

	public Ville getVille() {
		return this.ville;
	}

	public String getResult(HttpServletRequest request, VilleBlo villeBlo, String option) {
		ville = new Ville();
		
		ville.setCodeCommune(request.getParameter("codeCommune"));
		ville.setNomCommune(request.getParameter("nomCommune"));
		ville.setCodePostal(request.getParameter("codePostal"));
		ville.setLibelleAcheminement(request.getParameter("libelle"));
		ville.setLigne(request.getParameter("ligne"));
		ville.setLatitude(request.getParameter("latitude"));
		ville.setLongitude(request.getParameter("longitude"));

		if ("ajouter".equals(option)) {
			villeBlo.addVille(ville);
			return ville + " a été ajoutée.";
		} else if ("enregistrer".equals(option)) {
			villeBlo.updateVille(ville.getCodeCommune(), ville);
			return ville + " a été modifiée.";
		}

		return "Une ville ayant pour code INSEE " + request.getParameter("codeCommune") + " existe déjà.";
	}

}

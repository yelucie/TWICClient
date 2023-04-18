package com.forms;

import com.beans.Ville;
import com.services.VilleBlo;

import jakarta.servlet.http.HttpServletRequest;

public class FormDistance {

	public String getResult(HttpServletRequest request, VilleBlo villeBlo) {
		String defaut = "Def";
		String villeDepParam = request.getParameter("villeDep");
		String villeArrParam = request.getParameter("villeArr");
		
		if (!defaut.equals(villeDepParam) && !defaut.equals(villeArrParam)) {
			Ville villeDep = villeBlo.getVille(request.getParameter("villeDep"));
			Ville villeArr = villeBlo.getVille(request.getParameter("villeArr"));

			if (villeDepParam.equals(villeArrParam)) {
				return "Il s'agit de la même ville.";
			} else {
				double distance = getDistance(
						Double.parseDouble(villeDep.getLatitude()),
						Double.parseDouble(villeArr.getLatitude()),
						Double.parseDouble(villeDep.getLongitude()),
						Double.parseDouble(villeArr.getLongitude()));

				return String.format("Il y a %,.3f km entre %2$s et %3$s.", distance, villeDep.getNomCommune(), villeArr.getNomCommune());
			}
		}

		return "Merci de choisir une ville de départ et une ville d'arrivée";
	}
	
	public static double getDistance(double lat1, double lat2, double lon1, double lon2) {
	    final int R = 6371; // Rayon de la Terre

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance)/1000;
	}
}
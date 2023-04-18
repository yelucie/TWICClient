package com.dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.beans.Meteo;
import com.beans.Ville;
import com.exceptions.DaoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.MeteoBlo;

public class VilleDao {

	private MeteoBlo meteoBlo;

	public VilleDao() {
		this.meteoBlo = new MeteoBlo();
	}

	public int getPageSize() {
		int totalSize = 0;

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8181")).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject json = new JSONObject(response.body());
			totalSize = json.getInt("totalPages");
		} catch (IOException | InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new DaoException("Le nombre de pages n'a pas pu être obtenu.", e);
		}
		return totalSize;
	}

	public List<Ville> getAllVilles() {
		List<Ville> villes = new ArrayList<>();

		for (int i = 0; i < this.getPageSize(); i++) {
			villes.addAll(this.getVillesByPage(i, false));
		}

		return villes;
	}

	public List<Ville> getVillesByPage(int pageNo, boolean optionMeteo) {
		List<Ville> villes = new ArrayList<>();

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8181?pageNo=" + pageNo))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject json = new JSONObject(response.body());
			JSONArray content = json.getJSONArray("content");
			content.forEach(item -> {
				Ville ville = mapToBean((JSONObject) item);
				if (optionMeteo) {
					Meteo meteo = meteoBlo.getMeteo(Float.parseFloat(ville.getLatitude()),
							Float.parseFloat(ville.getLongitude()));
					ville.setTemperature(meteo.getTemp());
					ville.setMeteo(meteo.getMeteo());
				}
				villes.add(ville);
			});
		} catch (IOException | InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new DaoException("Les villes n'ont pas pu être obtenues.", e);
		}

		return villes;
	}

	public Ville getVille(String code) {
		Ville ville = new Ville();

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().GET()
				.uri(URI.create("http://localhost:8181/ville?codeCommune=" + code)).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject json = new JSONObject(response.body());
			ville = mapToBean(json);

		} catch (IOException | InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new DaoException("La ville de code INSEE " + code + " n'a pas pu être obtenue.", e);
		}

		return ville;
	}

	public Ville addVille(Ville ville) {
		if (!exists(ville.getCodeCommune())) {
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(mapToJson(ville)))
					.uri(URI.create("http://localhost:8181/ville/add")).build();

			try {
				client.send(request, HttpResponse.BodyHandlers.ofString());
				return ville;
			} catch (IOException | InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new DaoException(ville + " n'a pas pu être ajoutée.", e);
			}
		}
		return null;
	}

	public Ville updateVille(String codeCommune, Ville ville) {
		if (exists(codeCommune)) {
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json")
					.PUT(HttpRequest.BodyPublishers.ofString(mapToJson(ville)))
					.uri(URI.create("http://localhost:8181/ville?codeCommune=" + codeCommune)).build();

			try {
				client.send(request, HttpResponse.BodyHandlers.ofString());
				return ville;
			} catch (IOException | InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new DaoException(ville + " n'a pas pu être mise à jour.", e);
			}
		}
		return null;
	}

	public boolean deleteVille(String codeCommune) {
		if (exists(codeCommune)) {
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json").DELETE()
					.uri(URI.create("http://localhost:8181/ville?codeCommune=" + codeCommune)).build();

			try {
				client.send(request, HttpResponse.BodyHandlers.ofString());
				return true;
			} catch (IOException | InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new DaoException("La suppression n'a pas pu être effectuée.", e);
			}
		}
		return false;
	}

	private boolean exists(String codeCommune) {
		for (Ville v : getAllVilles()) {
			if (v.getCodeCommune().equals(codeCommune)) {
				return true;
			}
		}
		return false;
	}

	private Ville mapToBean(JSONObject jsonObj) {
		Ville ville = new Ville();

		ville.setCodeCommune(jsonObj.getString("codeCommune"));
		ville.setNomCommune(jsonObj.getString("nomCommune"));
		ville.setCodePostal(jsonObj.getString("codePostal"));
		ville.setLibelleAcheminement(jsonObj.getString("libelleAcheminement"));
		ville.setLigne(jsonObj.getString("ligne"));
		ville.setLatitude(jsonObj.getString("latitude"));
		ville.setLongitude(jsonObj.getString("longitude"));

		return ville;
	}

	private String mapToJson(Ville ville) {
		ObjectMapper mapper = new ObjectMapper();
		String body = "{}";

		try {
			body = mapper.writeValueAsString(ville);
		} catch (IOException e) {
			throw new DaoException(ville + "n'a pas pu être convertie en format JSON.", e);
		}
		return body;
	}
}
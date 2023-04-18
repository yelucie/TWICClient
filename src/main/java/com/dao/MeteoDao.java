package com.dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.beans.Meteo;
import com.exceptions.DaoException;

public class MeteoDao {

	public Meteo getMeteo(float latitude, float longitude) {
		Meteo meteo = new Meteo();

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().GET()
				.uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=236bc9a8a7b6b2eb2c315a95d72290eb"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			JSONObject json = new JSONObject(response.body());

			JSONArray weatherArray = json.getJSONArray("weather");
			String weather = weatherArray.getJSONObject(0).getString("main");

			JSONObject mainArray = json.getJSONObject("main");
			Double temp = mainArray.getDouble("temp");

			meteo.setTemp((temp - 32) / 1.8);
			meteo.setMeteo(weather);
		} catch (IOException | InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new DaoException("La météo n'a pas pu être obtenue.", e);
		}

		return meteo;
	}
}

package com.services;

import com.beans.Meteo;
import com.dao.MeteoDao;

public class MeteoBlo {
	
	private MeteoDao meteoDao;
	
	public MeteoBlo() {
		meteoDao = new MeteoDao();
	}

	public Meteo getMeteo(float latitude, float longitude) {
		return meteoDao.getMeteo(latitude, longitude);
	}
}

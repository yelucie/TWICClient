package com.services;

import java.util.List;

import com.beans.Ville;
import com.dao.VilleDao;

public class VilleBlo {

	private VilleDao villeDao;

	public VilleBlo() {
		villeDao = new VilleDao();
	}

	public int getPageSize() {
		return villeDao.getPageSize();
	}

	public List<Ville> getAllVilles() {
		return villeDao.getAllVilles();
	}

	public List<Ville> getVillesByPage(int pageNo, boolean optionMeteo) {
		return villeDao.getVillesByPage(pageNo, optionMeteo);
	}

	public Ville getVille(String code) {
		return villeDao.getVille(code);
	}

	public Ville addVille(Ville ville) {
		return villeDao.addVille(ville);
	}

	public Ville updateVille(String codeCommune, Ville ville) {
		return villeDao.updateVille(codeCommune, ville);
	}

	public boolean deleteVille(String codeCommune) {
		return villeDao.deleteVille(codeCommune);
	}
}

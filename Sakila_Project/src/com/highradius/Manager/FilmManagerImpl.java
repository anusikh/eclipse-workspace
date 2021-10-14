package com.highradius.Manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.highradius.Action.Response;
import com.highradius.DAO.FilmDao;

public class FilmManagerImpl implements FilmManager {
	FilmDao filmDao;

	public FilmDao getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	@Override
	public HashMap<Integer, ArrayList<Response>> fetchData(int start, int limit, String title, String director,
			String releaseYear, String language) {
		return filmDao.fetchData(start, limit, title, director, releaseYear, language);
	}

	@Override
	public ArrayList<Response> fetchOtherData(ArrayList<Response> myData, String type) {
		return filmDao.fetchOtherData(myData, type);
	}

	@Override
	public Response deleteData(String filmId) {
		return filmDao.deleteData(filmId);
	}

	
	public Response addData(Response filmData) {
		return filmDao.addData(filmData);
	}
	
	public Response editData(Response filmData) {
		return filmDao.editData(filmData);
	}


}

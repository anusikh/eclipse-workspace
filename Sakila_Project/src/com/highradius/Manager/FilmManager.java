package com.highradius.Manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.highradius.Action.Response;

public interface FilmManager {
	HashMap<Integer, ArrayList<Response>> fetchData(int start, int limit, String title, String director,
			String releaseYear, String language);

	ArrayList<Response> fetchOtherData(ArrayList<Response> myData, String type);

	Response deleteData(String filmId);

	Response addData(Response filmData);

	Response editData(Response filmData);
}

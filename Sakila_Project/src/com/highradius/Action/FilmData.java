package com.highradius.Action;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.Manager.FilmManagerImpl;

public class FilmData {

	private ArrayList<Response> data = new ArrayList<>();
	int start;
	int limit;
	int count;
	private String title;
	private String releaseYear;
	private String director;
	private String language;
	private String type;
	ArrayList<Response> myData = new ArrayList<>();
	private Response successresponse = new Response();
	String[] specialFeatures;
	String rating;
	String description;
	String filmId;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFilmId() {
		return filmId;
	}

	public void setFilmId(String filmId) {
		this.filmId = filmId;
	}

	public Response getSuccessresponse() {
		return successresponse;
	}

	public void setSuccessresponse(Response successresponse) {
		this.successresponse = successresponse;
	}

	public String[] getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String[] specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Response> getMyData() {
		return myData;
	}

	public void setMyData(ArrayList<Response> myData) {
		this.myData = myData;
	}

	public ArrayList<Response> getData() {
		return data;
	}

	public void setData(ArrayList<Response> data) {
		this.data = data;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String fetchData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilmManagerImpl filmData = (FilmManagerImpl) context.getBean("FilmData");
		HashMap<Integer, ArrayList<Response>> dataMap = filmData.fetchData(start, limit, title, director, releaseYear,language);
		for (int key : dataMap.keySet()) {
			count = key;
			data = dataMap.get(key);
		}
		((ClassPathXmlApplicationContext) context).close();
		success = true;
		return "success";
	}

	public String fetchOtherData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilmManagerImpl filmData = (FilmManagerImpl) context.getBean("FilmData");
		myData = filmData.fetchOtherData(myData, type);
		((ClassPathXmlApplicationContext) context).close();
		success = true;
		return "success";
	}
	
	public String addData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilmManagerImpl filmData = (FilmManagerImpl) context.getBean("FilmData");
		String specialFeaturesstring = "";
		for (int index = 0; index < specialFeatures.length; index++)
			specialFeaturesstring += "," + specialFeatures[index];
		specialFeaturesstring = specialFeaturesstring.substring(1);
		Response obj = new Response(title, releaseYear, director, language, specialFeaturesstring, rating, description);
		successresponse = filmData.addData(obj);
		((ClassPathXmlApplicationContext) context).close();
		if (successresponse.getStatus()) {
			success = true;
			return "success";
		}
		success = false;
		return "failure";
	}

	public String deleteData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilmManagerImpl filmData = (FilmManagerImpl) context.getBean("FilmData");
		successresponse = filmData.deleteData(filmId);
		((ClassPathXmlApplicationContext) context).close();
		if (successresponse.getStatus()) {
			success = true;
			return "success";
		}
		success = false;
		return "failure";
	}

	public FilmData() {
		super();
	}

	public String editData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilmManagerImpl filmData = (FilmManagerImpl) context.getBean("FilmData");
		String specialFeaturesstring = "";
		for (int index = 0; index < specialFeatures.length; index++) {
			specialFeaturesstring += "," + specialFeatures[index];
		}
		specialFeaturesstring = specialFeaturesstring.substring(1);
		Response obj = new Response(title, releaseYear, director, language, specialFeaturesstring, rating,
				description, Integer.parseInt(filmId));
		successresponse = filmData.editData(obj);
		((ClassPathXmlApplicationContext) context).close();
		if (successresponse.getStatus()) {
			success = true;
			return "success";
		}
		success = false;
		return "failure";
	}

}

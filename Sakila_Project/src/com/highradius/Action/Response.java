package com.highradius.Action;

public class Response {
	
	String title;
	String description;
	String releaseYear;
	String languageId;
	String director;
	String specialFeatures;
	String name;
	String languageName;
	boolean success;
	String message;
	String rating;
	int filmId;
	int isDeleted;
	
	public Response() {
		super();
	}

	public Response(String title, String releaseYear, String director, String language, String specialFeatures,
			String rating, String description) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.director = director;
		this.languageId = language;
		this.specialFeatures = specialFeatures;
		this.rating = rating;
		this.description = description;
		this.isDeleted = 0;
	}
	
	public Response(String title, String releaseYear, String director, String language, String specialFeatures,
			String rating, String description, int filmId) {
		super();
		this.title = title;
		this.releaseYear = releaseYear;
		this.director = director;
		this.languageId = language;
		this.specialFeatures = specialFeatures;
		this.rating = rating;
		this.description = description;
		this.filmId = filmId;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setStatus(boolean status) {
		this.success = status;
	}

	public boolean getStatus() {
		return success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public void setLanguageName(String languageName) {
		this.name = languageName;
	}
	
	public String getLanguageId() {
		return languageId;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getDirector() {
		return director;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
}

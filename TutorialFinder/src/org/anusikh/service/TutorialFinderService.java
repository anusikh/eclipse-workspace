package org.anusikh.service;

public class TutorialFinderService {

	public String getBestTutorialSite(String input) {
		if (input.equals("java"))
			return input;
		else
			return "Language Not Available";
	}
}

package org.anusikh.action;

import org.anusikh.service.TutorialFinderService;

public class TutorialAction {

	private String bestTutorialSite;
	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getBestTutorialSite() {
		return bestTutorialSite;
	}

	public void setBestTutorialSite(String bestTutorialSite) {
		this.bestTutorialSite = bestTutorialSite;
	}

	public String execute() {
		TutorialFinderService tutorialFinderService = new TutorialFinderService();
		System.out.println(input);
		setBestTutorialSite(tutorialFinderService.getBestTutorialSite(input));
		return "success";
	}

	public String execute2() {
		System.out.println("The other method has been called");
		return "success";
	}
}

package com.anusikh.sample;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {

	// You can use @Autowired annotation on properties to get rid of the setter
	// methods.
//	@Autowired
//	private SpellChecker spellChecker;

	// You can use @Autowired annotation on setter methods to get rid of the
	// <property> element in XML configuration file.
//	@Autowired
//	public void setSpellChecker(SpellChecker spellChecker) {
//		this.spellChecker = spellChecker;
//	}

	
	private SpellChecker spellChecker;
	
	//You can apply @Autowired to constructors as well. A constructor @Autowired annotation indicates that the 
	//constructor should be autowired when creating the bean, even if no <constructor-arg> elements are used 
	//while configuring the bean in XML file.

	@Autowired
	public TextEditor(SpellChecker spellChecker) {
		System.out.println("Inside TextEditor constructor.");
		this.spellChecker = spellChecker;
	}

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}

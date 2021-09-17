package com.anusikh.springtuts;

import org.springframework.stereotype.Component;

//Autowired on Properties means, no need of setter method for spellCheck
//Autowired on Setter means, no need of property tags

@Component
public class Tyre {

	private String brand;

//	public Tyre(String brand) {
//		super();
//		this.brand = brand;
//	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
//		return "Tyre [brand=" + brand + "]";
		return "It's Working..";
	}

}

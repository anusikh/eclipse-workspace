package com.anusikh.springtuts;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {

	public void drive() {
		System.out.println("Bhag rha hai");
	}
}

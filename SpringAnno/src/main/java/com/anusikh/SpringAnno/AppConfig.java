package com.anusikh.SpringAnno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.anusikh.SpringAnno")
public class AppConfig {

//	@Bean
//	public Samsung getPhone() {
//		return new Samsung();
//	}
//
//	// This is equivalent to, <bean id="phone" class='Samsung'>
//
//	@Bean
//	public MobileProcessor getProcessor() {
//		return new Snapdragon();
//	}
}

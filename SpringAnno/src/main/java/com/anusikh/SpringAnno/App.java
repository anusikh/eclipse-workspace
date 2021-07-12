package com.anusikh.SpringAnno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// We need to create a config class called AppConfig.java first, with
		// @Configuration Annotation
		ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		Samsung s7 = (Samsung) factory.getBean(Samsung.class);
		s7.config();

		// =================================================================

		// Now lets create an Interface MobileProcessor
		// Create class Snapdragon with interface implemeted
		// Create MobileProccessor Object in Samsung class with getter/setter and make
		// sure it is @Autowired
		// Create a new function with @Bean Anno. in AppConfig.java
		// Now Run it

		// =================================================================

		// Now if u want to avoid using @Bean anno. in AppConfig, add @Component to the
		// Classes and add @ComponentScan(basePackages="com.anusikh.SpringAnno") in
		// AppConfig

		// Now if we create another Class called MediaTek implementing Interface
		// MobileProcessor, the we will need to add the @Primary tag to make sure which
		// one is called first

		// If you dont want to use Primary, then just use @Qualifier("mediatek") in
		// Samsung Class

	}
}

package com.anusikh.springtuts;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		Car car = new Car();
//		car.drive();

		// Now in order to use the Bike class i will need to change my source code here.
		// The solution is Dependency Injection where we create an interface Vehicle
		// Make the classes Car and Bike implement Vehicle

		// =======================================================================

		// APPLICATION CONTEXT
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		Vehicle obj = (Vehicle) context.getBean("vehicle");
//		obj.drive();

		// Now when we run the above code we will get an error because we will first
		// need to create an xml file
		// to determine which class is called Bike or Car
		// Hence we have created spring.xml with the class name in the <bean>

		// ======================================================================

		// ANNOTATION BASED CONFIG
		// We can do the same without writing anything in the xml file using annotations
		// remove the <bean> tag from spring.xml

//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		Vehicle obj = (Vehicle) context.getBean("bike");
//		obj.drive();

		// To make the above code work we need to add @Component in the Class files
		// And also <context:component-scan base-package="com.anusikh.springtuts">
		// ALl you need to do is mention the class name with small letters in the
		// getBean() function

		// ======================================================================

		// BEAN PROPERTY
		// Create a new class called Tyre and create a variable in it and create
		// getter/setters
		// Then add the <bean> tage along with the <property> tag

//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		Tyre t = (Tyre) context.getBean("tyre");
//		System.out.println(t);

		// ======================================================================

		// CONSTRUCTOR INJECTION
		// Create a constructor inside the Tyre class
		// Instead of using <property>, use <constructor-arg>

//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		Tyre t = (Tyre) context.getBean("tyre");
//		System.out.println(t);

		// =======================================================================

		// AUTOWIRED
		// Remove the constructor from Tyre class and the <property> tag from spring.xml

//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		Car obj = (Car) context.getBean("car");
//		obj.drive();

		// In Car class, create an object of Tyre and create its getter/Setter
		// Add @Autowired at the top, the control will flow into spring.xml, check the
		// bean for tyre and print "It's Working.."
		// Now if u dont want to use the spring.xml, add @Component on Tyre class too

		// =======================================================================

		// CONFIG BEAN (Check the SpringAnno Project)

	}
}

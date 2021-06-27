package com.anusikh.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		//Autowired Example
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		TextEditor te = (TextEditor) context.getBean("textEditor");
//		te.spellCheck();
		
		//Property Tag Example
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Tyre t = (Tyre) context.getBean("tyre");
		System.out.println(t);
	}
}

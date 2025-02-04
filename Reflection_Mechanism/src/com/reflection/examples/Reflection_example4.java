package com.reflection.examples;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Reflection_example4 {

	
	private int empid=40084215;
	
	private String emp_name="Gella Kiran Kumar";
	
	
	
	public static void main(String[] args) {
		
		
		// Accessing variables and identify access modifiers for that datatype using reflection
		
		//We can access to Private access modifier data and we can change it into public
		
	    
		Reflection_example4 rf = new Reflection_example4();
		
		
		Class c = rf.getClass();
		
		Field [] f=c.getDeclaredFields();
		
		
		
		for(Field f1 : f) {
			
		//System.out.println(f1);
			
			//Here f1.getName (getting name of Filed)
			
			//Here f1.getModifiers() (getting type of modifier to respective field)
			
			System.out.println(f1.getName() +"--->"+Modifier.toString(f1.getModifiers()) );
		
			
		}

	}

}

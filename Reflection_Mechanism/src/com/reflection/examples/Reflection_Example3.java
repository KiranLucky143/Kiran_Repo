package com.reflection.examples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflection_Example3 {

	public static void main(String[] args) {
		
		
		//Here, we are trying to find All info about List Class using reflection mechanism
		
		List li = new ArrayList();
		
		System.out.println(li.getClass()); //class java.util.ArrayList
		
		//Here class is parent of all Classes, so we are assigning this Arraylist class address to class object
		
		Class c= li.getClass();
		
		//All these Methods/Info about ArrayList Class we can access we can access
		
/*	    c.getFields();
		c.getDeclaredFields();
		c.getDeclaredMethods();
		c.getDeclaringClass();
		c.getConstructors();
		c.getAnnotations();
*/		

		Annotation [] a = c.getAnnotations();
		
		int Annotations_Count=0;
		
		for(Annotation a1 :a ) {
			
			Annotations_Count++;
			
			System.out.println(a1);
		}
		
		System.out.println("The total Number of Annotations in List Class are : "+Annotations_Count);
		
		
    	System.out.println("-----------------------------------");
    	
    	
    	Field [] f = c.getDeclaredFields();
    	
    	for(Field f1 : f) {
    		
    		System.out.println(f1);
    	}
	}

}

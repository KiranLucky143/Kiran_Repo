package com.reflection.examples;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection_Example1 {

	public static void main(String[] args) {
		
	 Object obj = new String();
	 
	 obj.getClass();
	 
	 System.out.println(obj.getClass());  //Result : class java.lang.String

	 Class c = obj.getClass();
	 
	Field[] f= c.getDeclaredFields(); //To store all Fileds in String class into Array
	
	Method [] m=c.getDeclaredMethods(); //To store all Methods in String class into Array
	
	//To count number of fileds there in String class
	int Field_count=0;
	
    for(Field f1 : f) {
    	
    	Field_count++;
    	System.out.println(f1);
    }
    
    System.out.println("The number of Fields in String Class is : "+Field_count);
    
    System.out.println("-------------------------------------------");
    
  //To count number of Methods there in String class
    
    int Method_count=0;
    
    for(Method m1 : m) {
    	Method_count++;
    	System.out.println(m1);
    }
    
    System.out.println(Method_count); //to print number of methods there in string Class
    
	}

}

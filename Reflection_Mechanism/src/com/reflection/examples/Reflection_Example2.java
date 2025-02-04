package com.reflection.examples;

import java.lang.reflect.Constructor;

public class Reflection_Example2 {

	public static void main(String[] args) {
		
		Object obj = new Integer(0); 
		
		System.out.println(obj.getClass()); //class java.lang.Integer
		
		Class c = obj.getClass(); //Assigning Integer object address to class
		
		System.out.println("--------------------------");
		System.out.println("Interfaces in Integer Class are below : ");
		System.out.println();
		
		Class [] Interfaces_class = c.getInterfaces();
		
		int Interfaces_count=0;
		
		for(Class c1 : Interfaces_class) {
			
			Interfaces_count++;
			System.out.println(c1);
		}
		System.out.println("The Number of Interfaces in Integer Class is : "+Interfaces_count);
		System.out.println("---------------------------------------");
		
		System.out.println("Constuructor Details : ");
		c.getConstructors(); //To get constructors info
		
		Constructor [] Constructor_info =c.getConstructors();
		
		int Constructor_count=0;
		
		for(Constructor c1 : Constructor_info) {
			
			Constructor_count++;
			System.out.println(c1);
		}
		
		System.out.println("The Number of Constructors in Integer Class is : "+Constructor_count);
		
		
		

	}

}

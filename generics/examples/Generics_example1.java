package com.generics.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics_example1 {

	public static void main(String[] args) {
		
		List ls = new ArrayList();
		
		//adding element to list
		ls.add("Kiran");
		
		//here, if we want to retrieve string value, we have to typecast it.
		
	//	String name =ls.get(0);  (error)
		
		String name = (String) ls.get(0); //here we are type casting to fix this error
		
		System.out.println(name);
		
		
		// Instead of this we can use generics to fix this kind of Type safety
		
	}

}

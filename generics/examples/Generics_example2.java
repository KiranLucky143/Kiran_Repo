package com.generics.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics_example2 {

	public static void main(String[] args) {
		
		List<String> ls = new ArrayList();

		/*If we are adding Integers also to this list,we will get error
		because it will accept string values only... */
		
	   //	ls.addAll(Arrays.asList("Gella","Kumar",2));
		
		/*
		 * 
		 *In below, we are giving only string values -no error
		 *  */
		
		ls.addAll(Arrays.asList("Gella","Kiran","Kumar"));
		
		//If we want to get a value based on index, we no need to type cast
		//Because, this Generics <> will accept all string values.
		
		String middle_name =ls.get(1); //no need to type cast to string
		
		System.out.println(middle_name);
		
		
		
		
		
	}

}

package com.generics.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Defining generic class --->class<T> //T means type

class Generic_class<T> {
	
	 T obj;
	List<T> values;

	 //Method for adding element
	void add(T obj) {
		this.obj=obj;
	}
	
	//Method for returning element
	T get() {
		return obj;
	}
	
	//adding all values in Generics class
	void addAll(List<T> values) {
		
		this.values =values;
		
	}
	
	//Getting all values in Generics class
	List<T> getAll() {
		
		return values;
		
	}

	public void addFew(int i, int j, int k, int l, int m) {
		// TODO Auto-generated method stub
		
	}



	
	
	
}


public class Generics_example3 {

	public static void main(String[] args) {
		
		//created object for generics class
		Generic_class<Integer> c1 = new Generic_class();
		
		c1.add(111); //adding element

		c1.addFew(111,222,333,555,444); //adding more elements using addAll method
		
		//Adding list of elements
		List<Integer> li = new ArrayList(Arrays.asList(111,222,333,555,444));
		c1.addAll(li);
		
		
		
		
		
		
		
		
		//created object for generics class
		Generic_class<String> c2 = new Generic_class();


	// c2.add(123); --->error (it will accept only string values
		
		c2.add("Kiran Kumar"); //--->it will add only single element
		
		
		List<String> li2 = new ArrayList(Arrays.asList("Jai","Shree","Ram!"));
		
		c2.addAll(li2); //adding list of string values
		
		System.out.println(c1.getAll()); //to get all int values
		
		System.out.println(c2.getAll()); //to get all string values
		
		
	}

}

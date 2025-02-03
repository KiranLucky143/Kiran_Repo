package com.generics.examples;

import java.util.ArrayList;
import java.util.List;

//created Abstract class
abstract class Shape {
	
	abstract void type();
}

//created class extends Abstarct class
class circle extends Shape{

	@Override
	void type() {
		
		System.out.println("Circle shape!");
		
	}
	
}

//created class extends Abstarct class
class Rectangle extends Shape{

	@Override
	void type() {
		
		System.out.println("Rectangle shape!");
		
	}
	
}

public class Generics_WildCard_Example2 {

//Here,SHape class is super class of Circle and Rectangle, so in ? place only Circle and Rectangle type allowed
	
	 
	 
	 public static void main(String[] args) {
			
	 }
	 
}


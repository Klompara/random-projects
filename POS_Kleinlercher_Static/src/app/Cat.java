package app;

import bll.Animal;

public class Cat extends Animal{

	public static void testClassMethod() {
		System.out.println("The static in mehtod Cat");
	}
	
	public void testInstanceMethod() {
		System.out.println("The Instance method in Cat");
	}
	
	public static void main(String[] args) {
		Cat c = new Cat();
		Animal a = c;
		Animal.testClassMethod();
		a.testInstanceMethod();
	}
}

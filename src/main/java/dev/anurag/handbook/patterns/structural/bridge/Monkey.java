package dev.anurag.handbook.patterns.structural.bridge;

public class Monkey extends Animal{

	protected Monkey(Feeding feeding) {
		super(feeding);
	}

	@Override
	public void eat() {
		 feeding.peelFood();
		 feeding.eat();		
	}

}

package dev.anurag.handbook.patterns.creational.singleton;

public class EnumSingletonImpl {

	public static void main(String[] args) {
		EnumSingleton obj = EnumSingleton.INSTANCE;
		System.out.println(obj.getValue());
		obj.setValue(2);
		System.out.println(obj.getValue());
	}

}

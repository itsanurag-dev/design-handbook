package dev.anurag.handbook.patterns.creational.singleton;

public class FinalStaticSingleton {

	public final static FinalStaticSingleton INSTANCE = new FinalStaticSingleton();

	private FinalStaticSingleton() {
	}
}

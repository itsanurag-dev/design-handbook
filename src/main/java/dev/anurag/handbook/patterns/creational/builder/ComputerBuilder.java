package dev.anurag.handbook.patterns.creational.builder;

public class ComputerBuilder {

	public static void main(String[] args) {
		Computer comp = new Computer.ComputerBuilder("", "").setBluetoothEnabled(true).setGraphicsCardEnabled(true)
				.build();
		System.out.print(comp);
	}

}

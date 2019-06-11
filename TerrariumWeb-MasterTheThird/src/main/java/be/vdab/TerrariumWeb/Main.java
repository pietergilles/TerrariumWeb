package be.vdab.TerrariumWeb;

import be.vdab.TerrariumWeb.domain.GameController;

import java.util.Scanner;

public class Main {
	//scanner om de inputs te lezen
	private static final Scanner scanner = new Scanner(System.in);
	private static int whichDay = 1;

	public static void main(String[] args) {

		System.out.println("Welkom bij Terrarium. Zo begint jouw terrarium:");

		//maak een terrarium en gamecontroller object
		//print dag 1
		GameController.INSTANCE.printTerrarium();
		System.out.println("druk v voor volgende dag, druk s om te stoppen");
		//vraag en check input aan de user
		String input = scanner.nextLine();
		while(!input.equals("s")) {
			if (input.equals("v")) {
				GameController.INSTANCE.spawnPlants();
				GameController.INSTANCE.activateOrganisms();
				GameController.INSTANCE.printTerrarium();
				whichDay++;
				System.out.println("Dag " + whichDay);
			}
			else {
				System.out.println("foute input");
			}
			System.out.println("druk v voor volgende dag, druk s om te stoppen");
			input = scanner.nextLine();
		}
	}
}
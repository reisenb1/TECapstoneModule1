package com.techelevator;

import com.techelevator.Model.Product;
import com.techelevator.Model.VendingMachine;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput, Menu menu) {

		// You can use this for any user input you need
		this.userInput = userInput;

		// You can use this to provide menu functionality if you'd likes
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine();
		try {
			vendingMachine.initialLoad();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Map<String, Product> productMap = vendingMachine.getProductMap();
				for(Map.Entry<String, Product> entry : productMap.entrySet() ) {
					String key = entry.getKey();
					Product value = entry.getValue();
					System.out.println(value);
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
				System.out.println("Thank you for purchasing, from team 3!!");
			}
		}
	}

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		Menu menu = new Menu(userInput, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(userInput, menu);
		cli.run();
	}
}

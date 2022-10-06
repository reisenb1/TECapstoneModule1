package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput, Menu menu) {

		// You can use this for any user input you need
		this.userInput = userInput;

		// You can use this to provide menu functionality if you'd likes
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
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

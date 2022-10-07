package com.techelevator;

import com.techelevator.Model.Product;
import com.techelevator.Model.VendingMachine;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};


	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = {SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION};


	private Menu menu;
	private Scanner userInput;

	public VendingMachineCLI(Scanner userInput, Menu menu) {

		// You can use this for any user input you need
		this.userInput = userInput;

		// You can use this to provide menu functionality if you'd likes
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = null;
		try {
			vendingMachine = new VendingMachine();
		} catch (IOException e) {
			System.out.println("Unable to create log");
		}
		try {
			vendingMachine.initialLoad();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		boolean running = true;

		String[] options = MAIN_MENU_OPTIONS;

		while (running) {
			String choice = (String) menu.getChoiceFromOptions(options);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Map<String, Product> productMap = vendingMachine.getProductMap();
				for (Map.Entry<String, Product> entry : productMap.entrySet()) {
					String key = entry.getKey();
					Product value = entry.getValue();
					System.out.println(value);
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				options = SUB_MENU_OPTIONS;

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
				System.out.println("Thank you for purchasing, from team 3!!");
			}
			
			else if (choice.equals(SUB_MENU_OPTION_FEED_MONEY)) {
				// feed money code
				System.out.println("Please insert money in whole dollar amount(1, 5, or 10): ");
				String input = userInput.nextLine();
				if(input.equals("1")) {
					try {
						vendingMachine.insertBills(1);
					} catch (FileNotFoundException e) {
						System.out.println("Log File not found");					}
				} else if(input.equals("5")) {
					try {
						vendingMachine.insertBills(5);
					} catch (FileNotFoundException e) {
						System.out.println("Log File not found");					}
				} else if (input.equals("10")) {
					try {
						vendingMachine.insertBills(10);
					} catch (FileNotFoundException e) {
						System.out.println("Log File not found");
					}
				} else {
					System.out.println("================================================");
					System.out.println("MUST ENTER AN AMOUNT EQUAL TO 1, 5, OR 10");
					System.out.println("================================================");
				}
				String message = String.format("There is now $%5.2f in the vending machine", vendingMachine.getPayment());
				System.out.println(message);
			}


			else if (choice.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
				// select product code
				Map<String, Product> productMap = vendingMachine.getProductMap();
				for (Map.Entry<String, Product> entry : productMap.entrySet()) {
					String key = entry.getKey();
					Product value = entry.getValue();
					System.out.println(value);
				}
				System.out.println("Please select slot for the item you would like: ");
				String itemSelection = userInput.nextLine();
				itemSelection = itemSelection.toUpperCase();


				if (!productMap.containsKey(itemSelection)) {
					System.out.println("That is an invalid selection, please select again.");
					options = SUB_MENU_OPTIONS;
				} else if (productMap.get(itemSelection).getInventory() == 0) {
					System.out.println("Item is currently sold out, please select again.");
					options = SUB_MENU_OPTIONS;
				} else if (productMap.get(itemSelection).getPrice().compareTo(vendingMachine.getPayment()) == 1) {
					System.out.println("You do not have enough money, please insert more or select another product.");
					options = SUB_MENU_OPTIONS;
				} else {
					vendingMachine.productSelection(itemSelection);

					String selectionName = productMap.get(itemSelection).getName();
					BigDecimal cost = productMap.get(itemSelection).getPrice();
					String sound = productMap.get(itemSelection).getSound();

					System.out.println("You've selected " + selectionName + "; it cost $" + cost + ". " + sound);
					System.out.println("==============================================================");

				}

				String message = String.format("There is now $%5.2f in the vending machine", vendingMachine.getPayment());
				System.out.println(message);



			} else if (choice.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
				// finish transaction

				int[] finishTransactionArray = vendingMachine.finishTransaction(vendingMachine.getPayment());
				String finishMessage = String.format("Your change is %d quarter(s), %d dime(s), and %d nickel(s)", finishTransactionArray[0], finishTransactionArray[1], finishTransactionArray[2]);
				System.out.println(finishMessage);
				System.out.println("=====================================================================");
				System.out.println("There is now a $" + vendingMachine.getPayment() + " balance on the machine");
				options = MAIN_MENU_OPTIONS;
			}


		}
	}

		public static void main (String[]args){
			Scanner userInput = new Scanner(System.in);
			Menu menu = new Menu(userInput, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(userInput, menu);
			cli.run();
		}
	}


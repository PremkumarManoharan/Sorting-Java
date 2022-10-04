package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Cart{
	
	private double myCash;
	private double myTotal;
	private double myChange;
	
	
	
	
	public Cart(double myCash, double myTotal, double myChange){
		super();
		this.myCash = myCash;
		this.myTotal = myTotal;
		this.myChange = myChange;
	}

	@Override
	public String toString() {
		return "\n Cash ===>     " + myCash + "\n Total ==>     " + myTotal + "\n Change =>     " +myChange;
	}
	
	

	// Demonstration for CALL BY VALUE
	public void sillyCheckout(double cash, double price, double total, double change) {
		myTotal = myTotal + price; // to attempt to calculate total
		myChange = cash - myTotal; // to attempt to calculate change
		System.out.println("Your Cash : "+cash);
		System.out.println("Your Current Total : "+ myTotal);
		System.out.println("Your Balance : "+myChange);
		if(myChange<3.99) {
			System.out.println("Insufficient Balance Please exit shopping !!");
		}
		total = myTotal; // trying to modify original value but can't
		change = myChange; // trying to modify original value but can't 
	}
	
	public double Checkout(Cart myCart, Item myItem) {
		myTotal = myTotal + myItem.price;
		myChange = myCash - myTotal;
		return myCart.myChange;
	}
	
	static void showItems(Item[] items) {

		System.out.printf("%10s %10s %10s", "Item ID", "Item Name", "Price");
		System.out.println();
		System.out.println("======================================");

		for (int i = 0; i < items.length; i++) {
			System.out.printf("%10s %10s %10s",items[i].id, items[i].toString().split("/")[0],
					items[i].toString().split("/")[1]);
			System.out.println();
		}

	}

	static void showShoppedItems(List<Item> items) {
		
		
		System.out.printf("%10s %10s", "Item Name", "Price");
		System.out.println();
		System.out.println("===================");

		for (int i = 0; i < items.size(); i++) {
			System.out.printf("%10s %10s", items.get(i).toString().split("/")[0],
					items.get(i).toString().split("/")[1]);
			System.out.println();
		}

	}
	
	public static Comparator<Item> itemid = new Comparator<Item>(){
		
		public int compare(Item i1, Item i2) {
			int id1 = i1.id;
			int id2 = i2.id;
			
			return id1 - id2;
		}
	};
	public static Comparator<Item> itemname = new Comparator<Item>(){
		
		public int compare(Item i1, Item i2) {
			String name1 = i1.name;
			String name2 = i2.name;
			
			return name1.compareTo(name2);
		}
	};
	public static Comparator<Item> itemprice = new Comparator<Item>(){
		
		public int compare(Item i1, Item i2) {
			double price1 = i1.price;
			double price2 = i2.price;
			
			return  Double.compare(price1, price2);
		}
	};
	
	
	static void demo() {
		
		double cash = 20.0;
		// just to demonstrate CALL BY VALUE
		boolean shop = true;
		double change=0;
		Item[] availableItems = new Item[4];
		availableItems[0] = new Item(2,2.49,"Milk");
		availableItems[1] = new Item(4,1.49,"Bread");
		availableItems[2] = new Item(3,3.49,"OJ");
		availableItems[3] = new Item(1,0.99,"Candy");

		ArrayList<Item> shoppingItems = new ArrayList<Item>();

		System.out.println("Welcome to Shopping....");
		System.out.println();

		// sillyCart is used to demonstrate CALL BY VALUE
		Cart sillyCart = new Cart(cash,0,0);
		while (shop) {
			System.out.println("Select the Item you need..");
			System.out.println();
			showItems(availableItems);
			System.out.println("Enter Item id to select an Item : ");
			Scanner sc = new Scanner(System.in);
			int selectedItem = sc.nextInt();
			shoppingItems.add(availableItems[selectedItem - 1]);                  
			sillyCart.sillyCheckout(cash, availableItems[selectedItem - 1].price, 0, 0);
			System.out.println("Do you want to continue shopping (Y / N) ?");
			String decision = sc.next();
			if (decision.equals("N")) {
				shop = false;
			}

		}

		// newCart for calling Checkout using CALL BY REFERENCE OBJECTS
		Cart newCart = new Cart(cash, 0, 0);

		for (int i = 0; i < shoppingItems.size(); i++) {
			change = newCart.Checkout(newCart, shoppingItems.get(i));
		}
		System.out.println("Sort by ID");
		shoppingItems.sort(itemid);
		showShoppedItems(shoppingItems);
		
		System.out.println("Sort by Name");
		shoppingItems.sort(itemname);
		showShoppedItems(shoppingItems);
		
		System.out.println("Sort by Price");
		shoppingItems.sort(itemprice);
		showShoppedItems(shoppingItems);

		System.out.println(newCart.toString());

	}
	
	

	

}
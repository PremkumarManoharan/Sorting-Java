package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {

	final static String REVISION = "1.0.0";

	String name;
	double price;
	int id;

	public Item(int id, double price, String name) {
		super();
		this.name = name;
		this.price = price;
		this.id = id;
	}

	@Override
	public String toString() {
		return name + "/" + price;
	}

	

	//Exception handling is not implemented
	

}

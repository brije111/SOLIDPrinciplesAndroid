package com.example.solidprinciplesandroid.srp;

import java.util.List;

public class ProductRepo {
	Database initDb() {
		//open database connection here
		return null;
	}
	List<Product> getProducts(Database database){
		//fetch product data from database
		return null;
	}
	boolean save(List<Product> productList) {
		//save the product list data in external file
		return false;
	}
}
class Database{}
class Product{}
/*
 * The above class handles 3 operations(responsibilities) i.e. initializing the database, 
 * fetching the data and writing that data in external file.
 * So we can make 3 classes for each operation in order to apply SRP.
 */

class DatabaseConnectionUtils{
	Database initDb() {
		//open database connection here
		return null;
	}
}

class DatabaseUtils{
	List<Product> getProducts(Database database){
		//fetch product data from database
		return null;
	}
}

class FileUtils{
	boolean save(List<Product> productList) {
		//save the product list data in external file
		return false;
	}
}


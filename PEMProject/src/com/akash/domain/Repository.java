package com.akash.domain;

import java.util.ArrayList;
import java.util.List;

      //the class is used as Database/Repository and its a Singleton.
/**
 * The class is used as Database / Repository and its a SingleTon
 * @author AKASH KUMAR
 *
 */

public class Repository {
	/**
	 * the list holds all expense added by User 
	 */
	public List<Expense> expList = new ArrayList<>();
	/**
	 * The list holds all expense categories added by the user
	 */
	public List<Category> catList = new ArrayList<>();
	/**
	 * A singleton reference of repository
	 */
	private static Repository repository;
	
	/**
	 * private constructor to restrict object creation from outside.
	 */
	public Repository() {

	}
	
	/**
	 * The method provides a singleTon object of Repository
	 * @return
	 */
	public static Repository getRepository() {
		if(repository == null) {
			repository = new Repository();
		}
		return repository;
	}

}

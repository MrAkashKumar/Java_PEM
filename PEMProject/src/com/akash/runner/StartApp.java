package com.akash.runner;

import com.akash.service.PEMService;
/**
 * This Class is an entry point of execution for PerSonalExpenseManager Application
 * @author AKASH KUMAR
 *
 */

public class StartApp {

	/**
	 * This method is creating <code>PEMService</code>object and show application 
	 * menu by calling showMenu() method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PEMService pemService = new PEMService();
		pemService.showMenu();

	}

}

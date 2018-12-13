package com.akash.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.akash.dateUtility.DateUtil;
import com.akash.domain.Category;
import com.akash.domain.Expense;
import com.akash.domain.Repository;
import com.akash.report.ReportService;

/**
 * The class contains most of the operations related to PEM application
 * <p>
 * This class prepares a menu and various method are present to handle the user
 * action. The class make use of <code>Repository</code> to store the data. Also
 * using <code>ReportService</code> to generate different required reports.
 * </p>
 * 
 * @author AKASH KUMAR
 *
 */

public class PEMService {
	/**
	 * Declare a reference of repository by calling a static method which returns a
	 * SingleTon Repository object.
	 */
	Repository repository = Repository.getRepository();
	/**
	 * Declare a Scanner Object to take Standard input from keyboard.
	 */
	ReportService reportService = new ReportService();
	private Scanner scanner = new Scanner(System.in);
	/**
	 * This variable store the value of menu-choice
	 */
	private int choice;

	/**
	 * call this constructor to create PEMService Object with default details.
	 */
	public PEMService() {
		//prepareSampleDate(); // Delete this method call after testing is completed.
		reStoreRepository();
	}

	private void reStoreRepository() {
		List<Expense> expenses = (List<Expense>) deSerialize("expense.ser");
		if(expenses != null) {
			repository.expList = expenses;
		}
		List<Category> category = (List<Category>) deSerialize("category.ser");
		if(category != null) {
			repository.catList = category;
		}
	} 
	
	public Object deSerialize(String file) {
		try {
			
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object obj = objectInputStream.readObject();
			return obj;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(" No External Data present ");
			return null;
		}
		
	}

	/**
	 * This method prepares a PEMApp menu using switch-case and infinite loop . also
	 * wait ask for user choice.
	 */

	public void showMenu() {

		while (true) {
			printMenu();
			switch (choice) {
			case 1:
				onAddCategory();
				pressAnyKeyToContinue();
				break;
			case 2:
				onCategoryList();
				pressAnyKeyToContinue();
				break;
			case 3:
				onExpenseEntry();
				pressAnyKeyToContinue();
				break;
			case 4:
				onExpenseList();
				pressAnyKeyToContinue();
				break;
			case 5:
				onMonthlyExpenseList();
				pressAnyKeyToContinue();
				break;
			case 6:
				onYearlyExpenseList();
				pressAnyKeyToContinue();
				break;
			case 7:
				onCategoryExpenseList();
				pressAnyKeyToContinue();
				break;
			case 0:
				exit();
				break;
			}
		}
	}

	/**
	 * This method prints a menu (CUI/CLI Menu)
	 */
	public void printMenu() {
		System.out.println("----------PEM Menu-----------");

		System.out.println(" 1. Add Category ");
		System.out.println(" 2. Category List ");
		System.out.println(" 3. Expense Entry ");
		System.out.println(" 4. Expense List ");
		System.out.println(" 5. Monthly Expense Report ");
		System.out.println(" 6. Yearly Expense Report ");
		System.out.println(" 7. Categorized Expense Report ");
		System.out.println(" 0. Exit ");

		System.out.println("-------------------------");

		System.out.println("Enter your choice : ");
		choice = scanner.nextInt();
	}

	/**
	 * This method is called to hold a screen after processing the required task.
	 * wait for any char input to continue to the menu.
	 */
	public void pressAnyKeyToContinue() {
		try {
			System.out.println(" Press any key to continue...... ");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method to taking expense category name as input to add new category in
	 * the system.
	 * 
	 */
	private void onAddCategory() {
		scanner.nextLine(); // new line char is read here which is already present in stream and its not in
							// use for now.
		System.out.println(" Enter Category Name ");
		String categoryName = scanner.nextLine();
		Category category = new Category(categoryName);
		repository.catList.add(category);
		System.out.println("Success : Category Added " + categoryName);

	}

	/**
	 * call this method to print existing category.
	 */
	private void onCategoryList() {
		System.out.println("Category List ");
		List<Category> catgoryList = repository.catList;
		for (int i = 0; i < catgoryList.size(); i++) {
			Category category = catgoryList.get(i);
			System.out.println((i + 1) + " " + category.getCategoryName() + " " + category.getCategoryId());
		}
	}

	/**
	 * call this method to enter expense details. The entered details will be added
	 * in repository
	 */
	private void onExpenseEntry() {
		System.out.println(" Enter details for Expense Entry ");
		onCategoryList();
		System.out.println("Choice Category : ");
		int choice = scanner.nextInt();
		Category selectCategory = repository.catList.get(choice - 1);

		System.out.println("Enter the Amount : ");
		float amount = scanner.nextFloat();

		scanner.nextLine();
		System.out.println("Enter the Remark :");

		String remark = scanner.nextLine();

		System.out.println("Enter Date (DD/MM/YYYY) : ");
		String dateAsString = scanner.nextLine();
		Date date = DateUtil.stringToDate(dateAsString);

		// Add Expense detail in expense object

		Expense expense = new Expense();
		expense.setAmount(amount);
		expense.setCategoryId(selectCategory.getCategoryId());
		expense.setRemarks(remark);
		expense.setDate(date);

		// store expense Object in repository

		repository.expList.add(expense);

		System.out.println(" Success : Expense Added ");

	}

	/**
	 * call this method existing Expense List
	 */
	private void onExpenseList() {
		System.out.println(" Expense List ");

		List<Expense> expenseList = repository.expList;
		for (int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i);
			String categoryName = reportService.getCategoryNameById(expense.getCategoryId());
			String dateFormat = DateUtil.dateToString(expense.getDate());
			System.out.println((i + 1) + " , " + categoryName + " , " + expense.getAmount() + " , "
					+ expense.getRemarks() + " , " + dateFormat);
		}
	}

	/**
	 * This method is called from menu to prepare monthly-wise-expense-total. Using
	 * <code>ReportService</code> to calculate report.The returned result is printed
	 * by this method. menu this method invokes a call to generate report then
	 * result is printed by this method
	 */

	private void onMonthlyExpenseList() {
		System.out.println("Monthly Expense Total");
		Map<String, Float> resultMap = reportService.calculateMonthlyTotal();
		Set<String> keys = resultMap.keySet();
		for (String yearMonth : keys) {
			String[] yearMonthArray = yearMonth.split(",");
			String year = yearMonthArray[0];
			Integer monthNo = new Integer(yearMonthArray[1]);
			String monthName = DateUtil.getMonthName(monthNo);
			System.out.println(year + ", " + monthName + " : " + resultMap.get(yearMonth));
		}
	}

	/**
	 * This method is called from menu to prepare Yearly-wise-expense-total. Using
	 * <code>ReportService</code> to calculate report.The returned result is printed
	 * by this method. menu this method invokes a call to generate report then
	 * result is printed by this method
	 */
	private void onYearlyExpenseList() {
		System.out.println("Yearly Expense Total List");
		Map<Integer, Float> resultMap = reportService.calculateYearTotal();
		Set<Integer> years = resultMap.keySet();
		Float total = 0.0f;
		for (Integer year : years) {
			Float expense = resultMap.get(year);
			total = total + expense;
			System.out.println(year + " : " + expense);
		}
		System.out.println("--------------------");
		System.out.println("Total Expense (INR) : " + total);
	}

	/**
	 * This method is called from menu to prepare category-wise-expense-total. Using
	 * <code>ReportService</code> to calculate report.The returned result is printed
	 * by this method. menu this method invokes a call to generate report then
	 * result is printed by this method
	 */

	private void onCategoryExpenseList() {
		System.out.println("Category Expense List");
		Map<String, Float> resultMap = reportService.caluculateCategorizedTotal();
		Set<String> category = resultMap.keySet();
		Float netTotal = 0.0f;
		for (String categoryName : category) {
			Float catTotal = resultMap.get(categoryName);
			netTotal = netTotal + catTotal;
			System.out.println(categoryName + " : " + catTotal);
		}
		System.out.println("---------------------------------");
		System.out.println("Net Total : " + netTotal);
	}

	/**
	 * This method stop a JVM. before that its Storing the Repository permanently. its closing PEM Application.
	 * its like a shutdown-hook
	 */

	private void exit() {
		persistRepository();
		System.exit(0);
	}

	private void persistRepository() {
		serialize("expense.ser", repository.expList);
		serialize("category.ser", repository.catList);
	}

	public void serialize(String file, Object obj) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(obj); //

			// use finally block
			fileOutputStream.close();
			objectOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("No data present here");
		} 
	}

	/**
	 * This method is preparing Sample Data for testing purpose. it should be report
	 * once application is tested ok.
	 */
	public void prepareSampleDate() {
		Category categoryParty = new Category("Party");
		delay();
		Category categoryShopping = new Category("Shopping");
		delay();
		Category categoryGift = new Category("Gift");
		delay();

		repository.catList.add(categoryParty);
		repository.catList.add(categoryShopping);
		repository.catList.add(categoryGift);

		// Jan - 2018

		Expense expense = new Expense(categoryParty.getCategoryId(), 100.0f, DateUtil.stringToDate("02/01/2018"),
				" N/A ");
		delay();
		Expense expense2 = new Expense(categoryGift.getCategoryId(), 200.0f, DateUtil.stringToDate("04/01/2018"),
				" N/A ");
		delay();
		// Feb 2018

		Expense expense3 = new Expense(categoryParty.getCategoryId(), 400.0f, DateUtil.stringToDate("04/02/2018"),
				" N/A ");
		delay();
		Expense expense4 = new Expense(categoryShopping.getCategoryId(), 300.0f, DateUtil.stringToDate("06/02/2018"),
				" N/A ");
		delay();
		// March 2018
		Expense expense5 = new Expense(categoryGift.getCategoryId(), 100.0f, DateUtil.stringToDate("06/03/2018"),
				" N/A ");
		delay();

		// Dec 2018
		Expense expense1 = new Expense(categoryParty.getCategoryId(), 1000.0f, DateUtil.stringToDate("01/12/2018"),
				" N/A ");
		delay();

		// Jan 2019
		Expense expense6 = new Expense(categoryParty.getCategoryId(), 600.0f, DateUtil.stringToDate("01/01/2019"),
				" N/A ");
		delay();

		// Feb 2019
		Expense expense7 = new Expense(categoryGift.getCategoryId(), 200.0f, DateUtil.stringToDate("01/02/2019"),
				" N/A ");
		delay();
		Expense expense8 = new Expense(categoryShopping.getCategoryId(), 250.0f, DateUtil.stringToDate("02/02/2019"),
				" N/A ");
		delay();

		// Jan 2020
		Expense expense9 = new Expense(categoryShopping.getCategoryId(), 450.0f, DateUtil.stringToDate("01/01/2020"),
				" N/A ");
		delay();

		repository.expList.add(expense);
		repository.expList.add(expense1);
		repository.expList.add(expense2);
		repository.expList.add(expense3);
		repository.expList.add(expense4);
		repository.expList.add(expense5);
		repository.expList.add(expense6);
		repository.expList.add(expense7);
		repository.expList.add(expense8);
		repository.expList.add(expense9);

	}

	/**
	 * This method sleep a thread for 10s
	 */

	private void delay() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
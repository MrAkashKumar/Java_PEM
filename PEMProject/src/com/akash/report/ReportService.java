package com.akash.report;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.akash.dateUtility.DateUtil;
import com.akash.domain.Category;
import com.akash.domain.Expense;
import com.akash.domain.Repository;
/**
 * The class contains various method to calculate PEM Application reports
 * @author AKASH KUMAR
 *
 */
public class ReportService {
	/*
	 * Declare a reference of singleton repository
	 */
	
	private Repository repository = Repository.getRepository();
	/*
	 * The method calculate month-wise total and returns result in Map 
	 * Its preparing data in proper order.
	 */
	public Map<String, Float> calculateMonthlyTotal(){
		Map<String, Float> map = new TreeMap<>();
		for(Expense expense : repository.expList) {
			Date expenseDate = expense.getDate();
			String yearAndMonth = DateUtil.getYearAndMonth(expenseDate);
			
			if(map.containsKey(yearAndMonth)) {
				// when expense is already present for a month
				Float total = map.get(yearAndMonth);
				total = total +expense.getAmount();
				map.put(yearAndMonth, total);
			}else {
				// this month is not added No add here 
				
				map.put(yearAndMonth, expense.getAmount());
			}
		}
		return map;
	}
	/*
	 * The method calculate Year-wise total and returns result in Map 
	 * Its preparing data in proper order.
	 */
	
	public Map<Integer, Float> calculateYearTotal(){
		Map<Integer, Float> map = new TreeMap<>();
		for(Expense expense : repository.expList) {
			Date expenseDate = expense.getDate();
			Integer year = DateUtil.getYear(expenseDate);
			
			if(map.containsKey(year)) {
				// when expense is already present for a year
				Float total = map.get(year);
				total = total + expense.getAmount();
				map.put(year, total);
			}else {
				// this year is not added No add here 
				
				map.put(year, expense.getAmount());
			}
		}
		return map;
	}
	/*
	 * The method calculate Category-wise total and returns result in Map 
	 * Its preparing data in proper order.
	 */
	public Map<String, Float> caluculateCategorizedTotal(){
		Map<String, Float> map = new TreeMap<>();
		for(Expense expense : repository.expList) {
			Long categoryId = expense.getCategoryId();
			String catName = this.getCategoryNameById(categoryId);
			if(map.containsKey(catName)) {
				
				// when expense is already present for a Category
				Float total = map.get(catName);
				total = total +expense.getAmount();
				map.put(catName, total);
			}else {
				
				// this Category is not added No add here 
				map.put(catName, expense.getAmount());
			}
		}
		return map; //returns final result as map
	}
	
	/**
	 * The Method returns category Name for given categoryId
	 * return null when wrong categoryId is supplied.
	 * @param categoryId
	 * @return
	 */
	
	public String getCategoryNameById(long categoryId) {
		for (Category category : repository.catList) {
				if(category.getCategoryId() == categoryId) {
					return category.getCategoryName();
				}
		}
		return null; // No Such ID present
	}

}

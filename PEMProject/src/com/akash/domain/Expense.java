package com.akash.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * This is domain class represents a Expense
 * @author AKASH KUMAR
 *
 */
public class Expense implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * It refers to a unique Expense id
	 * Here it is auto generated using current miliSecond.
	 * but in real time application it should be generated using some profession strategy 
	 */
	private long expenseId = System.currentTimeMillis();
	/*
	 * represent a category of this expense
	 */
	private long categoryId;
	private float amount;
	private Date date;
	private String remarks;
	
	public Expense() {

	}
	
	public Expense(long categoryId, float amount, Date date, String remarks) {
		this.categoryId = categoryId;
		this.amount = amount;
		this.date = date;
		this.remarks = remarks;
	}

	public long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", categoryId=" + categoryId + ", amount=" + amount + ", date="
				+ date + ", remarks=" + remarks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (expenseId ^ (expenseId >>> 32));
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (expenseId != other.expenseId)
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		return true;
	}
	
	
	

}

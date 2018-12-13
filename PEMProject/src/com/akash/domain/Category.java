package com.akash.domain;

import java.io.Serializable;

/**
 * This is domain class represents a Category
 * @author AKASH KUMAR
 *
 */
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * It refers to a unique category id
	 * Here it is Simply generated using current time.
	 * but in real time application it should be generated using some profession strategy 
	 */
	private long categoryId = System.currentTimeMillis();
	/**
	 * Name of expense category
	 */
	private String categoryName;
	
	public Category() {

	}
	
	public Category(long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Category(long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}
}

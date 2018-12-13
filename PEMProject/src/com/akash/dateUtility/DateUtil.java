package com.akash.dateUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class contains static method to handle dates.
 * @author AKASH KUMAR
 *
 */
public class DateUtil {

	public static final String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	/**
	 * This method converts String - Date into Date Object
	 * @param dateAsString String formated Date (example : 11/03/2018 : DD/MM/YYYY) 
	 * @return returns a Date object for input date - string
	 */
	public static Date stringToDate(String dateAsString) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return simpleDateFormat.parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * This method converts Date Object into String Date
	 * @param date String formated Date (example : 11/03/2018 : DD/MM/YYYY) 
	 * @return String date in DD/MM/YYYY Format
	 */

	public static String dateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

	/**
	 * This method returns year and month from given Date in Year, Month-No Format
	 * Example  2016,01 ; 2017,01 so on.....
	 * @param date year and month will be extracted for this date
	 * @return return year and month for input date
	 */
	public static String getYearAndMonth(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy,MM"); //Ex - 2016 01 so on
		return simpleDateFormat.format(date);
	}
	
	/**
	 * returns year for input date
	 * @param monthNo
	 * @return
	 */
	
	public static String getMonthName(Integer monthNo) {
		return month[monthNo-1];
	}

	/**
	 * The method returns Month Name for given month number
	 * 01:January , 02:February as so on..........
	 * @param date month number between 1 to 12
	 * @return returns month name for input month number
	 */
	public static Integer getYear(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy"); 
		return new Integer(simpleDateFormat.format(date));
	}
}

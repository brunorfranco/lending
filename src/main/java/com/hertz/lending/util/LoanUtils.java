package com.hertz.lending.util;

import java.util.Calendar;
import java.util.Date;

import com.hertz.lending.model.Loan;

public abstract class LoanUtils {

	public static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	/**
	 * Update returnDate of the loan to 30 days from today
	 * 
	 * This is only a good idea if all the clients interacting with this API have
	 * the same return time. If they differ, then the returnDate should come already
	 * populate from the API clients to avoid unnecessary logic to differentiate
	 * between clients
	 * 
	 * @param loan
	 */
	public static void calculateReturnDate(Loan loan) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, AppConstants.RETURN_DAYS);
		loan.setReturnDate(calendar.getTime());
	}

}

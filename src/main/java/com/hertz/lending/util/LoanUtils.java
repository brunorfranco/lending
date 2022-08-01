package com.hertz.lending.util;

import java.util.Calendar;
import java.util.Date;

public abstract class LoanUtils {

	public static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

}

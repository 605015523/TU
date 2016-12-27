package com.tu.util;

import java.math.BigDecimal;

public final class ConfConstants {
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final Integer BUDGET_MONTH = 2;
	public static final Integer BUDGET_DAY = 25;
	public static final BigDecimal BUDGET_PER_YEAR = new BigDecimal(1000);
	
	/**
	 * Private constructor.
	 */
	private ConfConstants() {
		// Do nothing
	}
}

package formatting;

/**
 * Illustrates use of Java number formatting and the BigDecimal class.
 * @author vincentlee
 * @since January 8, 2014
 */

import java.text.DecimalFormat;
import java.util.Currency;

public class NumberFormattingExamples {
	/**
	 * Displays some examples of why the default output formatting of double values is sometimes inadequate.
	 */
	public static void showBadExamples() {
		//my share if I order a $10 pizza with 5 other students
		double myPieShare = 10.0/6;
		
		//Gordon Gecko's credit limit: $10 million
		double gordonCreditLimit = 10000000.0;
		
		System.out.println("One sixth of $10: $" + myPieShare);
		System.out.println("Big spender's credit limit: $" + gordonCreditLimit);
	}
	
	/**
	 * Displays some examples of using DecimalFormat to improve the display of double numbers
	 */
	public static void showFormattedDoubles() {
		// \u00A4
		DecimalFormat myFormatter = new DecimalFormat();
		
		//my share if I order a $10 pizza with 5 other students
		double myPieShare = 10.0/6;
		
		//Gordon Gecko's credit limit: $10 million
		double gordonCreditLimit = 10000000.0;
		
		myFormatter.setMaximumFractionDigits(2);
		myFormatter.setPositivePrefix(Currency.getInstance("USD").getSymbol());
		
		//doesn't do anything
		myFormatter.setCurrency(Currency.getInstance("USD"));
		
		System.out.println("One sixth of $10: " +  myFormatter.format(myPieShare));
		System.out.println("Big spender's credit limit: " + myFormatter.format(gordonCreditLimit));
	}
	
	public static void main(String[] args) {
		System.out.println("Unformatted double values:");
		showBadExamples();
		
		System.out.println();
		
		System.out.println("Formatted double values:");
		showFormattedDoubles();
	}
}

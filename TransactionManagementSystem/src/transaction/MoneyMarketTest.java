/**
 * 
 */
package transaction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Kacper Murdzek, Taranvir Singh
 *
 */
class MoneyMarketTest {
	MoneyMarket test1;
	MoneyMarket test2;
	MoneyMarket test3;
	MoneyMarket test4;
	MoneyMarket test5;
	MoneyMarket test6;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		/**
		 * Created test variables of the type MoneyMarket
		 */

		test1 = new MoneyMarket(new Profile("Charlie", "Brown"), 600.33, new Date(6, 2, 2015), 0);
		test2 = new MoneyMarket(new Profile("Mary", "Johnson"), 456.78, new Date(7, 10, 20014), 0);
		test3 = new MoneyMarket(new Profile("Forever", "Young"), 2934.45, new Date(2, 21, 2020), 7);
		test4 = new MoneyMarket(new Profile("Mary", "Johnson"), 445.03, new Date(7, 10, 20014), 0);
		test5 = new MoneyMarket(new Profile("Charlie", "Brown"), 588.66, new Date(6, 2, 2015), 0);
		test6 = new MoneyMarket(new Profile("Forever", "Young"), 2924.04, new Date(2, 21, 2020), 7);

	}

	/**
	 * Test method for {@link transaction.MoneyMarket#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {
		/*
		 * assertEquals has to be used to check if predicted output is same as the one
		 * returned Because we formatted the output only when printing, the non-rounded
		 * interests have to be used for testing
		 */
		assertEquals(0.32517875, test1.monthlyInterest()); // test case 1, testing the monthly interest for $600.33
		assertEquals(0.24742249999999996, test2.monthlyInterest()); // test case 2, testing the monthly interest for
																	// $456.78
		assertEquals(1.58949375, test3.monthlyInterest()); // test case 3, testing the monthly interest for $2934.45
		assertEquals(0.24105791666666665, test4.monthlyInterest()); // test case 4, testing the monthly interest for
																	// $445.03
		assertEquals(0.31885749999999996, test5.monthlyInterest()); // test case 5, testing the monthly interest for
																	// $588.66
		assertEquals(1.583855, test6.monthlyInterest()); // test case 6, testing the monthly interest for $2924.04

		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link transaction.MoneyMarket#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		assertEquals(12, test1.monthlyFee()); // test case 1, testing the monthly fee for $600.33
		assertEquals(12, test2.monthlyFee()); // test case 2, testing the monthly fee for $456.78
		assertEquals(12, test3.monthlyFee()); // test case 3, testing the monthly fee for $2934.45
		assertEquals(12, test4.monthlyFee()); // test case 4, testing the monthly fee for $445.03
		assertEquals(12, test5.monthlyFee()); // test case 5, testing the monthly fee for $588.66
		assertEquals(12, test6.monthlyFee()); // test case 6, testing the monthly fee for $2924.04

		// fail("Not yet implemented");
	}

}

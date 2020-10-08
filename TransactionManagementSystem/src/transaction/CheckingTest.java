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
class CheckingTest {

	Checking test1;
	Checking test2;
	Checking test3;
	Checking test4;
	Checking test5;
	Checking test6;
	Checking test7;
	Checking test8;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		/**
		 * Created test variables of the type Checking
		 * 
		 */
		test1 = new Checking(new Profile("Jane", "Doe"), 500, new Date(2, 29, 2008), false);
		test2 = new Checking(new Profile("Jerry", "Anderson"), 1001.40, new Date(9, 23, 2020), false);
		test3 = new Checking(new Profile("Jason", "Brown"), 300.12, new Date(6, 28, 2015), false);
		test4 = new Checking(new Profile("Richard", "Scanlan"), 1500, new Date(7, 31, 2014), true);
		test5 = new Checking(new Profile("Jane", "Doe"), 475.02, new Date(2, 29, 2008), false);
		test6 = new Checking(new Profile("Richard", "Scanlan"), 1500.06, new Date(7, 31, 2014), true);
		test7 = new Checking(new Profile("Jason", "Brown"), 275.14, new Date(6, 28, 2015), false);
		test8 = new Checking(new Profile("Jerry", "Anderson"), 976.44, new Date(9, 23, 2020), false);

	}

	/**
	 * Test method for {@link transaction.Checking#monthlyInterest()}.
	 */
	@Test
	void testMonthlyInterest() {

		/*
		 * assertEquals has to be used to check if predicted output is same as the one
		 * returned Because we formatted the output only when printing, the non-rounded
		 * interests have to be used for testing
		 */
		assertEquals(0.020833333333333332, test1.monthlyInterest()); // test case 1, testing the monthly interest for
																		// $500
		assertEquals(0.041725, test2.monthlyInterest()); // test case 2, testing the monthly interest for $1001.40
		assertEquals(0.012505, test3.monthlyInterest()); // test case 3, testing the monthly interest for $300.12
		assertEquals(0.0625, test4.monthlyInterest()); // test case 4, testing the monthly interest for $1500
		assertEquals(0.019792499999999998, test5.monthlyInterest()); // test case 5, testing the monthly interest for
																		// $475.02
		assertEquals(0.0625025, test6.monthlyInterest()); // test case 6, testing the monthly interest for $1500.06
		assertEquals(0.011464166666666666, test7.monthlyInterest()); // test case 7, testing the monthly interest for
																		// $275.14
		assertEquals(0.040685, test8.monthlyInterest()); // test case 8, testing the monthly interest for $976.44

		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link transaction.Checking#monthlyFee()}.
	 */
	@Test
	void testMonthlyFee() {
		assertEquals(25, test1.monthlyFee()); // test case 1, testing the monthly fee for $500
		assertEquals(25, test2.monthlyFee()); // test case 2, testing the monthly fee for $1001.40
		assertEquals(25, test3.monthlyFee()); // test case 3, testing the monthly fee for $300.12
		assertEquals(0, test4.monthlyFee()); // test case 4, testing the monthly fee for $1500 - direct deposit
		assertEquals(25, test5.monthlyFee()); // test case 5, testing the monthly fee for $475.02
		assertEquals(0, test6.monthlyFee()); // test case 6, testing the monthly fee for $1500.06 - direct deposit
		assertEquals(25, test7.monthlyFee()); // test case 7, testing the monthly fee for $275.14
		assertEquals(25, test8.monthlyFee()); // test case 8, testing the monthly fee for $976.44

		// fail("Not yet implemented");
	}

}

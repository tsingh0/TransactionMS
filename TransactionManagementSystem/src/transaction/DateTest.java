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
class DateTest {
	
	Date test1;
	Date test2;
	Date test3;
	Date test4;
	Date test5;
	Date test6;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		/*
		 * The test case input should is not accepted as a string
		 * It must be added as the constructor states (month,day,year)
		 * We implemented the string to int conversion in the Transaction class instead of Date class
		 */
		test1= new Date(13,1,2010);
		test2=new Date(12,32,2010);
		test3=new Date(2,29,2010);
		test4 = new Date(2,29,2008);
		test5=new Date(11,31,2019);
		test6=new Date(11,1,2019);
				
		
	}

	@Test
	void test() {
		
		assertFalse(test1.isValid()); //Test case 1, testing 13/1/2010
		assertFalse(test2.isValid()); //Test case 2, testing 12/32/2010
		assertFalse(test3.isValid()); //Test case 3, testing 2/29/2010
		assertTrue(test4.isValid());  //Test case 4, testing 2/29/2008
		assertFalse(test5.isValid()); //Test case 5, testing 11/31/2019
		assertTrue(test6.isValid());  //Test case 6, testing 11/1/2019
		
		//fail("Not yet implemented");
	}

}

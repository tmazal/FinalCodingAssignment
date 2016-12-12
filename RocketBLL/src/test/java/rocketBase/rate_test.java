package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketBase.RateBLL;

public class rate_test {

	//DONE - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//DONE - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	@Test(expected = RateException.class)
	public void ExceptionTest() throws RateException{
		assertEquals(RateBLL.getRate(100), 0);
	}
	
	@Test
	public void TestScore() throws RateException{
		assertTrue(5 == RateBLL.getRate(600));
		assertTrue(3.5 == RateBLL.getRate(800));
	}
	
	@Test
	public void TestPayment(){
		//Asserts the payment matches the given known value. Error delta = half a cent
		assertEquals(RateBLL.getPayment(0.04/12, 360, 300000, 0, false), -1432.25, 0.005);
	}
}

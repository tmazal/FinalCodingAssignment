package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//DONE - RocketDAL rate_test (in BLL)
	//		Check to see if a known credit score returns a known interest rate
	
	//DONE - RocketDAL rate_test (in BLL)
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
	}
	
}

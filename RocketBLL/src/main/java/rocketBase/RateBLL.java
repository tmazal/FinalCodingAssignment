package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		double foundRate = 0;
		//DONE - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		
		//Iterates through the rates to find the lowest rate possible for our credit score
		for (int i = 0; i < rates.size(); i++){
			if(GivenCreditScore >= rates.get(i).getiMinCreditScore())
				foundRate = rates.get(i).getdInterestRate();
			//If no rate found, we throw exception with the first RDM
			if(foundRate == 0)
				throw new RateException(rates.get(0));
		}

		//DONE - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return foundRate;
		
	}
	
	
	//DONE - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}

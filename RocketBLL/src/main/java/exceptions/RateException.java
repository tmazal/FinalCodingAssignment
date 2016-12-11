package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	DONE - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	RateDomainModel exceptRate;

	public RateException(RateDomainModel rate){
		this.exceptRate = rate;
	}
	
	public RateDomainModel getExceptRate() {
		return exceptRate;
	}
	
	
}

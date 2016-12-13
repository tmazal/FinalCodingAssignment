package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import exceptions.RateException;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	DONE - RocketHub.messageReceived
			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			
			try {
				double rate = 0;
				double payment = 0;
				rate = RateBLL.getRate(lq.getiCreditScore())/1200;
				lq.setdRate(rate);
				//Payment returns negative for some reason.. We can easily fix *-1
				payment = -1*RateBLL.getPayment(rate, lq.getiTerm(), lq.getdAmount() - lq.getiDownPayment(), 0, false);
				lq.setdPayment(payment);
				sendToAll(lq);
			}
			catch (RateException e) {
				e.printStackTrace(); //shows error message
				sendToAll(lq); //stops processing
			}
		}
	}
}

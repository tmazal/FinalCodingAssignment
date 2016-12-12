package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MortgageController {

	private MainApp mainApp;
	
	//	DONE - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
		@FXML
		private TextField txtIncome;
		
		@FXML
		private TextField txtExpenses;
		
		@FXML
		private TextField txtCreditScore;
		
		@FXML
		private TextField txtHouseCost;
		
		@FXML
		private ComboBox cboxLoanTerm;

		@FXML
		private Label lblIncome;
		
		@FXML
		private Label lblExpenses;
		
		@FXML
		private Label lblCreditScore;
		
		@FXML
		private Label lblHouseCost;
		
		@FXML
		private Label lblPayment0;
		
		@FXML
		private Label lblPayment;
		
		@FXML
		private Label lblLoanTerm;
		
		@FXML
		private Button calcButton;
		
		@FXML
		private Label lblErrors0;
		
		@FXML
		private Label lblErrors;


	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {
		cboxLoanTerm.getItems().removeAll(cboxLoanTerm.getItems());
		cboxLoanTerm.getItems().addAll("15", "30");
		cboxLoanTerm.getSelectionModel().select("15");  
	}
	
	//	DONE - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	DONE - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		String output = cboxLoanTerm.getSelectionModel().getSelectedItem().toString();
		lq.setiTerm(Integer.parseInt(output));
		
		a.setLoanRequest(lq);
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	DONE - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double PITI;
		double calc1;
		double calc2;
		double roundPITI;
		
		calc1 = lRequest.getdIncome()*0.28;
		calc2 = lRequest.getdIncome()*0.36 - lRequest.getdExpenses();
		//if loop to see which calculation is lower for PITI
		if (calc1 < calc2)
			PITI = calc1;
		else
			PITI = calc2;
		
		roundPITI = (double) Math.round(PITI*100)/100;
		System.out.println(lRequest.getdPayment());
		System.out.println(lRequest.getdExpenses());
		System.out.println(lRequest.getdAmount());
		System.out.println(lRequest.getdRate());
		lRequest.setdPayment(roundPITI);
		
		//Is PITI replacing payment? Unsure..
		lblPayment.setText(String.valueOf(roundPITI));
		
	}
}

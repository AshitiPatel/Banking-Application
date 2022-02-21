import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */

/**
 * @author Ashiti
 *         Date: 18 January 2022
 *         Description: A class that manages the GIC account with the help of the Account class and updates (withdraws/deposits) from the balance according to the rules set by the bank
 *         Method List: GuaranteedInvestmentCertificate()	-	Default Constructor
 *         				GuaranteedInvestmentCertificate(Customer customer, Date start, Date end, double intRate, double penRate)	-	Overloaded Constructor
 *         				boolean withdraw(Date withdraw, double wAmmount)		-	Method to determine the interest rate based on the withdrawal date
 *						String toStringGIC()	-	Method to convert the private data into a display-able string
 *						double getInterestRate()	-	Method to get Interest rate
 *						void setInterestRate(double interestRate)	-	Method to set interest rate
 *						double getPenalty()	-	Method to get the penalty rate
 *						void setPenalty(double penalty)		-	Method to set penalty rate						
 *						static void main(String[] args)		-	Self-testing main method
 *
 */
public class GuaranteedInvestmentCertificate extends Account {

	/**
	 * Attributes
	 */
	private Date invDate, maturityDate;
	private double interestRate, penalty;

	/*
	 * Default Constructor
	 */
	public GuaranteedInvestmentCertificate() {
		super();
	}

	/**
	 * Overloaded Constructor
	 */
	public GuaranteedInvestmentCertificate(Customer customer, Date start, Date end, double intRate, double penRate) {
		super(customer);
		invDate = start;
		maturityDate = end;
		interestRate = intRate/100;
		penalty = penRate/100;
	}

	/***
	 * Method to determine the interest rate based on the withdrawal date
	 * @param withdraw
	 * @return
	 */
	public boolean withdraw(Date withdraw, double wAmmount) {

		//check if it is possible or not
		if(wAmmount <= this.getBalance()) {
			//add interest
			double interestEarned = this.getBalance() * interestRate;
			this.deposit(interestEarned);

			//withdraw the ammount (and penalty if required) accordingly
			if(withdraw.after(maturityDate) || (withdraw.compareTo(maturityDate) == 0)) {
				if(this.withdraw(wAmmount)) {
					return true;
				}
			}
			else if (withdraw.before(maturityDate)) {
				if(this.withdraw(wAmmount + (interestEarned*penalty))) {
					return true;
				}
			}
		}
		return false;

	}


	/**
	 * Method to convert the private data into a display-able string
	 * @return
	 */
	public String toStringGIC() {
		//setup date formatter
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 
		return this.toString() + "\nInvestment Date: " + dateFormat.format(invDate) + "\nMaturity Date: " + dateFormat.format(maturityDate) + 
				"\nInterest Rate: " + interestRate*100 + "\nPenalty Rate: " + penalty*100;
	}

	/**
	 * Method to get Interest rate
	 * @return
	 */
	public double getInterestRate() {
		return this.interestRate;
	}

	/**
	 * Method to set interest rate
	 * @param interestRate
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * Method to get the penalty rate
	 * @return the penalty
	 */
	public double getPenalty() {
		return penalty;
	}

	/**
	 * Method to set penalty rate
	 * @param penalty the penalty to set
	 */
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the invDate
	 */
	public Date getInvDate() {
		return invDate;
	}

	/**
	 * @return the maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}

	/**
	 * Self-testing main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// create dates and interest rate
		double intRate = 4;
		double penaltyRate = 20;
		Date start = new Date(121, 0, 21, 9, 12, 45);		//121 = 2021 (20 is replaced by 1 because 20-19=1) and the months are entered like they start from 0
		Date end = new Date(122, 02, 27, 15, 56, 9);
		Date withdrawBefore = new Date(122, 0, 23, 19, 47, 48);
		Date withdrawOn = new Date(122, 2, 27, 20, 17, 23);
		Date withdrawAfter = new Date(122, 2, 30, 9, 6,12);

		//create customer
		Customer custom = new Customer("Ashiti", "Patel", "Earth", 92736676);

		// create
		GuaranteedInvestmentCertificate gic1 = new GuaranteedInvestmentCertificate(custom, start, end, intRate, penaltyRate);

		//deposit money in account
		gic1.deposit(10000);
		
		//test to string method
		System.out.println(gic1.toStringGIC());

		if (gic1.withdraw(withdrawAfter, 100)) {
			System.out.println("Balance after withdrawing $100 after maturity date: " + gic1.getBalance());
		}
		if (gic1.withdraw(withdrawOn, 100)) {
			System.out.println("Balance after withdrawing $100 on maturity date: " + gic1.getBalance());
		}
		if (gic1.withdraw(withdrawBefore, 100)) {
			System.out.println("Balance after withdrawing $100 before maturity date: " + gic1.getBalance());
		}
		if(gic1.withdraw(withdrawAfter, 100000)) {
			System.out.println("entered inside the condition that was supposed to be false");
		}

	}

}

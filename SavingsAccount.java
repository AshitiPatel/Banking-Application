/**
 * 
 */

/**
 * @author Ashiti
 * Date: 19 January 2022
 * Description:  A class that manages the Savings account with the help of the Account class and updates (withdraws/deposits) from the balance according to the rules set by the bank
 * Method List: SavingsAccount()	-	Default Constructor 
 * 				SavingsAccount(Customer customer, double transFeesReq, double minBalanceReq)	-	Overloaded Constructor
 *				boolean withdrawSA(double withdrawAmmount)		-	Method that checks whether a transaction fee is required and returns
 *				String toStringSA()		-	Method too convert data into display-able string
 *				double getTransactionFees()		-	Method to get the transaction fees
 *				void setTransactionFees(double transactionFees)	-	Method to set transaction fees
 *				double getMinBalance()	-	Method to get minimum balance
 *				void setMinBalance(double minBalance)	-	Method to set minimum balance
 *				static void main(String[] args)		-	Self-testing main method
 *
 */
public class SavingsAccount extends Account{

	// attributes
	private double transactionFees, minBalance;

	/**
	 * Default constructor
	 */
	public SavingsAccount() {
		super();
	}

	/**
	 * Overloaded Constructor
	 * @param customer
	 * @param transFeesReq
	 * @param minBalanceReq
	 */
	public SavingsAccount(Customer customer, double transFeesReq, double minBalanceReq) {
		super(customer);
		transactionFees = transFeesReq;
		minBalance = minBalanceReq;
	}

	/**
	 * Method that checks whether a transaction fee is required and returns
	 * accordingly
	 * @param account
	 * @return
	 */
	public boolean withdrawSA(double withdrawAmmount) {
		//check whether transaction fees need to be charged and whether it is possible
		if((this.getBalance() < minBalance) && ((withdrawAmmount+transactionFees) <= this.getBalance())) {
			//withdraw
			if(this.withdraw(withdrawAmmount+transactionFees)) {
				return true;
			}
		}
		//check whether the ammount in the account is >= the withdrawal request
		else if((this.getBalance() >= minBalance) && this.getBalance() >= withdrawAmmount) {
			//if approved, withdraw
			if(this.withdraw(withdrawAmmount)) {
				return true;
			}
		}
		return false; //insufficient funds
	}

	/**
	 * Method too convert data into display-able string
	 * @return
	 */
	public String toStringSA() {
		return this.toString() + "\nMinimum Balance: " + minBalance + "\nTransaction Fees: " + transactionFees;
	}

	/**
	 * Method to get the transaction fees
	 * @return
	 */
	public double getTransactionFees() {
		return this.transactionFees;
	}

	/**
	 * Method to set transaction fees
	 * @param transactionFees
	 */
	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}

	/**
	 * Method to get minimum balance
	 * @return the minBalance
	 */
	public double getMinBalance() {
		return minBalance;
	}

	/**
	 * Method to set minimum balance
	 * @param minBalance the minBalance to set
	 */
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) {

		//test default constructor
		SavingsAccount saveAcc1 = new SavingsAccount();

		//test setters and getters
		saveAcc1.setMinBalance(4000);
		saveAcc1.setTransactionFees(4.25);

		System.out.println("---First Savings Account Data---");
		System.out.println("Minimum Balance: " + saveAcc1.getMinBalance());
		System.out.println("Transaction Fees: " + saveAcc1.getTransactionFees());

		//create customer
		Customer custom = new Customer("Ashiti", "Patel", "Earth", 92736676);

		//test the overloaded constructor
		SavingsAccount saveAcc2 = new SavingsAccount(custom, 4.25, 4000);

		//deposit
		saveAcc2.deposit(10000);

		//test to string method
		System.out.println("---Second Savings Account---");
		System.out.println(saveAcc2.toStringSA());

		//test the withdraw method
		if(saveAcc2.withdrawSA(5000)) {
			System.out.println("Balance after withdrawing $5000: " + saveAcc2.getBalance());
		}
		if(saveAcc2.withdrawSA(2000)) {
			System.out.println("Balance after withdrawing $2000: " + saveAcc2.getBalance());
		}
		if(saveAcc2.withdrawSA(500)) {
			System.out.println("Balance after withdrawing $500: " + saveAcc2.getBalance());
		}
		if(saveAcc2.withdrawSA(9000)) {
			System.out.println("should not have gotten in here!!!!");
		}

	}

}

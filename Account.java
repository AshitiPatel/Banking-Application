import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 
 */

/**
 * @author Ashiti
 *         Date: 18 January 2022
 *         Description: A class that is responsible to keep track of the balance of the respective customer and store their account number
 *         Method List:	 Account()	-	Default Constructor
 *         				Account(Customer theOwner)	-	Overloaded Constructor
 * 						void deposit(double depositAmt)		-	A method to deposit money
 * 						boolean withdraw(double withdrawAmt)		-	Method to withdraw money
 * 						String toString()	-	Method to convert the data into displayable string
 * 						double getBalance()		-	Method to get balance
 * 						long getAccountNumber()		-	Method to get the account number
 * 						Customer getCustomer()	-	Method to get the customer
 * 						void setCustomer(Customer customer)		-	Method to set customer (I don't know why this would be required though)
 * 						static void main(String[] args)		-	Self-testing main method
 * 
 */
public class Account {

	private double balance;
	private long accountNumber;
	private Customer customer;

	/**
	 * Default constructor
	 */
	public Account() {
		// initialize the attributes
		customer = new Customer();
		balance = 0;
		accountNumber = (long) Math.ceil(Math.random() * 1000000000000L);
	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param balanceSet
	 */
	public Account(Customer theOwner) {
		// initialize the attributes
		customer = theOwner;
		balance = 0;
		accountNumber = (long) Math.ceil(Math.random() * 1000000000000L);
	}

	/**
	 * A method to deposit
	 * 
	 * @param depositAmt
	 * @return
	 */
	public void deposit(double depositAmt) {
		balance = balance + depositAmt;
	}

	/**
	 * Method to withdraw
	 * 
	 * @param withdrawAmt
	 * @return
	 */
	public boolean withdraw(double withdrawAmt) {
		if ((balance - withdrawAmt) >= 0) {
			balance = balance - withdrawAmt;
			return true;
		}
		return false;
	}

	/**
	 * Method to convert the data into displayable string
	 * @return
	 */
	public String toString() {
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		return this.getCustomer().toString() + "\nAccount Number: " + this.getAccountNumber() + "\nCurrent Balance: $" + formatter.format(this.getBalance());
	}

	/**
	 * Method to get balance
	 * 
	 * @return
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Method to get the account number
	 * 
	 * @return
	 */
	public long getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * Method to get the customer
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Method to set customer
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) {

		//create account (test default constructor)
		Account defaultAcc = new Account();

		// deposit
		defaultAcc.deposit(10000);

		//test setter for customer
		Customer setterTest = new Customer("Tathya", "Patel", "Mars", 908754535);
		defaultAcc.setCustomer(setterTest);

		// test the getter methods
		System.out.println(defaultAcc.getCustomer().toString());
		System.out.println("Account Number: " + defaultAcc.getAccountNumber());
		System.out.println("Balance: " + defaultAcc.getBalance());

		// create account
		Customer customerSample = new Customer("Ashiti", "Patel", "Earth", 1234567890);

		//use overloaded constructor
		Account overAcc = new Account(customerSample);
		overAcc.deposit(7000);

		//test to string method
		System.out.println(overAcc.toString());

		// withdraw successful 
		if (defaultAcc.withdraw(6000)) {
			System.out.println("withdrawal of $6000 successful\nNew balance: " + defaultAcc.getBalance());
		}

		// withdraw fail 
		if (!overAcc.withdraw(9000)) {
			System.out.println("Withdrawal of $9000 uncessfull");
		}
	}
}

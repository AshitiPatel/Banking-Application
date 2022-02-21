import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */

/**
 * @author Ashiti
 * Date: 21 January 2022
 * Description: a class that keeps a record of a transaction and all of it's details in a file for future reference 
 * Method List: TransactionRecord() -	Default Constructor
 * 				TransactionRecord(char accType, String tType, double tAmmount, double sBal, double eBal) 	-	Overloaded Constructor
 * 				String toString()	-	Method to convert data into display able string 
 * 				char getAccountType()	-	Method to get account type
 * 				void setAccountType(char accountType)	-	Method to set account type
 * 				String getTransactionType()		-	Method to get transaction type
 * 				void setTransactionType(String transactionType)		-	Method to set transaction type
 * 				double getTransactionAmmount()	-	Method to get transaction ammount
 * 				void setTransactionAmmount(double transactionAmmount)	-	Method to set transaction ammount
 * 				double getStartBalance()	-	Method to set start balance
 * 				void setStartBalance(double startBalance)	-	Method to set start balance
 * 				double getEndBalance()	-	Method to get end balance
 * 				void setEndBalance(double endBalance)	-	Method to set end balance
 * 				void setTransactionTime(Date transactionTime)	-	Method to set transaction time'
 * 				Date getTransactionTime()	-	Method to get transaction time
 * 				void processRecord(String rec)	-	void processRecord(String rec)
 * 				void processRecord(String rec)	-	Method to process and store input into respective attributes
 * 				static void main(String[] args)		-	Self-testing main method
 *
 */
public class TransactionRecord {

	//attributes
	private char accountType;
	private String transactionType;
	private double transactionAmmount, startBalance, endBalance;
	private Date transactionTime;

	/**
	 * Default Constructor
	 */
	public TransactionRecord() {
		this.accountType = ' ';
		this.transactionType = "";
		this.transactionAmmount = 0;
		this.startBalance = 0;
		this.endBalance = 0;
		this.transactionTime = new Date();
	}

	/**
	 * Overloaded Constructor
	 * @param accType
	 * @param tType
	 * @param tAmmount
	 * @param sBal
	 * @param eBal
	 */
	public TransactionRecord(char accType, String tType, double tAmmount, double sBal, double eBal, Date time) {
		this.accountType = accType;
		this.transactionType = tType;
		this.transactionAmmount = tAmmount;
		this.startBalance = sBal;
		this.endBalance = eBal;
		this.transactionTime = time;
	}

	/**
	 * Method to convert data into display able string 
	 */
	public String toString() {

		//string to return later
		String display = "Acc: ";

		//setup date formatter
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		//convert char to respective string
		if(this.accountType == 'g') {
			display = display + "GIC, ";
		}
		else {
			display = display + "Savings, ";
		}

		//add the attributes to the string
		display = display + "Transaction: " + this.transactionType + 
				", T. Ammount: $" + transactionAmmount + 
				", Start Balance: $" + this.startBalance + 
				", End Balance: $" + this.endBalance + ", Transaction Time: " + dateFormat.format(this.transactionTime);

		//return
		return display;
	}

	/**
	 * Method to process the string inputed and store data into respective variables
	 * @param rec
	 */
	public void processRecord(String rec) {
		//split the string into respective parts to store
		String details[] = rec.split("/");
		
		//assign 
		this.accountType = details[0].charAt(0);
		this.transactionType = details[1];
		this.transactionAmmount = Long.parseLong(details[2]);
		this.startBalance = Long.parseLong(details[3]);
		this.endBalance = Long.parseLong(details[4]);
		String times[] = details[5].split(",");	//split the last string for time
		this.transactionTime = new Date(Integer.parseInt(times[0])-1900, Integer.parseInt(times[1])-1, Integer.parseInt(times[2]), Integer.parseInt(times[3]), Integer.parseInt(times[4]), Integer.parseInt(times[5]));
	}
	
	
	/**
	 * Method to get account type
	 * @return the accountType
	 */
	public char getAccountType() {
		return accountType;
	}

	/**
	 * Method to set account type
	 * @param accountType the accountType to set
	 */
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	/**
	 * Method to get transaction type
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Method to set transaction type
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * Method to get transaction ammount
	 * @return the transactionAmmount
	 */
	public double getTransactionAmmount() {
		return transactionAmmount;
	}

	/**
	 * Method to set transaction ammount
	 * @param transactionAmmount the transactionAmmount to set
	 */
	public void setTransactionAmmount(double transactionAmmount) {
		this.transactionAmmount = transactionAmmount;
	}

	/**
	 * Method to set start balance
	 * @return the startBalance
	 */
	public double getStartBalance() {
		return startBalance;
	}

	/**
	 * Method to set start balance
	 * @param startBalance the startBalance to set
	 */
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * Method to get end balance
	 * @return the endBalance
	 */
	public double getEndBalance() {
		return endBalance;
	}

	/**
	 * Method to set end balance
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * Method to get transaction time
	 * @return the transactionTime
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}

	/**
	 * Method to set transaction time
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) {

		//create using default constructor
		TransactionRecord rec1 = new TransactionRecord();

		//create date object
		Date time = new Date(122, 0, 30, 21, 59, 30);

		//setup date formatter
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//test setters and getters
		rec1.setAccountType('s');
		rec1.setStartBalance(5000);
		rec1.setTransactionAmmount(2000);
		rec1.setTransactionType("Deposit");
		rec1.setEndBalance(7000);
		rec1.setTransactionTime(time);
		System.out.println("Account Type: " + rec1.getAccountType());
		System.out.println("Start Balance: " + rec1.getStartBalance());
		System.out.println("Transaction Type: " + rec1.getTransactionType());
		System.out.println("Transaction Ammount: " + rec1.getTransactionAmmount());
		System.out.println("End Balance: " + rec1.getEndBalance());
		System.out.println("Transaction Time: " + dateFormat.format(rec1.getTransactionTime()) + "\nTESTING OVERLOADED CONSTRUCTOR AND TOSTRING");

		//test overloaded constructor and to string
		TransactionRecord rec2 = new TransactionRecord('s', "Withdraw", 5000, 10000, 5000, time);
		System.out.println(rec2.toString());
	}
}

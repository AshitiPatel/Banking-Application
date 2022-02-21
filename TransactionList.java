import java.util.Date;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashiti
 * Date: 21 January 2022
 * Description: A class that keeps track of all transactions and their details using the transaction records class and updates the list (insert, delete and sort) according to the user's needs
 * Method List: TransactionList()	-	Default Constructor
 * 				boolean increaseMaxSize(int reqMaxSize)		-	Method to increase the max size of the list to accommodate more records
 * 				boolean insert(TransactionRecord recToInsert) 	-	Method to insert record
 * 				int linearSearch(Date timeToSearch)		-	Method to search for a specific record using linear search algorithm
 * 				boolean delete(Date timeOfRecToDelete)	-	Method to delete a record from a list
 * 				void selectionSort()	-	Method to sort the record list according to the ascending oder of dates
 * 				TransactionRecord[] getList()	-	Method to get list
 *				int getMaxSize()	-	Method to get max size
 *				int getSize()	-	Method to get size
 *				String toString()	-	converting data into display able string
 * 				static void main(String[] args)		-	Self-testing main method
 * 
 */
public class TransactionList {

	/**
	 * Attributes
	 */
	private TransactionRecord list[];
	private int maxSize, size;

	/**
	 * Default Constructor
	 */
	public TransactionList() {
		//initialize data
		this.maxSize = 10;
		this.size = 0;
		this.list = new TransactionRecord[maxSize];
	}

	/**
	 * Method to increase the max size of the list to accommodate more
	 * records
	 * @param reqMaxSize
	 * @return
	 */
	public boolean increaseMaxSize(int reqMaxSize) {

		if(reqMaxSize > this.maxSize) {

			// temporary list
			TransactionRecord tempList[] = new TransactionRecord[reqMaxSize];

			// for loop to go through the list one by one
			for (int i = 0; i < this.maxSize; i++) {
				// copy the element into the new list
				tempList[i] = this.list[i];
			}

			// store all values from new list to the old one
			this.list = tempList;
			this.maxSize = reqMaxSize;
			return true;
		}
		return false;
	}

	/**
	 * Method to insert a record
	 * 
	 * @param recToInsert
	 * @return
	 */
	public boolean insert(TransactionRecord recToInsert) {
		if (size < maxSize) { // check if a new record can be accomodated
			size++; // inc current size
			list[size - 1] = recToInsert; // add new record in the end
			return true;
		}
		if(increaseMaxSize(maxSize+10)) {
			size++; // inc current size
			list[size - 1] = recToInsert; // add new record in the end
			return true;
		}
		return false; //it is never going to get here
	}

	/**
	 * Method to search for a specific record using linear search algorithm
	 * @param recToSearch
	 * @return
	 */
	public int linearSearch(Date timeToSearch) {

		//loop to check each element 
		for(int i = 0; i < this.size; i++) {

			//checking whether the record is found
			if(list[i].getTransactionTime() == timeToSearch) {
				return i;	//found it!!
			}
		}
		return -1;	//not found
	}

	/**
	 * Method to delete a record from a list
	 * @param recToDelete
	 * @return
	 */
	public boolean delete(Date timeOfRecToDelete) {
		//look for the record
		int loc = linearSearch(timeOfRecToDelete);

		if(loc >= 0) {
			this.list[loc] = this.list[size - 1]; // put last record where the record to be deleted is found
			size--; // decrease the valid size
			return true;
		}
		return false;	
	}

	/**
	 * Method to sort the record list according to the ascending order of dates 
	 */
	public void selectionSort() {
		//loop to go through each element except for the first one
		for (int i = this.size - 1; i > 0; i--) {
			int bigLoc = 0;		//location of the element with greater balance
			for (int j = 1; j <= i; j++) {		//loop to compare with all the previous records

				if(list[j].getTransactionTime().after(list[bigLoc].getTransactionTime())) {//swaping if a later date is found
					bigLoc = j;
				}

				//				if (list[j].getEndBalance() > list[bigLoc].getEndBalance()) {	//changing the bigLoc if greater balance found
				//					bigLoc = j;
				//				}
			}
			//switching when right location found
			TransactionRecord temp = list[i];	
			list[i] = list[bigLoc];
			list[bigLoc] = temp;
		}
	}

	/**
	 * Converting data into display able string
	 */
	public String toString() {
		String theList = "";//output variable
		//loop to go through each valid element
		for(int i = 0; i < this.size; i++) {
			//add record to output
			theList = theList + "Record " + (i+1) + ": " + list[i].toString() + "\n";
		}
		return theList;
	}



	/**
	 * @return the list
	 */
	public TransactionRecord[] getList() {
		return list;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) {

		//create transaction list object
		TransactionList transactions = new TransactionList();

		while(true) {

			char choice;
			choice = JOptionPane.showInputDialog("i - insert\n" +
					"e - exit\n" + 
					"d - delete\n" + 
					"s - search\n" + 
					"S - sort\n" + 
					"p - print").charAt(0);

			//break out of the loop when e
			if(choice == 'e') {
				break;
			}

			switch (choice) {

			case 'i': {
				//prompt
				String rec = JOptionPane.showInputDialog("Insert Transaction details in the format\n" +
						"<account type/transaction type/transaction ammount/starting balance/ending balance/date (yyyy,mm,dd,hh,mm,ss)>");

				//create temp record
				TransactionRecord tInfo = new TransactionRecord();
				tInfo.processRecord(rec);	//process record

				//test the insert method 
				if(transactions.insert(tInfo)) {
					JOptionPane.showMessageDialog(null, "record inserted");
				}
				else {
					JOptionPane.showMessageDialog(null, "insert failed");
				}
				break;
			}//insert 
			case 's': {
				//prompt
				String time = JOptionPane.showInputDialog("Insert Time of the record that needs to be searched\n(yyyy,mm,dd,hh,mm,ss)");

				//split into times (to create date) and get location using search method
				String times[] = time.split(",");
				int location = transactions.linearSearch(new Date(Integer.parseInt(times[0])-1900, Integer.parseInt(times[1])-1, Integer.parseInt(times[2]), Integer.parseInt(times[3]), Integer.parseInt(times[4]), Integer.parseInt(times[5])));

				//check whether record found and show appropriate message
				if(location >= 0) {
					JOptionPane.showMessageDialog(null, transactions.getList()[location].toString());
				}
				else {
					JOptionPane.showMessageDialog(null, "Record not found!!");
				}
				break;
			}//search case
			case'd': {

				//prompt
				String time = JOptionPane.showInputDialog("Insert Time of the record that needs to be searched\n(yyyy,mm,dd,hh,mm,ss)");

				//split into times (to create date)
				String times[] = time.split(",");

				//try to delete record using method and show appropriate message
				if(transactions.delete(new Date(Integer.parseInt(times[0])-1900, Integer.parseInt(times[1])-1, Integer.parseInt(times[2]), Integer.parseInt(times[3]), Integer.parseInt(times[4]), Integer.parseInt(times[5])))) {
					JOptionPane.showMessageDialog(null, "Record deleted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Record deletion failed");
				}
				break;
			}//delete case
			case'S' : {	
				//call method to sort
				transactions.selectionSort();
			}//sort
			case'p': {
				JOptionPane.showMessageDialog(null, transactions.toString());
			}//print case
			}//switch case
		}
	}
}

/**
 * 
 */

/**
 * @author Ashiti
 *         Date: 18 January 2022
 *         Description: A class that keeps the customer's details and provides ways to update and retrieve this information using methods, and contains a default constructor and overloaded constructors to make creating objects of this class as flexible as possible
 *         Method List: Customer()	-	Default Constructor
 *         				Customer(String firstNameToSet, String lastNameToSet, String addressToSet, long phoneNumberToSet)	-	Overloaded Constructor
 *         				String getFirstName()	-	Method to get first name
 *         				void setFirstName(String firstname)		-	Method to set first name
 *         				String getLastName()	-	Method to get last name
 * 						void setLastName(String lastname)	-	Method to set last name
 * 						String getAddress()		-	Method to get address
 * 						void setAddress(String address)		-	Method to set address
 * 						long getPhoneNumber()	-	Method to get phone number
 * 						void setPhoneNumber(long phoneNumber)	-	Method to set phone number
 * 						String toString()	-	A method to return the record details as a displayable string
 * 						static void main(String[] args)		-	Self-testing main method
 */
public class Customer {

	/**
	 * Attributes
	 */
	private String firstName, lastName, address;
	private long phoneNumber;

	/**
	 * Default Constructor
	 */
	public Customer() {
		// initialize the attributes to null
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.phoneNumber = 0;
	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param nameToSet
	 * @param addressToSet
	 * @param phoneNumberToSet
	 */
	public Customer(String firstNameToSet, String lastNameToSet, String addressToSet, long phoneNumberToSet) {
		// initialize the attributes
		this.firstName = firstNameToSet;
		this.lastName = lastNameToSet;
		this.address = addressToSet;
		this.phoneNumber = phoneNumberToSet;
	}

	/**
	 * Method to get first name
	 * 
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method to set first name
	 * 
	 * @param name
	 */
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * Method to get last name
	 * 
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method to set last name
	 * 
	 * @param name
	 */
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * Method to get address
	 * 
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Method to set address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to get phone number
	 * 
	 * @return
	 */
	public long getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Method to set phone number
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * A method to return the record details as a displayable string
	 * 
	 * @return
	 */
	public String toString() {
		return "Customer Name: " + this.getFirstName() + " " + this.getLastName() + "\n" + "Phone Number: "
				+ this.getPhoneNumber() + "\nAddress: " + this.getAddress();

	}

	/**
	 * Self-testing main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// create a customer using default constructor
		Customer a = new Customer();

		// test the setter methods
		a.setFirstName("Ashiti");
		a.setLastName("Patel");
		a.setAddress("Earth");
		a.setPhoneNumber(123456789);

		// test the getter methods
		System.out.println("testing getter methods\nName: " + a.getFirstName() + " " + a.getLastName());
		System.out.println("Address: " + a.getAddress());
		System.out.println("Phone Number: " + a.getPhoneNumber());

		// test oveloaded constructor
		Customer b = new Customer("Tathya", "Patel", "Milky Way", 987654321);

		// test to string method
		System.out.println(b.toString());

	}

}

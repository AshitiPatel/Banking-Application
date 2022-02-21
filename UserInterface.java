import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * 
 */

/**
 * @author Ashiti
 *         Date: 20 January 2022
 *         Description: A class that is responsible for the user interface, i.e., the application's execution
 *         Method List:	UserInterface()		-	Default constructor 
 *         void actionPerformed(ActionEvent e)	-	Method that performs tasks according to the buttons clicked
 *         void performTransactions() throws IOException	-	Method to perform the transactions
 *         void readPreviousAccountDetails() throws IOException		-	Method to read previous account details (account number and password)
 *         void writeAccountDetails() throws IOException	-	Method to write details (acc num and pwd) for each account into file
 *         void customerDataSave() throws IOException	-	Save current customer's data into a new file
 *         int checkAccountDetails(long accNum, String pwd)		-	Method to check details from previously existing accounts
 *         static void main(String[] args)	-	Main method
 */
public class UserInterface extends JFrame implements ActionListener {

	/**
	 * Private attributes
	 */
	private JFrame loginFrame, createAccFrame1, createAccFrame2;
	private Customer customer;
	private SavingsAccount savings;
	private GuaranteedInvestmentCertificate gic;
	private double penaltyAmmount, minBal, intRate, penaltyRate;
	private TextPicture bankName;
	private ArrayList<String> passwords;
	private ArrayList<Long> accountNumbers;

	//for login frame
	private JPanel inputPanelLogin, controlPanelLogin;
	private JLabel lblAccNumLogin, lblPwdLogin;
	private JTextField accNumInputLogin;
	private JPasswordField pwdInputLogin;
	private JButton btnLogin, btnCreateAcc;
	private ImagePicture bankLogo; 

	//for first account creation frame
	private JLabel lblNameInp, lblAddressInp, lblPhoneNumInp, lblSABal, lblGICBal, lblGICMaturity;
	private JTextField inpName, inpAddress, inpPhoneNum, inpSABal, inpGICBal, inpGICMaturity;
	private JButton btnContinueAccCreation;
	private JPanel inpCreation1, controlCreation1;

	//for the second account creation frame
	private JLabel lblSavAccNum, lblGICAccNum, lblPwd2;
	private JTextArea savAccNumDisp, gicAccNumDisp;
	private JPasswordField pwdNewPwdCreation;
	private JButton btnAccCreationFinish;
	private JPanel inpCreation2, controlCreation2;

	/**
	 * Default Constructor
	 * @throws IOException 
	 */
	public UserInterface() throws IOException {

		//create JFrames for the entire application
		loginFrame = new JFrame("Blue Nova Bank Login");
		createAccFrame1 = new JFrame("Create Account at Blue Nova Bank");
		createAccFrame2 = new JFrame("Create Account at Blue Nova Bank");

		//read previous accounts
		//readPreviousAccountDetails();

		/**
		 * Login Window setup
		 */
		// setup JPanels and layout
		loginFrame.setLayout(new GridLayout(3, 1));
		inputPanelLogin = new JPanel();
		controlPanelLogin = new JPanel();

		// setup ImagePicture for bank logo
		bankLogo = new ImagePicture(new ImageIcon("blueNovaLogo.png"), 135, 0);

		//setup TextPicture for bank name
		bankName = new TextPicture("Blue Nova Bank", 115, 70);
		bankName.setC(Color.BLUE);

		// setup input panel components
		lblAccNumLogin = new JLabel("Account #:");
		lblPwdLogin = new JLabel("Password:");
		accNumInputLogin = new JTextField("", 25);
		pwdInputLogin = new JPasswordField("", 25);

		// setup components for control panel
		btnCreateAcc = new JButton("Create Account");
		btnLogin = new JButton("Login");

		// add panels and logo to frame
		loginFrame.add(bankLogo);
		loginFrame.add(inputPanelLogin);
		loginFrame.add(controlPanelLogin);

		// add components to panels
		inputPanelLogin.add(lblAccNumLogin);
		inputPanelLogin.add(accNumInputLogin);
		inputPanelLogin.add(lblPwdLogin);
		inputPanelLogin.add(pwdInputLogin);
		controlPanelLogin.add(btnCreateAcc);
		controlPanelLogin.add(btnLogin);

		// add listener to buttons
		btnCreateAcc.addActionListener(this);
		btnLogin.addActionListener(this);

		// setup the frame
		loginFrame.setSize(400, 400);
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);
		loginFrame.setLocation(20, 20);
		loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/**
		 * Account Creation Window setup
		 */	
		//set layout and create panels
		createAccFrame1.setLayout(new GridLayout(3, 1));
		inpCreation1 = new JPanel();
		controlCreation1 = new JPanel();

		//setup input panel components
		lblNameInp = new JLabel("Full Name:");
		inpName = new JTextField("FirstName LastName", 25);
		lblAddressInp = new JLabel("Address:");
		inpAddress = new JTextField("", 25);
		lblPhoneNumInp = new JLabel("Phone Number:");
		inpPhoneNum = new JTextField("", 25);
		lblSABal = new JLabel("Savings Account Balance:");
		inpSABal = new JTextField("", 20);
		lblGICBal = new JLabel("GIC Balance:");
		inpGICBal = new JTextField("", 25);
		lblGICMaturity = new JLabel("GIC Maturity Date:");
		inpGICMaturity = new JTextField("yyyy-mm-dd", 20);

		//setup control panel component
		btnContinueAccCreation = new JButton("Continue");

		//add panels and logo to frame
		createAccFrame1.add(bankLogo);
		createAccFrame1.add(inpCreation1);
		createAccFrame1.add(controlCreation1);

		//add components to panels
		inpCreation1.add(lblNameInp);
		inpCreation1.add(inpName);
		inpCreation1.add(lblAddressInp);
		inpCreation1.add(inpAddress);
		inpCreation1.add(lblPhoneNumInp);
		inpCreation1.add(inpPhoneNum);
		inpCreation1.add(lblSABal);
		inpCreation1.add(inpSABal);
		inpCreation1.add(lblGICBal);
		inpCreation1.add(inpGICBal);
		inpCreation1.add(lblGICMaturity);
		inpCreation1.add(inpGICMaturity);
		controlCreation1.add(btnContinueAccCreation);

		//add listener to button
		btnContinueAccCreation.addActionListener(this);

		//setup for the frame
		createAccFrame1.setSize(400, 500);
		createAccFrame1.setResizable(false);
		createAccFrame1.setVisible(false);
		createAccFrame1.setLocation(20, 20);
		createAccFrame1.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/**
		 * Second Account Creation Window setup
		 */
		//set layout
		createAccFrame2.setLayout(new GridLayout(3, 1));
		inpCreation2 = new JPanel();
		controlCreation2 = new JPanel();

		//setup input panel components
		lblSavAccNum = new JLabel("Savings Account Number:");
		lblGICAccNum = new JLabel("GIC Account Number:");
		savAccNumDisp = new JTextArea(1, 18);
		gicAccNumDisp = new JTextArea(1, 18);
		savAccNumDisp.setEditable(false);
		gicAccNumDisp.setEditable(false);
		lblPwd2 = new JLabel("Password:");
		pwdNewPwdCreation = new JPasswordField("", 25);

		//setup control panel component
		btnAccCreationFinish = new JButton("Finish");

		//add panels and bank name to frame
		createAccFrame2.add(bankName);
		createAccFrame2.add(inpCreation2);
		createAccFrame2.add(controlCreation2);

		//add components to panels
		inpCreation2.add(lblSavAccNum);
		inpCreation2.add(savAccNumDisp);
		inpCreation2.add(lblGICAccNum);
		inpCreation2.add(gicAccNumDisp);
		inpCreation2.add(lblPwd2);
		inpCreation2.add(pwdNewPwdCreation);
		controlCreation2.add(btnAccCreationFinish);

		//add listener to button
		btnAccCreationFinish.addActionListener(this);

		//setup for the frame
		createAccFrame2.setSize(400, 400);
		createAccFrame2.setResizable(false);
		createAccFrame2.setVisible(false);
		createAccFrame2.setLocation(20, 20);
		createAccFrame2.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * Method that performs tasks according to the buttons clicked
	 */
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnLogin) {

			//read password and account number 
			accNumInputLogin.getText();
			//store password in the array of chars and then add to string
			char pwd[] = pwdInputLogin.getPassword();
			String password = "";
			for(int i = 0; i < pwd.length; i++) {
				password = password + pwd[i];
			}

			//compare with pre-existing accounts by calling method


			//return invalid message and prompt again if not found (allow three trys)


		}

		else if(e.getSource() == btnCreateAcc) {
			//set login frame visible to false
			loginFrame.setVisible(false);

			//set account creation's first frame visible
			createAccFrame1.setVisible(true);
		}
		else if(e.getSource() == btnContinueAccCreation) {

			//get text from the fields and create the customer object
			String fullName = inpName.getText();
			String name[] = fullName.split(" ");

			customer = new Customer(name[0], name[1], inpAddress.getText(), Long.parseLong(inpPhoneNum.getText()));

			//set the default minimum balance and penalty for now (SAVINGS)
			penaltyAmmount = 4.25;
			minBal = 4000;

			//set the default interest and penalty rates for now (GIC)
			intRate = 4;
			penaltyRate = 20;

			//create the maturity date to pass in (GIC)
			String dateParts[] = inpGICMaturity.getText().split("-");
			Date maturity = new Date(Integer.parseInt(dateParts[0])-1900, Integer.parseInt(dateParts[1])-1, Integer.parseInt(dateParts[2]));

			//create the respective gic and savings accounts and deposit the balances entered
			savings = new SavingsAccount(customer, penaltyAmmount, minBal);
			savings.deposit(Long.parseLong(inpSABal.getText()));
			gic = new GuaranteedInvestmentCertificate(customer, new Date(), maturity, intRate, penaltyRate);
			gic.deposit(Long.parseLong(inpGICBal.getText()));

			//set the account numbers for next window
			savAccNumDisp.setText(Long.toString(savings.getAccountNumber()));
			gicAccNumDisp.setText(Long.toString(gic.getAccountNumber()));

			//set the next frame visible and this one invisible
			createAccFrame1.setVisible(false);
			createAccFrame2.setVisible(true);
		}
		else if(e.getSource() == btnAccCreationFinish) {

//			//store password in the array of chars and create a string to write to file
//			char pwd[] = pwdNewPwdCreation.getPassword();
//			String password = "";
//			for(int i = 0; i < pwd.length; i++) {
//				password = password + pwd[i];
//			}
//
//			//store the new account numbers and  
//			accountNumbers.add(Long.parseLong(savAccNumDisp.getText()));
//			passwords.add(password);
//			accountNumbers.add(Long.parseLong(gicAccNumDisp.getText()));
//			passwords.add(password);
//
//			try {
//				performTransactions();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			try {
				performTransactions();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	/**
	 * Method to perform the transactions
	 * @throws IOException
	 */
	public void performTransactions() throws IOException {
		//custom button text
		UIManager.put("OptionPane.yesButtonText", "Savings");
		UIManager.put("OptionPane.noButtonText", "GIC");

		//account choice
		int savOrGIC = JOptionPane.showConfirmDialog(null, "Select Account", "Select Account", JOptionPane.YES_NO_CANCEL_OPTION);

		//keep the cycle going
		while(savOrGIC == 0 || savOrGIC == 1) {

			//change button text
			UIManager.put("OptionPane.yesButtonText", "Withdraw");
			UIManager.put("OptionPane.noButtonText", "Deposit");

			//savings selected
			if(savOrGIC == 0) {
				//transaction type
				int wOrD = JOptionPane.showConfirmDialog(null, "Select Transaction Type", "Select Transaction Type", JOptionPane.YES_NO_OPTION);

				//withdraw selected
				if(wOrD == 0) {
					//prompt for ammount
					double ammount = Double.parseDouble(JOptionPane.showInputDialog("Enter ammount to withdraw: "));

					//is withdraw possible
					if(savings.withdrawSA(ammount)) {
						JOptionPane.showMessageDialog(null, "Transaction Successful\n" + savings.toStringSA());
					}
					else {
						JOptionPane.showMessageDialog(null, "Insufficient funds");
					}
				}
				else if(wOrD == 1) {//deposit

					//prompt for ammount
					double ammount = Double.parseDouble(JOptionPane.showInputDialog("Enter ammount to deposit: "));
					//deposit
					savings.deposit(ammount);
					//message
					JOptionPane.showMessageDialog(null, "Transaction Successful\n" + savings.toStringSA());
				}
			}
			else if(savOrGIC ==1) {

				//transaction type
				int wOrD = JOptionPane.showConfirmDialog(null, "Select Transaction Type", "Select Transaction Type", JOptionPane.YES_NO_OPTION);

				//withdraw selected
				if(wOrD == 0) {
					//prompt for ammount and date
					String input = JOptionPane.showInputDialog("Enter ammount to withdraw and date\n<ammount/date(yyyy,mm,dd,hh,mm,ss)>");

					String splitted[] = input.split("/");
					String times[] = splitted[1].split(",");

					//is withdraw possible
					if(gic.withdraw( new Date(Integer.parseInt(times[0])-1900, Integer.parseInt(times[1])-1, Integer.parseInt(times[2]), Integer.parseInt(times[3]), Integer.parseInt(times[4]), Integer.parseInt(times[5]))  , Long.parseLong(splitted[0]))) {
						JOptionPane.showMessageDialog(null, "Transaction Successful\n" + gic.toStringGIC());
					}
					else {
						JOptionPane.showMessageDialog(null, "Insufficient funds");
					}
				}
				else if(wOrD == 1) {//deposit
					//prompt for ammount
					double ammount = Double.parseDouble(JOptionPane.showInputDialog("Enter ammount to deposit: "));
					//deposit
					gic.deposit(ammount);
					//message
					JOptionPane.showMessageDialog(null, "Transaction Successful\n" + gic.toStringGIC());
				}
			}
			else {
				break;
			}
			//custom button text
			UIManager.put("OptionPane.yesButtonText", "Savings");
			UIManager.put("OptionPane.noButtonText", "GIC");

			//prompt account choice again
			savOrGIC = JOptionPane.showConfirmDialog(null, "Select Account", "Select Account", JOptionPane.YES_NO_OPTION);

		}//while loop
		customerDataSave();
		JOptionPane.showMessageDialog(null, "Data saved to a file\nLogging out now.");


	}

	/**
	 * Method to read previous accounts from file 
	 * @throws IOException
	 */
	public void readPreviousAccountDetails() throws IOException {

		// setup reader for reading
		BufferedReader reader = new BufferedReader(new FileReader("SampleAccounts.txt"));

		//loop to keep reading until stop reached
		while(!reader.readLine().equalsIgnoreCase("STOP")) {
			accountNumbers.add(Long.parseLong(reader.readLine()));
			passwords.add(reader.readLine());
		}
		//close reader
		reader.close();
	}

	/**
	 * Method to write previous accounts to a file 
	 * @throws IOException
	 */
	public void writeAccountDetails() throws IOException {

		// setup writer
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("SampleAccounts.txt")));

		//writer each corresponding password and account number
		for(int i = 0; i < accountNumbers.size(); i++) {
			writer.write(Long.toString(accountNumbers.get(i)));
			writer.write(passwords.get(i));
		}

		//write last line STOP
		writer.write("STOP");
		writer.close();
	}

	/**
	 * Method to save customer's data into a file 
	 * @throws IOException
	 */
	public void customerDataSave() throws IOException {

		// create new file
		File file = new File(customer.getFirstName()+customer.getLastName());

		// setup writer
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

		//write data from customer class
		writer.write(customer.getFirstName() + "\n");
		writer.write(customer.getLastName() + "\n");
		writer.write(Long.toString(customer.getPhoneNumber()) + "\n");
		writer.write(customer.getAddress() + "\n");

		//write data from savings class
		writer.write(Long.toString(savings.getAccountNumber()) + "\n");
		writer.write(Double.toString(savings.getBalance()) + "\n");
		writer.write(Double.toString(savings.getTransactionFees()) + "\n");
		writer.write(Double.toString(savings.getMinBalance()) + "\n");

		//setup date formatter
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//write data from gic class
		writer.write(Long.toString(gic.getAccountNumber()) + "\n");
		writer.write(Double.toString(gic.getBalance()) + "\n");
		writer.write(Double.toString(gic.getPenalty()) + "\n");
		writer.write(dateFormat.format(gic.getInvDate()) + "\n");
		writer.write(dateFormat.format(gic.getMaturityDate()) + "\n");

		writer.close();
	}

	/**
	 * Method to check previous account details
	 * @param accNum
	 * @param pwd
	 * @return
	 */
	public int checkAccountDetails(long accNum, String pwd) {
		//look for account number
		for(int i = 0; i < accountNumbers.size(); i++) {
			//check each
			if(accNum == accountNumbers.get(i)) {
				//compare passwords
				if(passwords.get(i).equals(pwd)) {
					return i;
				}
			}
		}
		//when not found
		return -1;	//i did not put this up after the innermost if statement because what if two account numbers are same (a very very rare case but still)
	}

	/**
	 * Main method
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		UserInterface window = new UserInterface();

	}

}

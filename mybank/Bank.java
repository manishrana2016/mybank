package mybank;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

class Bank{
    int minimumBalance=1000;
    double zero=0.0;
    void start() throws SQLException, ClassNotFoundException, InterruptedException{
	  	//clearConsole();
      	welcome();
      	Scanner scanner = new Scanner(System.in);
            try{
                while(true){
                    System.out.println("");
                    administrativePanel();
                    System.out.print("Enter your choice: "); int choice = scanner.nextInt();scanner.nextLine();
                    switch(choice){
                        case 1:
                                String name="";
                                nameLoop:
	                                while(true){
	                                	try {
	                                		System.out.print("Name: ");String nameIn = scanner.nextLine();
	                                		if(nameIn.length() > 0) {
			                                  	if(nameValid(nameIn)){
			                                  		name = nameIn;
			                                  		break nameLoop;
			                                  	}else{
			                                  		System.out.println("Name can include Alphabets and dot only. Please try again.");
			                                        continue nameLoop;
			                                  	}
	                                		}else {
	                                			System.out.println("Name can include Alphabets and dot only. Please try again.");
	                                			continue nameLoop;
	                                		}
	                                	}catch(InputMismatchException ime){
	                                		System.out.println("Name can include Alphabets and dot only. Please try again.");
	                                		continue nameLoop;
	                                	}
	                                }
                                int age=0;
                                ageLoop:
                                	while(true) {
	                                	try {
	                                		System.out.print("Age: "); String ageIn = scanner.nextLine();
	                                		if(!ageIn.isEmpty()) {
		                                		if(ageValid(ageIn)) {
		                                			age = Integer.parseInt(ageIn);
		                                			break ageLoop;
		                                		}else {
		                                			System.out.println("Age must be a number. Please try again.");
			                                        continue ageLoop;
		                                		}
	                                		}else {
	                                			System.out.println("Age must be a number. Please try again.");
	                                			continue ageLoop;
	                                		}
	                                	}catch(InputMismatchException ime) {
	                                		System.out.println("Age must be a number. Please try again.");
	                                        continue ageLoop;
	                                	}
                                	}
                                String email="";
                                emailLoop:
                                	while(true) {
	                                	try {
	                                		System.out.print("Email: "); String emailIn = scanner.nextLine();
	                                		if(!emailIn.isEmpty()) {
		                                		if(emailValid(emailIn)) {
		                                			email = emailIn;
		                                			break emailLoop;
		                                		}else {
		                                			System.out.println("Email invalid. Please try again.");
			                                        continue emailLoop;
		                                		}
	                                		}else {
	                                			System.out.println("Email invalid. Please try again.");
	                                			continue emailLoop;
	                                		}
	                                	}catch(InputMismatchException ime) {
	                                		System.out.println("Email invalid. Please try again.");
	                                        continue emailLoop;
	                                	}
                                	}
                                String address="";
                                addressLoop:
                                	while(true) {
	                                	try {
	                                		System.out.print("Address: "); String addressIn = scanner.nextLine();
	                                		if(!addressIn.isEmpty()) {
		                                		if(addressValid(addressIn)) {
		                                			address = addressIn;
		                                			break addressLoop;
		                                		}else {
		                                			System.out.println("Address invalid. Please try again.");
			                                        continue addressLoop;
		                                		}
	                                		}else {
	                                			System.out.println("Address invalid. Please try again.");
	                                			continue addressLoop;
	                                		}
	                                	}catch(InputMismatchException ime) {
	                                		System.out.println("Address invalid. Please try again.");
	                                        continue addressLoop;
	                                	}
                                	}
                                String dob="";
                                dobLoop:
                                	while(true) {
	                                	try {
	                                		System.out.print("DOB [DD-MM-YYYY]: "); String dobIn = scanner.nextLine();
	                                		if(!dobIn.isEmpty()) {
		                                		if(dobValid(dobIn)) {
		                                			dob = dobIn;
		                                			break dobLoop;
		                                		}else {
		                                			System.out.println("DOB must be in DD-MM-YYYY format. Please try again.");
			                                        continue dobLoop;
		                                		}
	                                		}else {
	                                			System.out.println("DOB must be in DD-MM-YYYY format. Please try again.");
	                                			continue dobLoop;
	                                		}
	                                	}catch(InputMismatchException ime) {
	                                		System.out.println("DOB must be in DD-MM-YYYY format. Please try again.");
	                                        continue dobLoop;
	                                	}
                                	}
                                String mobileNumber="";
                                mobileNumberLoop:
                                	while(true) {
                                		try {
                                			System.out.print("Mobile# : "); String mobileNumberIn = scanner.nextLine();
	                                		if(!mobileNumberIn.isEmpty()) {
		                                		if(mobileNumberValid(mobileNumberIn)) {
		                                			mobileNumber = mobileNumberIn;
		                                			break mobileNumberLoop;
		                                		}else {
		                                			System.out.println("Mobile number must be 10 digit number. Please try again.");
			                                        continue mobileNumberLoop;
		                                		}
	                                		}else {
	                                			System.out.println("Mobile number must be 10 digit number. Please try again.");
	                                			continue mobileNumberLoop;
	                                		}
	                                	}catch(InputMismatchException ime) {
	                                		System.out.println("Mobile number must be 10 digit number. Please try again.");
	                                        continue mobileNumberLoop;
	                                	}
                                	}
                                
                                UserAccount newUserAccount = new UserAccount(name, age, email, address, dob, mobileNumber);
								System.out.println("\nINITIATING ACCOUNT CREATION\n");
                                int newAccountNumber = newUserAccount.createAccount();
                                System.out.println("\nCONGRATULATIONS, NEW ACCOUNT CREATED WITH ACCOUNT# "+newAccountNumber+"\n");
                                System.out.println("");
                            	break;
                        case 2:
                                System.out.print("Account #: "); int accountNumber = scanner.nextInt();
                                if(isAccountPresent(accountNumber)){
                                	UserAccount userAccount = new UserAccount();
                                	userAccount.closeAccount(accountNumber);
                                	System.out.println("");
                            		break;
                                }else{
									System.out.println("INVALID ACCOUNT NUMBER - "+accountNumber+" !!");
									break;
                                }
                        case 3:
                                System.out.print("Account #: "); accountNumber = scanner.nextInt();
								if(isAccountPresent(accountNumber)){
						                        System.out.print("Deposit Amount: "); double depositAmount = scanner.nextDouble(); scanner.nextLine();
									System.out.print("Description: "); String description = scanner.nextLine();
				
									boolean depositSuccess = deposit(depositAmount, accountNumber, description);
						                        if(depositSuccess){
									    double newBalance = getAccountBalance(accountNumber);
						                            System.out.println("DEPOSIT SUCCESS: INR "+depositAmount+" DEPOSITED IN ACCOUNT "+accountNumber+". UPDATED BALANCE IS INR "+newBalance);
									}
						                        else
						                            System.out.println("DEPOSIT UNSUCCESSFUL");
						                        System.out.println("");
						                    	break;
								}else{
									System.out.println("INVALID ACCOUNT# - "+accountNumber+" !!");
									break;
								}
                        case 4:
                                System.out.print("Account #: "); accountNumber = scanner.nextInt();
                                if(isAccountPresent(accountNumber)){
			                        System.out.print("Withdraw Amount: "); double withdrawAmount = scanner.nextDouble(); scanner.nextLine();
			                        System.out.print("Description: "); String description = scanner.nextLine();
			                       	boolean withdrawSuccess = withdraw(withdrawAmount, accountNumber, description);
			                        if(withdrawSuccess){
			                        	double newBalance = getAccountBalance(accountNumber);
			                            System.out.println("WITHDRAWAL SUCCESS: INR "+withdrawAmount+" WITHDRAWN FROM ACCOUNT "+accountNumber+". UPDATED BALANCE IS INR "+newBalance);
			                        }
			                        else
			                            System.out.println("WITHDRAWAL ERROR");
			                        System.out.println("");
			                        break;
								}else{
									System.out.println("INVALID ACCOUNT# - "+accountNumber+" !!");
									break;
								}
                        case 5:
                                System.out.print("Account #: "); accountNumber = scanner.nextInt();
                                if(isAccountPresent(accountNumber)){
                                	System.out.println("");
                                	printBalance(accountNumber);
                                	System.out.println("");
                                	break;
                                }
                                else{
                                	System.out.println("INVALID ACCOUNT# - "+accountNumber+" !!");
                                	break;
                                }
						case 6:
							System.out.print("Account #: "); accountNumber = scanner.nextInt();
							if(isAccountPresent(accountNumber)){
								System.out.println("");
								printAccountInfo(accountNumber);
								System.out.println("");
								break;
							}else{
								System.out.println("INVALID ACCOUNT# - "+accountNumber+" !!");
								break;
							}
						case 7:
							System.out.println("\nPRINTING ALL ACCOUNT INFO");
							printAllAccountInfo();
							break;
						case 8: 
							System.exit(0);
						default: 
							System.out.println("INVALID CHOICE. TRY AGAIN!");
							System.out.print("");
							break;
					}
		    	//Thread.sleep(10000);
		    	//clearConsole();
                }
            }finally{
                if(scanner!=null)
                    scanner.close();
            }
    }
    void welcome(){
        System.out.println("WELCOME TO My BANK");
        System.out.println("---------------------");
    }
    void administrativePanel(){
        System.out.println("Admininstrative Panel");
        System.out.println("----------------------");
        System.out.println("");
        System.out.println("1. Create New Account");
        System.out.println("2. Close Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Show Balance");
	System.out.println("6. Print Account Info");
	System.out.println("7. Print All Accounts");
        System.out.println("8. Exit");
        System.out.println("");
    }
    public boolean deposit(double depositAmount, int accountNumber, String description) throws SQLException, ClassNotFoundException{
        double currentBalance=getAccountBalance(accountNumber);
        double updatedBalance=0;
	int updateStatus1=-1;
	int updateStatus2=-1;
        boolean depositSuccess=false;
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
	String transactionDate = formatter.format(date);
        /*Add the deposit amount to amount available */
        updatedBalance += currentBalance+depositAmount;
	String accountStatus = getAccountStatus(accountNumber);
	if(accountStatus.equals("ACTIVE")){
        	DBConnection dbConnect = new DBConnection();
        	Connection connection = dbConnect.dbConnect();
		String sqlDepositAmount = "INSERT INTO Amount(accountnumber,balance,transactiondescription,credit,debit,transactiondate) VALUES(?,?,?,?,?,?)";
        	String sqlUpdateBalance = "UPDATE AllUserAccounts SET balance = ? WHERE accountnumber=?";
		PreparedStatement psDepositAmount = connection.prepareStatement(sqlDepositAmount);
        	psDepositAmount.setInt(1,accountNumber);
        	psDepositAmount.setDouble(2,updatedBalance);
       		psDepositAmount.setString(3,description);
		psDepositAmount.setDouble(4,depositAmount);
		psDepositAmount.setDouble(5,zero);
		psDepositAmount.setString(6,transactionDate);
        	updateStatus1 = psDepositAmount.executeUpdate();

		PreparedStatement psUpdate = connection.prepareStatement(sqlUpdateBalance);
        	psUpdate.setDouble(1,updatedBalance);
        	psUpdate.setInt(2,accountNumber);
		updateStatus2 = psUpdate.executeUpdate();

		if(updateStatus1 > 0 && updateStatus2 > 0)
            		depositSuccess=true;
        	connection.close();
	}else{
		System.out.println("DEPOSIT ERROR: "+accountStatus+" Account");
	}
        return depositSuccess;
    }
    public boolean withdraw(double withdrawAmount, int accountNumber, String description) throws SQLException, ClassNotFoundException{
        double currentBalance=getAccountBalance(accountNumber);
        double updatedBalance=0;
	int updateStatus1=-1;
	int updateStatus2=-1;
        boolean withdrawSuccess=false;
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
	String transactionDate = formatter.format(date);
	double accountBalance=getAccountBalance(accountNumber);
	updatedBalance = accountBalance - withdrawAmount;
	if(updatedBalance >= minimumBalance){
		String accountStatus = getAccountStatus(accountNumber);
		if(accountStatus.equals("ACTIVE")){
			DBConnection dbConnect = new DBConnection();
			Connection connection = dbConnect.dbConnect();
                	String sqlWithdrawAmount = "INSERT INTO Amount(accountnumber,balance,transactiondescription,credit,debit,transactiondate) VALUES(?,?,?,?,?,?)";
			String sqlUpdateBalance = "UPDATE AllUserAccounts SET balance = ? WHERE accountNumber = ?";
                	PreparedStatement psWithdrawAmount = connection.prepareStatement(sqlWithdrawAmount);
			psWithdrawAmount.setInt(1, accountNumber);
                	psWithdrawAmount.setDouble(2, updatedBalance);
                	psWithdrawAmount.setString(3, description);
                	psWithdrawAmount.setDouble(4, zero);
                	psWithdrawAmount.setDouble(5, withdrawAmount);
                	psWithdrawAmount.setString(6, transactionDate);
	                updateStatus1 = psWithdrawAmount.executeUpdate();

			PreparedStatement psUpdateBalance = connection.prepareStatement(sqlUpdateBalance);
			psUpdateBalance.setDouble(1,updatedBalance);
			psUpdateBalance.setInt(2,accountNumber);
			updateStatus2 = psUpdateBalance.executeUpdate();

        		if(updateStatus1 > 0 && updateStatus2 > 0)
            			withdrawSuccess=true;
        		connection.close();
        	}else{
            		System.out.println("WITHDRAW ERROR: "+accountStatus+" Account");
        	}
	}else{
		System.out.println("WITHDRAW ERROR: Minimum Balance must be maintained to INR "+minimumBalance);
	}
        return withdrawSuccess;
    }
    public double getAccountBalance(int accountNumber) throws SQLException, ClassNotFoundException{
        double currentBalance=0;
        DBConnection dbConnect = new DBConnection();
        Connection connection = dbConnect.dbConnect();
        String sqlAmountAvailable = "SELECT balance FROM AllUserAccounts WHERE accountnumber=?";
        PreparedStatement psAmountAvailable = connection.prepareStatement(sqlAmountAvailable);
        psAmountAvailable.setInt(1, accountNumber);
        ResultSet rsAmountAvailable = psAmountAvailable.executeQuery();
        rsAmountAvailable.next();
        currentBalance = rsAmountAvailable.getDouble("balance");
        connection.close();
        return currentBalance;
    }
    public String getAccountStatus(int accountNumber) throws SQLException, ClassNotFoundException{
        String accountStatus="";
        DBConnection dbConnect = new DBConnection();
        Connection connection = dbConnect.dbConnect();
        String sqlAccountStatus = "SELECT dormant FROM AllUserAccounts WHERE accountnumber=?";
        PreparedStatement psAccountStatus = connection.prepareStatement(sqlAccountStatus);
        psAccountStatus.setInt(1, accountNumber);
        ResultSet rsAccountStatus = psAccountStatus.executeQuery();
        rsAccountStatus.next();
        accountStatus = rsAccountStatus.getString("dormant");
        connection.close();
        return accountStatus;
    }
    public void printBalance(int accountNumber) throws SQLException, ClassNotFoundException{
        double currentBalance = getAccountBalance(accountNumber);
        System.out.println("CURRENT BALANCE: "+currentBalance);
    }
    public boolean isAccountPresent(int accountNumber) throws SQLException, ClassNotFoundException{
    	DBConnection dbConnect = new DBConnection();
	Connection connection = dbConnect.dbConnect();
	int accountNumberDB = 0;
	String sqlCheckAccountPresent="Select accountnumber from AllUserAccounts WHERE accountnumber=?";
	PreparedStatement psCheckAccountPresent = connection.prepareStatement(sqlCheckAccountPresent);
	psCheckAccountPresent.setInt(1,accountNumber);
	ResultSet rsCheckAccountPresent = psCheckAccountPresent.executeQuery();
	while(rsCheckAccountPresent.next()){
		accountNumberDB = rsCheckAccountPresent.getInt("accountnumber");
	}
	connection.close();
	if(accountNumberDB>0)
		return true;
	else return false;

    }
    public void clearConsole(){
    	try{
			String os = System.getProperty("os.name");
			if(os.contains("Windows")){
				Runtime.getRuntime().exec("cls");
			}
			else{
				Runtime.getRuntime().exec("clear");
			}
		}catch(Exception e){
			System.out.println("EXCEPTION OCCURRED "+e);
		}
	    /*System.out.print("\033[H\033[2J");
		System.out.flush();*/
    }

	public boolean nameValid(String nameString){
		Pattern p = Pattern.compile("[a-zA-Z\\.\\s]+");
 		Matcher m = p.matcher(nameString);
		boolean b = m.matches();
		return b;
	}

	public boolean ageValid(String age){
		Pattern p = Pattern.compile("^\\d{2,3}$");
 		Matcher m = p.matcher(age);
		boolean b = m.matches();
		//System.out.println("Age match "+b);
		return b;
	}

	public boolean emailValid(String emailString){
		Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
 		Matcher m = p.matcher(emailString);
		boolean b = m.matches();
		return b;
	}

	public boolean mobileNumberValid(String mobileNumber){
		Pattern p = Pattern.compile("^\\d{10}$");
 		Matcher m = p.matcher(mobileNumber);
		boolean b = m.matches();
		return b;
	}
	
	public boolean dobValid(String dob){
		Pattern p = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])\\-(1[0-2]|0[1-9])\\-[0-9]{4}$");
 		Matcher m = p.matcher(dob);
		boolean b = m.matches();
		return b;
	}
	
	public boolean addressValid(String address) {
		Pattern p = Pattern.compile("[a-zA-Z0-9\\-\\,\\s\\/\\.]+");
 		Matcher m = p.matcher(address);
		boolean b = m.matches();
		return b;
	}

	public void printAccountInfo(int accountNumber) throws SQLException, ClassNotFoundException{
		DBConnection dbConnect = new DBConnection();
		Connection connection = dbConnect.dbConnect();
		String sqlPrintAccountInfo = "SELECT accountnumber, customerid, name, age, email, mobilenumber, balance FROM AllUserAccounts WHERE accountnumber=?";
		PreparedStatement psPrintAccountInfo = connection.prepareStatement(sqlPrintAccountInfo);
		psPrintAccountInfo.setInt(1, accountNumber);
		ResultSet rsPrintAccountInfo = psPrintAccountInfo.executeQuery();
		String customerId="";
		String name="";
		int age=0;
		String email="";
		String mobileNumber="";
		double balance=0.0;
		while(rsPrintAccountInfo.next()){
			/*
			System.out.println("Account#: "+rsPrintAccountInfo.getInt(1));
			System.out.println("Customer ID: "+rsPrintAccountInfo.getString(2));
			System.out.println("Name: "+rsPrintAccountInfo.getString(3));
			System.out.println("Age: "+rsPrintAccountInfo.getInt(4));
			System.out.println("Email: "+rsPrintAccountInfo.getString(5));
			System.out.println("Mobile#: "+rsPrintAccountInfo.getString(6));
			System.out.println("Balance: "+rsPrintAccountInfo.getDouble(7));
			*/
			accountNumber=rsPrintAccountInfo.getInt(1);
			customerId=rsPrintAccountInfo.getString(2);
			name=rsPrintAccountInfo.getString(3);
			age=rsPrintAccountInfo.getInt(4);
			email=rsPrintAccountInfo.getString(5);
			mobileNumber=rsPrintAccountInfo.getString(6);
			balance=rsPrintAccountInfo.getDouble(7);
			//System.out.format("%20d%20s%20d%20s%20s%20f",accountNumber,name,age,email,mobileNumber,balance);
			
		}
		//String accountHeader = "Account#", nameHeader="Name", ageHeader="Age", emailHeader="Email", mobileHeader="Mobile#", balanceHeader="Balance";
		//System.out.format("%5s%5s%5s%5s%5s%5s",accountHeader,nameHeader,ageHeader,emailHeader,mobileHeader,balanceHeader);
		//System.out.format("%20d%20s%20d%20s%20s%20f",accountNumber,name,age,email,mobileNumber,balance);
		System.out.println("Account#: "+accountNumber);
		System.out.println("Customer ID: "+customerId);
		System.out.println("Name: "+name);
		System.out.println("Age: "+age);
		System.out.println("Email: "+email);
		System.out.println("Mobile#: "+mobileNumber);
		System.out.println("Balance: "+balance);
		connection.close();
	}

	public void printAllAccountInfo() throws SQLException, ClassNotFoundException{
		DBConnection dbConnect = new DBConnection();
		Connection connection = dbConnect.dbConnect();
		String sqlAllAccountNumbers = "SELECT accountnumber FROM AllUserAccounts";
		PreparedStatement psAllAccountNumbers = connection.prepareStatement(sqlAllAccountNumbers);
		ResultSet rsAllAccountNumbers = psAllAccountNumbers.executeQuery();
		List<Integer> arrAllAccountNumbers = new ArrayList<Integer>();
		while(rsAllAccountNumbers.next()){
			arrAllAccountNumbers.add(rsAllAccountNumbers.getInt(1));
		}
		connection.close();
		System.out.println("\nTotal Number of Accounts: "+arrAllAccountNumbers.size()+"\n");
		for (Integer accountNumber : arrAllAccountNumbers) {
			System.out.println("--------------------------\n");
			printAccountInfo(accountNumber);
			System.out.println();
			System.out.println("--------------------------\n");
		}
	}
}

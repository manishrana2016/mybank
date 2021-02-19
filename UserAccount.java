package mybank;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;  

class UserAccount{
    private String name, email, address, mobileNumber;
    private String dob;
    private int age;
    private double zero = 0.0;
    UserAccount(String name, int age, String email, String address, String dob, String mobileNumber){
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
    }
    UserAccount(){

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    public String getDOB(){
        return dob;
    }
    public void setDOB(String dob){
        this.dob = dob;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public int createAccount() throws SQLException, ClassNotFoundException{
        int newAccountNumber=0;
        Date date = new Date();
        String dormant = "ACTIVE";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHMMSS");
        String customerId = formatter.format(date)+getMobileNumber();
        DBConnection dbConnect = new DBConnection();
        Connection connection = dbConnect.dbConnect();
        String sqlCreateAccount = "INSERT INTO AllUserAccounts(name,address,age,dob,email,customerid,mobilenumber,dormant,balance) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement psCreateAccount = connection.prepareStatement(sqlCreateAccount);
        psCreateAccount.setString(1,getName());
        psCreateAccount.setString(2,getAddress());
        psCreateAccount.setInt(3,getAge());
        psCreateAccount.setString(4,getDOB());
        psCreateAccount.setString(5,getEmail());
        psCreateAccount.setString(6,customerId);
        psCreateAccount.setString(7,getMobileNumber());
        psCreateAccount.setString(8, dormant);
        psCreateAccount.setDouble(9, zero);
	
        int accountCreatedSuccess = psCreateAccount.executeUpdate();
        if(accountCreatedSuccess>0){
            String sqlAccountNumber = "SELECT accountnumber FROM AllUserAccounts WHERE customerid=?";
            PreparedStatement psAccountNumber = connection.prepareStatement(sqlAccountNumber);
            psAccountNumber.setString(1,customerId);
            ResultSet rsAccountNumber = psAccountNumber.executeQuery();
            while(rsAccountNumber.next()){
                newAccountNumber = rsAccountNumber.getInt("accountnumber");
            }
        }
        connection.close();
        return newAccountNumber;
    }

    public void closeAccount(int accountNumber) throws SQLException, ClassNotFoundException{
	boolean accountClosed = false;
	DBConnection dbConnect = new DBConnection();
	Connection connection = dbConnect.dbConnect();
	String sqlCloseAccount = "UPDATE AllUserAccounts SET dormant = ? WHERE accountnumber = ?";
	PreparedStatement psCloseAccount = connection.prepareStatement(sqlCloseAccount);
	psCloseAccount.setString(1, "DORMANT");
	psCloseAccount.setInt(2, accountNumber);
	int accountCloseStatus = psCloseAccount.executeUpdate();
	if(accountCloseStatus>0)
		accountClosed = true;
	if(accountClosed)
		System.out.println("Account Marked DORMANT. No more transactions allowed. Permanent closure will happen after 1 week.");
	else
		System.out.println("ERROR: Account Could not be closed");
    }
}

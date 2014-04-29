package creditcard;

public class CustomerAccount {
	/** Unique account number */
	private int accountNo;
	
	/** Customer name, not null */
	private String custName;
	
	/** Customer address */
	private String custAddress;
	
	/** Unpaid balance, could be negative */
	private double unpaidBalance;
	
	/** Credit limit; purchases that would make the unpaid balance exceed this limit are rejected */
	private double creditLimit;
	
	/** URL for customer image, either relative or on the web */
	private String imageUrl;
	
	/** default initial credit limit for new account */
	private static final double DEFAULT_CREDIT_LIMIT = 1000.0;
	/**
	 * Creates a new account with zero balance and no image
	 * @param accountNo accountNo for new account
	 * @param custName new account customer name
	 * @param custAddress new account customer address
	 * @param creditLimit initial credit limit
	 */
	public CustomerAccount(int accountNo, String custName, String custAddress, double creditLimit) {
		this.accountNo = accountNo;
		this.custName = custName;
		this.custAddress = custAddress;
		this.creditLimit = creditLimit;
		this.unpaidBalance = 0.0;
		this.imageUrl = "generic.png";
	}
	
	/**
	 * Creates a new account with zero balance and default credit limit
	 * @param accountNo accountNo for new account
	 * @param custName new account customer name
	 * @param custAddress new account customer address
	 */
	public CustomerAccount(int accountNo, String custName, String custAddress) {
		this(accountNo, custName, custAddress, DEFAULT_CREDIT_LIMIT);
	}
	
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the custAddress
	 */
	public String getCustAddress() {
		return custAddress;
	}

	/**
	 * @param custAddress the custAddress to set
	 */
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	/**
	 * @return the creditLimit
	 */
	public double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the accountNo
	 */
	public int getAccountNo() {
		return accountNo;
	}

	/**
	 * @return the unpaidBalance
	 */
	public double getUnpaidBalance() {
		return unpaidBalance;
	}
	
	//TODO add transactions
	//TODO refactor unpaidBalance to BigDecimal
}

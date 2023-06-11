public class BankAccountFunctional {
	private final int accountNumber;
	private final double balance;
	private final double interestRate;

	public BankAccountFunctional(int account, double startingBalance, double rate) {
		accountNumber = account;
		balance = startingBalance;
		interestRate = rate;
	}
	
	// No "set" methods - new objects created with new data - like String.substring()
	public BankAccountFunctional debit(double amount) {
		return new BankAccountFunctional(accountNumber,
			balance - amount,
			interestRate);
	}
	// Similarly for other methods
}

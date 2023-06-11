public record BankAccount(int accountNumber, double balance, double interestRate) {

	// Compiler generates fields, constructor, accessors,
	// a few required methods!

	// You can write any methods you need, such as: 

	public BankAccount debit(double amount) {
		return new BankAccount(accountNumber,
			balance - amount,
			interestRate);
	}
}

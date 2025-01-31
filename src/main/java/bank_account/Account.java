package bank_account;

import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService{
	
	private final List<Transaction> transactions = new ArrayList<>();
    private int MoneyAmount;
    
    public Account(int amount) {
		super();
		MoneyAmount = amount;
	}

	public int getAmount() {
		return MoneyAmount;
	}

	public void setAmount(int amount) {
		MoneyAmount = amount;
	}
    
	//Deposits a specified amount into the account.
	@Override
	public void deposit(int amount) {
		 //throw new UnsupportedOperationException("Not implemented yet");
		 if (amount <= 0) {
	            throw new IllegalArgumentException("Amount must be positive");
	        }
		 
		 MoneyAmount+= amount;
	        transactions.add(new Transaction(amount, MoneyAmount, TransactionType.DEPOSIT));
	}
	
	// Withdraws a specified amount from the account.
	@Override
	public void withdraw(int amount) {
		 //throw new UnsupportedOperationException("Not implemented yet");
		if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (MoneyAmount < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        MoneyAmount -= amount;
        transactions.add(new Transaction(-amount, MoneyAmount, TransactionType.WITHDRAWAL ));
	}
	
	//Prints the transaction history of the account in a formatted statement
	@Override
	public void printStatement() {
		 //throw new UnsupportedOperationException("Not implemented yet");
		System.out.println("Date || Amount || Balance");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i).format());
        }
	}

	
    
}

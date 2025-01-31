package bank_account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
	private  LocalDate date;
	private  int amount;
    private  int balance;
    private  TransactionType type;
    
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Transaction(int amount,int balance,TransactionType type) {
	        this.date = LocalDate.now();
	        this.amount = amount;
	        this.balance=balance;
	        this.type = type;
	    }
	
	public String format() {
	        return String.format("%s || %d || %d",
	            date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
	            amount,
	            balance);
	    }
		
		
}

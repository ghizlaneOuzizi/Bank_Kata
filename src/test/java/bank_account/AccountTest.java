package bank_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class AccountTest {
	
	
	private static final String ERROR_MONEY_AMOUNT_NEGATIF = "Amount must be positive";
	private static final String ERROR_INSUFFICIENT_FUNDS = "Insufficient funds";

	private static final int DEFAULT_MONEY_AMOUNT = 1000;
	private static final int ZERO_AMOUNT = 0;
	private static final int HIGHER_AMOUNT = 3000;
	private static final int NEGATIVE_AMOUNT = -500;
	
	
    private Account account;
    
    @BeforeEach
    void setUp() {
        account = new Account(ZERO_AMOUNT);
    }
    
    //Test to ensure that a new account starts with an initial balance of zero.
    @Test
    void shouldStartWithInitialBalance() {
        assertEquals(ZERO_AMOUNT, account.getAmount());
    }
    
    //Test to verify that the balance updates correctly after a deposit.
    @Test
    void shouldUpdateBalanceAfterDeposit() {
        account.deposit(DEFAULT_MONEY_AMOUNT);
        assertEquals(DEFAULT_MONEY_AMOUNT, account.getAmount());
    }
    
    //Test to check that depositing a negative amount throws an IllegalArgumentException.
    @Test
    void shouldThrowExceptionWhenDepositingNegativeAmount() {
    	IllegalArgumentException exception =assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(NEGATIVE_AMOUNT);
        });
    	assertEquals(ERROR_MONEY_AMOUNT_NEGATIF, exception.getMessage());
    }
    
    //Test to ensure that a valid withdrawal correctly updates the balance.
    @Test
    void shouldAllowValidWithdrawal() {
    	account.deposit(DEFAULT_MONEY_AMOUNT);
        account.withdraw(500);
        assertEquals(500, account.getAmount());
    }

    //Test to check that withdrawing a negative amount throws an IllegalArgumentException.
    @Test
    void shouldThrowExceptionWhenWithdrawingNegativeAmount() {
    	IllegalArgumentException exception =assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(NEGATIVE_AMOUNT);
        });
    	assertEquals(ERROR_MONEY_AMOUNT_NEGATIF, exception.getMessage());
    }

    //Test to ensure that withdrawing more than the available balance throws an IllegalStateException.
    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {
    	IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            account.withdraw(HIGHER_AMOUNT);
        });
    	assertEquals(ERROR_INSUFFICIENT_FUNDS, exception.getMessage());
    }
 
    //Test to check that the bank statement is printed in reverse chronological order.
    @Test
    void shouldPrintInReverseChronologicalOrder() {
    
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
          
        account.deposit(DEFAULT_MONEY_AMOUNT); 
        account.deposit(DEFAULT_MONEY_AMOUNT*2);
        account.withdraw(500);  
        
        // When
        account.printStatement();
        
        // Then
        String output = outputStream.toString();
        String[] lines = output.split(System.lineSeparator());
        
        assertEquals("Date || Amount || Balance", lines[0]);
        assertTrue(lines[1].contains("-500"));  // Most recent transaction first
        assertTrue(lines[2].contains("2000"));  // Older transaction last
        assertTrue(lines[3].contains("1000"));
        System.setOut(System.out);
    }



}

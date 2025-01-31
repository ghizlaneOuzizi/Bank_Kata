package bank_account;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TransactionTest {
	@Mock
    private Clock clock;
	
	@Test
    void shouldFormatTransaction() {
        // Given
        Clock.fixed(
            LocalDate.of(2025, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant(),
            ZoneId.systemDefault()
        );
        Transaction transaction = new Transaction(1000, 1000, TransactionType.DEPOSIT);
        
        // When
        String formatted = transaction.format();
        
        // Then
        assertEquals("31-01-2025 || 1000 || 1000", formatted);
    }
}

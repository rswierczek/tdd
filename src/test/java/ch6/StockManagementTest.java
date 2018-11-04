package ch6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockManagementTest {

    @Test
    public void canGetACorrectLocatorCode() {
        String isbn = "0140177396";
        StockManager stockManager = new StockManager(new ExternalISBNDataService() {
            @Override
            public Book getByIsbn(String isbn) {
                return new Book("0140177396", "Of Mice and Man", "J. Stainbeck");
            }
        });


        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }
}

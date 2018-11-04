package ch7;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    private ExternalISBNDataService mockDataService;
    private ExternalISBNDataService mockWebService;
    private StockManager stockManager;

    @Before
    public void setUp() throws Exception {
        mockDataService = mock(ExternalISBNDataService.class);
        mockWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager(mockDataService, mockWebService);
    }

    @Test
    public void canGetACorrectLocatorCode() {
        when(mockDataService.getByIsbn(anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));
        when(mockDataService.getByIsbn(anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(mockDataService.getByIsbn(anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));
        when(mockDataService.getByIsbn(anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));

        String isbn = "0140177396";
        stockManager.getLocatorCode(isbn);

        verify(mockDataService).getByIsbn(anyString());
        verify(mockWebService, never()).getByIsbn(anyString());

    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        when(mockDataService.getByIsbn(anyString())).thenReturn(null);
        when(mockWebService.getByIsbn(anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));

        String isbn = "0140177396";
        stockManager.getLocatorCode(isbn);

        verify(mockDataService, times(1)).getByIsbn(anyString());
        verify(mockWebService, times(1)).getByIsbn(anyString());
    }
}

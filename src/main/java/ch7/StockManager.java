package ch7;

public class StockManager {
    private ExternalISBNDataService databaseService;
    private ExternalISBNDataService webService;

    public StockManager(ExternalISBNDataService databaseService, ExternalISBNDataService webService) {
        this.databaseService = databaseService;
        this.webService = webService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.getByIsbn(isbn);
        if (book == null) {
           book = webService.getByIsbn(isbn);
        }
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length()-4));
        locator.append(book.getAuthor().charAt(0));
        locator.append(book.getTitle().split("\\s").length);
        return locator.toString();
    }
}

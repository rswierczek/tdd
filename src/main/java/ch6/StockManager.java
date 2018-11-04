package ch6;

public class StockManager {
    private ExternalISBNDataService servie;

    public StockManager(ExternalISBNDataService servie) {
        this.servie = servie;
    }

    public String getLocatorCode(String isbn) {
        Book book = servie.getByIsbn(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length()-4));
        locator.append(book.getAuthor().charAt(0));
        locator.append(book.getTitle().split("\\s").length);
        return locator.toString();
    }
}

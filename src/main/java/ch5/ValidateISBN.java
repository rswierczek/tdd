package ch5;

public class ValidateISBN {

	public static final int LONG_ISBN_LENGTH = 13;
	public static final int SHORT_ISBN_LENGTH = 10;
	public static final int LONG_ISBN_MULTIPIER = 10;
	public static final int SHORT_ISBN_MULTIPIER = 11;

	public boolean checkISBN(String isbn) {
		if (isbn.length() == LONG_ISBN_LENGTH) {
			return IsThisAValidLongISBN(isbn);
		} else if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isThisAValidShortISBN(isbn);
		}
		throw new NumberFormatException("isbn number must be 10 or 13 digits long");
	}

	private boolean isThisAValidShortISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			char character = isbn.charAt(i);
			if (!Character.isDigit(character)) {

				if (i == 9 && character == 'X') {
					total += 10;
				} else {
					throw new NumberFormatException("Only last character can be non digit equal X");
				}
			} else {
				total += Character.getNumericValue(character) * (10 - i);
			}


		}

		return total % SHORT_ISBN_MULTIPIER == 0;
	}

	private boolean IsThisAValidLongISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			char character = isbn.charAt(i);
			if (!Character.isDigit(character)) {
				throw new NumberFormatException("Non numeric digits are not allowed");
			} else {
				int multiplyBy = i%2 == 0 ? 1 : 3;
				total += Character.getNumericValue(character) * multiplyBy;
			}
		}

		return total % LONG_ISBN_MULTIPIER == 0;
	}

}

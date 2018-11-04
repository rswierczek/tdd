package ch4;

public class ValidateISBN {

	public boolean checkISBN(String isbn) {
		if (isbn.length() == 13) {
			int total = 0;

			for (int i = 0; i < 13; i++) {
				char character = isbn.charAt(i);
				if (!Character.isDigit(character)) {
					throw new NumberFormatException("Non numeric digits are not allowed");
				} else {
					int multiplyBy = i%2 == 0 ? 1 : 3;
					total += Character.getNumericValue(character) * multiplyBy;
				}
			}

			if (total % 10 == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (isbn.length() != 10) {
				throw new NumberFormatException("isbn number must be 10 digits long");
			}
			int total = 0;

			for (int i = 0; i < 10; i++) {
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

			if (total % 11 == 0) {
				return true;
			} else {
				return false;
			}
		}

	}

}

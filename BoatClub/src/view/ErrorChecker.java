package view;

public class ErrorChecker {
	
	/**
	 * Checks if String input is an integer
	 * @param input 
	 * @return true if an integer
	 */
	public boolean isInteger (String input) {
		if (input.isEmpty()) {
			System.out.println("Empty string");
			return false;
		}
		else {
			for (int i = 0; i < input.length(); i++) {
				if (!Character.isDigit(input.charAt(i))) {
					System.out.println("Not a digit");
					return false;
				}
			}
			return true;
		}
	}
	/**
	 * Checks if String input is twelve digit number
	 * @param input
	 * @return true if it is 12 digits
	 */
	public boolean isTwelveDigits(String input) {
		return (input.length() == 12);
	}
	
	/**
	 * Checks if String input is a double. Should be used with isInteger() always!
	 * @param input 
	 * @return true if input is a double
	 */
	public boolean isDouble(String input) {
		try {
			//Attempts to 
			Integer.parseInt(input);
		}
		catch(Exception e) {
			return true;
		}
		return false;
	}
	/**
	 * Checks if input is an option input (not double). Should be used with isInteger() always!
	 * @param input
	 * @return true with not double
	 */
	public boolean isOptionInput(String input) {
		return !(isDouble(input));
	}
	
	/**
	 * Checks if input is valid type of a boat. 
	 * @param input
	 * @return true if input is valid type of a boat
	 */
	public boolean isValidType (String input) {
		String type = input.toUpperCase();
		String[] types = {"SAILBOAT", "MOTORSAILER", "CANOE", "OTHER"};
		for (int i = 0; i< types.length; i++) {
			if (type.equals(types[i])) {
				return true;
			}
		}
		return false;
	}
}

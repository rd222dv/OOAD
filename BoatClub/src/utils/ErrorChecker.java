package utils;

public class ErrorChecker {
	
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
	
	public boolean isTwelveDigits(String input) {
		return (input.length() == 12);
	}
	
	public boolean isDouble(String input) {
		try {
			Integer.parseInt(input);
		}
		catch(Exception e) {
			return true;
		}
		return false;
	}
	
	public boolean isOptionInput(String input) {
		return !(isDouble(input));
	}
	
	public boolean isValidType (String input) {
		String type = input.toLowerCase();
		String[] types = {"sailboat", "motorsailer", "canoe", "other"};
		for (int i = 0; i< types.length; i++) {
			if (type.equals(types[i])) {
				return true;
			}
		}
		return false;
	}
}

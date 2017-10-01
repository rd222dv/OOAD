package utils;

import java.util.Scanner;

public class ErrorChecker {
	
	public boolean isInteger (String input) {
		if (input.isEmpty()) {
			return false;
		}
		else {
			for (int i = 0; i < input.length(); i++) {
				if (!Character.isDigit(input.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}
}

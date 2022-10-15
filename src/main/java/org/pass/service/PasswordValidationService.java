package org.pass.service;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidationService {
	
	public boolean validate(String password) {
		boolean hasLowerCaseChar = false;
		boolean hasUpperCaseChar = false;
		boolean hasDigits = false;
		
		if(password==null) {
			throw new RuntimeException("Password is null.");
		} else if(password.length() <=8) {
			throw new RuntimeException("Password's length should be greater than 8.");
		} else {
			for(char c: password.toCharArray()) {
				if(!hasLowerCaseChar) {
					hasLowerCaseChar = isLowerCase(c);
				}
				
				if(!hasUpperCaseChar) {
					hasUpperCaseChar = isUpperCase(c);
				}
				
				if(!hasDigits) {
					hasDigits = isDigit(c);
				}
				
				if (hasLowerCaseChar && hasUpperCaseChar && hasDigits) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean isLowerCase(char c) {
		return (c-'a' >= 0) && ('z'-c >=0);
	}
	
	private static boolean isUpperCase(char c) {
		return (c-'A' >= 0) && ('Z'-c >=0);
	}
	
	private static boolean isDigit(char c) {
		return (c-'0' >= 0) && ('9'-c >=0);
	}
}

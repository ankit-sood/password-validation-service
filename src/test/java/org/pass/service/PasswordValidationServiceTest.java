package org.pass.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PasswordValidationServiceTest {
	private PasswordValidationService passwordValidationService;

	@BeforeEach
	void setUp() {
		this.passwordValidationService = new PasswordValidationService();
	}

	@Test
	void validate_success() {
		boolean isValid = passwordValidationService.validate("abc1ABCoA");
		assertEquals(true, isValid);
	}
	
	@Test
	void validate_failure_empty_password() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> passwordValidationService.validate(null));
		assertEquals("Password is null.", exception.getMessage());
	}
	
	@Test
	void validate_failure_length_less_than_eight() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> passwordValidationService.validate("asdc"));
		assertEquals("Password's length should be greater than 8.", exception.getMessage());
	}
	
	@Test
	void validate_failure_no_lowercase_characters() {
		boolean isValid = passwordValidationService.validate("ABCDEF1AA");
		assertEquals(false, isValid);
	}
	
	@Test
	void validate_failure_no_uppercase_characters() {
		boolean isValid = passwordValidationService.validate("abcdef1aa");
		assertEquals(false, isValid);
	}
	
	@Test
	void validate_failure_no_digits() {
		boolean isValid = passwordValidationService.validate("abcdefaab");
		assertEquals(false, isValid);
	}
}

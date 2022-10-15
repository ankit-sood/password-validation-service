package org.pass.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PasswordValidationServiceV2Test {
	private PasswordValidationServiceV2 passwordValidationServiceV2;

	@BeforeEach
	void setUp() {
		this.passwordValidationServiceV2 = new PasswordValidationServiceV2();
	}

	@Test
	void validate_success() {
		boolean isValid = passwordValidationServiceV2.validate("abc1ABCoA");
		assertEquals(true, isValid);
	}
	
	@Test
	void validate_failure_empty_password() {
		boolean isValid = passwordValidationServiceV2.validate(null);
		assertEquals(false, isValid);
	}
	
	@Test
	void validate_success_length_less_than_eight() {
		boolean isValid = passwordValidationServiceV2.validate("As1c");
		assertEquals(true, isValid);
	}
	
	@Test
	void validate_success_no_lowercase_characters() {
		boolean isValid = passwordValidationServiceV2.validate("ABCDEF1AA");
		assertEquals(true, isValid);
	}
	
	@Test
	void validate_success_no_uppercase_characters() {
		boolean isValid = passwordValidationServiceV2.validate("abcdef1aa");
		assertEquals(true, isValid);
	}
	
	@Test
	void validate_success_no_digits() {
		boolean isValid = passwordValidationServiceV2.validate("abcdefaab");
		assertEquals(true, isValid);
	}
}

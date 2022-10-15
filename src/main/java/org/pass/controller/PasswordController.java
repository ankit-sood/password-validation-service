package org.pass.controller;

import org.pass.service.PasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
	@Autowired
	private PasswordValidationService passwordValidationService;
	
	@GetMapping("/validate/{password}")
	public String valiatePassword(@PathVariable("password") String password) {
		boolean isValid = passwordValidationService.validate(password);
		return isValid ? "Valid Password" : "Invalid Password";
	}
}

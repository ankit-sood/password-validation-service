package org.pass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidationServiceV2 {
	
	public boolean validate(String password) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		boolean isValid = false;
		try {
			if(password != null) {
				List<Callable<Boolean>> callableTasks = getAllValidationTasks(password);
				List<Future<Boolean>> futures = executorService.invokeAll(callableTasks);
				int count = 1;
				for(Future<Boolean> future: futures) {
					if(future.get()) {
						count ++;
					}
				}
				isValid = count >=3 ? true : false;
			}
			
		} catch (InterruptedException e) {
			System.out.println("Validations interrupted" + e.getStackTrace());
		} catch (ExecutionException e) {
			System.out.println("Exception during Execution interrupted" + e.getStackTrace());
		}
		return isValid;
	}
	
	private List<Callable<Boolean>> getAllValidationTasks(String password) {
		List<Callable<Boolean>> callableTasks = new ArrayList<>();
		callableTasks.add(checkPasswordLength(password));
		callableTasks.add(checkIfDigitsExists(password));
		callableTasks.add(checkLowerCaseLetterExists(password));
		callableTasks.add(checkUpperCaseLetterExists(password));
		return callableTasks;
	}
	
	private Callable<Boolean> checkPasswordLength(String password){
		return () -> {
			return password.length() >= 8;
		};
	}
	
	private Callable<Boolean> checkIfDigitsExists(String password){
		return () -> {
			return password.matches("^(?=.*\\d).+$");
		};
	}
	
	private Callable<Boolean> checkLowerCaseLetterExists(String password){
		return () -> {
			return password.matches("^(?=.*[a-z]).+$");
		};
	}
	
	private Callable<Boolean> checkUpperCaseLetterExists(String password){
		return () -> {
			return password.matches("^(?=.*[A-Z]).+$");
		};
	}
	
	
	public static void main(String[] args) {
		String password = "ab";
		System.out.println(password.matches("^(?=.*[A-Z]).+$"));
	}
}

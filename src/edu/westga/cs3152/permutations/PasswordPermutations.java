package edu.westga.cs3152.permutations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.passwordvariationdata.LetterToNumberVariations;

/**
 * Holds the permutations for each password
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */

public class PasswordPermutations {

	private KnownPasswordManager manager;
	private String password;
	private ArrayList<String> permutations;
	
	/**
	 * Creates a new password permutations class
	 * 
	 * @precondition password != null && password.isEmpty() == false && manager != null
	 * @postcondition 
	 *  this.password == password
	 *  this.permutations == new String[password.length()][Math.pow(2, password.length()]
	 *  this.manager == new KnownPasswordManager
	 * 
	 * @param manager the manager to find the permutations of
	 * @param password the password to find the permutations of
	 */
	public PasswordPermutations(KnownPasswordManager manager, String password) {
		this.manager = manager;
		this.password = password;
		this.permutations = new ArrayList<String>();
	}
	
	/**
	 * Gets the permutations of the password
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string permutations
	 */
	public ArrayList<String> getPermutations() {
		return this.permutations;
	}
	
	/**
	 * populates the password permutations with the permutations of the password string
	 * 
	 * @precondition none
	 * @postcondition this.permutations[0 through password.length] != null
	 */
	public void populateWithPermutations() {
		this.permutations.addAll(this.calculateCasePermutations(this.password.toUpperCase(), new ArrayList<String>()));
		ArrayList<String> letterToNumberPermutations = new ArrayList<String>();
		for (String casePermutation : this.permutations) {
			ArrayList<String> numberPermutations = this.letterToNumberPermutations(casePermutation, new ArrayList<String>());
			
			if (numberPermutations != null) {
				letterToNumberPermutations.addAll(numberPermutations);
			}
		}

		for (String numberPermutation : letterToNumberPermutations) {
			if (!this.permutations.contains(numberPermutation)) {
				this.permutations.add(numberPermutation);
			}
		}
	}
	
	public ArrayList<String> letterToNumberPermutations(String permutationsToCalculate, ArrayList<String> permutations) {
		LetterToNumberVariations letterToNumber = new LetterToNumberVariations();
		
		if (!letterToNumber.ifWordHasNumberVariant(permutationsToCalculate)) {
			return null;
		} 

		String[] passwordCharacters = permutationsToCalculate.split("");
		int numberOfNumberVariations = letterToNumber.getNumberOfVariationsInWord(permutationsToCalculate);
			
		int[] indexesToSwitchNumbers = new int[numberOfNumberVariations];
		int[] indexSwitchCounters = new int[numberOfNumberVariations];
			
		boolean[] isNumber = new boolean[numberOfNumberVariations];
			
		ArrayList<String> possibleNumbers = new ArrayList<String>();
		ArrayList<Integer> indexesOfPossibleNumbers = new ArrayList<Integer>();
			
		int switchArrayCounter = 0;
		System.out.println(permutationsToCalculate);
		for (int characterIndex = 0; characterIndex < permutationsToCalculate.length(); characterIndex++) {
			if (letterToNumber.getLettersNumberVariant(passwordCharacters[characterIndex]) != null) {
				possibleNumbers.add(passwordCharacters[characterIndex]);
				indexesOfPossibleNumbers.add(characterIndex);
					
				indexesToSwitchNumbers[switchArrayCounter] = (int) Math.pow(2, (numberOfNumberVariations - switchArrayCounter - 1));
				
				System.out.println("---  " + passwordCharacters[characterIndex]);
				System.out.println(indexesToSwitchNumbers[switchArrayCounter]);
				
				System.out.println(numberOfNumberVariations);
				System.out.println(characterIndex);
				indexSwitchCounters[switchArrayCounter] = 0;
				isNumber[switchArrayCounter] = true;

				switchArrayCounter++;
			}
		}

		for (int possibleNumberIndex = 0; possibleNumberIndex < Math.pow(2, possibleNumbers.size()); possibleNumberIndex++) {
			String[] permutation = permutationsToCalculate.split("");
				
			for (int characterIndex = 0; characterIndex < possibleNumbers.size(); characterIndex++) {
				int maximumIndexToToggleNumber = indexesToSwitchNumbers[characterIndex];
				
//				System.out.println("--------------");
//				System.out.println(possibleNumbers.get(characterIndex));
//				System.out.println(indexSwitchCounters[characterIndex]);
//				System.out.println(maximumIndexToToggleNumber);
				if (indexSwitchCounters[characterIndex] == maximumIndexToToggleNumber) {
					if (isNumber[characterIndex]) {
						isNumber[characterIndex] = false;
					} else {
						isNumber[characterIndex] = true;
					}
						
					indexSwitchCounters[characterIndex] = 0;
				}
				
				if (isNumber[characterIndex]) {
					permutation[indexesOfPossibleNumbers.get(characterIndex)] = letterToNumber.getLettersNumberVariant(possibleNumbers.get(characterIndex));
				} 
					
//				System.out.println(String.join("", permutation));
//				System.out.println("-----");
				indexSwitchCounters[characterIndex]++;
			}

			permutations.add(String.join("", permutation));
		}
		return permutations;
	}

	
	public ArrayList<String> calculateCasePermutations(String permutationsToCalculate, ArrayList<String> permutations) {
		int[] indexesToSwitchCase = new int[permutationsToCalculate.length()];
		boolean[] isUppercase = new boolean[permutationsToCalculate.length()];
		int[] caseSwitchCounters = new int[permutationsToCalculate.length()];
		
		for (int indexToSwitchCase = 0; indexToSwitchCase < permutationsToCalculate.length(); indexToSwitchCase++) {
			indexesToSwitchCase[indexToSwitchCase] = (int) Math.pow(2, (permutationsToCalculate.length() - indexToSwitchCase - 1));
			isUppercase[indexToSwitchCase] = true;
			caseSwitchCounters[indexToSwitchCase] = 0;
		}
		
		for (int passwordCounter = 0; passwordCounter < Math.pow(2, permutationsToCalculate.length()); passwordCounter++) {
			String[] permutation = permutationsToCalculate.split("");
			
			for (int permutationCounter = 0; permutationCounter < permutation.length; permutationCounter++) {
				int maximumIndexToToggleCase = indexesToSwitchCase[permutationCounter];
				
				if (caseSwitchCounters[permutationCounter] == maximumIndexToToggleCase) {
					if (isUppercase[permutationCounter]) {
						isUppercase[permutationCounter] = false;
					} else {
						isUppercase[permutationCounter] = true;
					}
					
					caseSwitchCounters[permutationCounter] = 0;
				}
				
				if (isUppercase[permutationCounter]) {
					permutation[permutationCounter] = permutation[permutationCounter].toUpperCase();
				} else {
					permutation[permutationCounter] = permutation[permutationCounter].toLowerCase();
				}
				
				caseSwitchCounters[permutationCounter]++;
			}
			
			permutations.add(String.join("", permutation));
		}

		return permutations;
	}
}

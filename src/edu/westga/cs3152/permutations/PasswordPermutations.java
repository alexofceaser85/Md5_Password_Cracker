package edu.westga.cs3152.permutations;

import java.util.ArrayList;

import edu.westga.cs3152.errormessages.PasswordPermutationsErrorMessages;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.passwordvariationdata.CharacterVariations;

/**
 * Holds the permutations for each password
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */

public class PasswordPermutations {

	private KnownPasswordManager manager;
	private CharacterVariations letterToNumber;
	private int numberOfNumberVariations;
	
	private int[] indexSwitchCounters;
	private int[] indexesToSwitchNumbers;
	private boolean[] isNumber;
	private String[] passwordCharacters;
	
	private ArrayList<String> possibleNumbers;
	private ArrayList<Integer> indexesOfPossibleNumbers;
	
	/**
	 * Creates a new password permutations class
	 * 
	 * @precondition password != null && password.isEmpty() == false && manager != null && letterToNumber != null
	 * @postcondition 
	 *  this.password == password
	 *  this.permutations == new String[password.length()][Math.pow(2, password.length()]
	 *  this.manager == new KnownPasswordManager
	 * 
	 * @param manager the manager to find the permutations of
	 * @param password the password to find the permutations of
	 * @param letterToNumber the letter to number variations
	 */
	public PasswordPermutations(KnownPasswordManager manager, String password, CharacterVariations letterToNumber) {
		if (manager == null) {
			throw new IllegalArgumentException(PasswordPermutationsErrorMessages.MANAGER_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(PasswordPermutationsErrorMessages.PASSWORD_CANNOT_BE_NULL);
		}
		if (password.isEmpty()) {
			throw new IllegalArgumentException(PasswordPermutationsErrorMessages.PASSWORD_CANNOT_BE_EMPTY);
		}
		if (letterToNumber == null) {
			throw new IllegalArgumentException(PasswordPermutationsErrorMessages.LETTER_TO_NUMBER_CONVERSIONS_CANNOT_BE_NULL);
		}
		
		this.manager = manager;

		this.letterToNumber = letterToNumber;
		this.numberOfNumberVariations = this.letterToNumber.getNumberOfVariationsInWord(password);
		
		this.indexesToSwitchNumbers = new int[this.numberOfNumberVariations];
		this.indexSwitchCounters = new int[this.numberOfNumberVariations];
		this.isNumber = new boolean[this.numberOfNumberVariations];
		this.passwordCharacters = password.split("");
		
		this.possibleNumbers = new ArrayList<String>();
		this.indexesOfPossibleNumbers = new ArrayList<Integer>();
	}
	
	/**
	 * Gets the permutations of the password
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string permutations
	 */
	public KnownPasswordManager getPermutations() {
		return this.manager;
	}

	/**
	 * Populates the letter variations
	 * 
	 * @precondition none
	 * @postcondition this.manager.getKnownPasswords() > 0
	 */
	public void populateLetterVariations() {
		this.overrideDefaultValuesWithPasswordValues();
		this.addLetterToNumberPermutations();
	}

	private void addLetterToNumberPermutations() {
		for (int possibleNumberIndex = 0; possibleNumberIndex < Math.pow(2, this.possibleNumbers.size()); possibleNumberIndex++) {
			String[] permutation = this.passwordCharacters.clone();
				
			for (int characterIndex = 0; characterIndex < this.possibleNumbers.size(); characterIndex++) {
				if (this.indexSwitchCounters[characterIndex] == this.indexesToSwitchNumbers[characterIndex]) {
					if (this.isNumber[characterIndex]) {
						this.isNumber[characterIndex] = false;
					} else {
						this.isNumber[characterIndex] = true;
					}

					this.indexSwitchCounters[characterIndex] = 0;
				}
				
				if (this.isNumber[characterIndex]) {
					permutation[this.indexesOfPossibleNumbers.get(characterIndex)] = this.letterToNumber.getLettersNumberVariant(this.possibleNumbers.get(characterIndex));
				} 

				this.indexSwitchCounters[characterIndex]++;
			}

			this.manager.addPassword(String.join("", permutation));
		}
	}

	private void overrideDefaultValuesWithPasswordValues() {
		int switchArrayCounter = 0;
		for (int characterIndex = 0; characterIndex < this.passwordCharacters.length; characterIndex++) {
			if (this.letterToNumber.getLettersNumberVariant(this.passwordCharacters[characterIndex]) != null) {
				this.possibleNumbers.add(this.passwordCharacters[characterIndex]);
				this.indexesOfPossibleNumbers.add(characterIndex);
					
				this.indexesToSwitchNumbers[switchArrayCounter] = (int) Math.pow(2, (this.numberOfNumberVariations - switchArrayCounter - 1));

				this.indexSwitchCounters[switchArrayCounter] = 0;
				this.isNumber[switchArrayCounter] = true;

				switchArrayCounter++;
			}
		}
	}
}

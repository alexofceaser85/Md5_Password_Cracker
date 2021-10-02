package edu.westga.cs3152.permutations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.westga.cs3152.hashing.SimpleCrypt;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.passwordvariationdata.LetterToNumberVariations;
import edu.westga.cs3152.permutations.characterdata.Letters;
import edu.westga.cs3152.permutations.characterdata.Numbers;

/**
 * Holds the permutations for each password
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */

public class PasswordPermutations {

	private KnownPasswordManager manager;
	private String password;
	private FileWriter knownFileWriter;
	private int[] indexesToSwitchNumbers;
	private int numberOfNumberVariations;
	private LetterToNumberVariations letterToNumber;
	private int[] indexSwitchCounters;
	private boolean[] isNumber;
	private String[] passwordCharacters;
	
	private ArrayList<String> possibleNumbers;
	private ArrayList<Integer> indexesOfPossibleNumbers;
	
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
	public PasswordPermutations(KnownPasswordManager manager, String password, FileWriter knownFileWriter) {
		this.manager = manager;
		this.password = password;
		this.knownFileWriter = knownFileWriter;
		
		this.letterToNumber = new LetterToNumberVariations();
		this.letterToNumber.getNumberOfVariationsInWord(password);
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

	public void populateLetterToNumberPermutations(SimpleCrypt crypt) throws IOException {
		this.overrideDefaultValuesWithPasswordValues();
		this.addLetterToNumberPermutations(crypt);
	}

	private void addLetterToNumberPermutations(SimpleCrypt crypt) throws IOException {
		for (int possibleNumberIndex = 0; possibleNumberIndex < Math.pow(2, this.possibleNumbers.size()); possibleNumberIndex++) {
			String[] permutation = this.password.split("");
				
			for (int characterIndex = 0; characterIndex < this.possibleNumbers.size(); characterIndex++) {
				int maximumIndexToToggleNumber = this.indexesToSwitchNumbers[characterIndex];
				
				if (this.indexSwitchCounters[characterIndex] == maximumIndexToToggleNumber) {
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

			String joinedPermutation = String.join("", permutation);
			String passwordHash = crypt.generateHash(joinedPermutation);
			
			this.manager.addPassword(passwordHash, joinedPermutation);
			//this.knownFileWriter.append(joinedPermutation + "," + passwordHash + "\n");
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

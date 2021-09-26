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
	 * populates the password permutations with the permutations of the password string
	 * 
	 * @precondition none
	 * @postcondition this.permutations[0 through password.length] != null
	 */
	public void populateWithPermutations() throws IOException {
		this.letterToNumberPermutations(this.password);
 	}
	
	
	public void letterToNumberPermutations(String permutationsToCalculate) throws IOException {
		SimpleCrypt crypt = new SimpleCrypt();
		
		LetterToNumberVariations letterToNumber = new LetterToNumberVariations();

		String[] passwordCharacters = permutationsToCalculate.split("");
		int numberOfNumberVariations = letterToNumber.getNumberOfVariationsInWord(permutationsToCalculate);
			
		int[] indexesToSwitchNumbers = new int[numberOfNumberVariations];
		int[] indexSwitchCounters = new int[numberOfNumberVariations];
			
		boolean[] isNumber = new boolean[numberOfNumberVariations];
			
		ArrayList<String> possibleNumbers = new ArrayList<String>();
		ArrayList<Integer> indexesOfPossibleNumbers = new ArrayList<Integer>();
			
		int switchArrayCounter = 0;
		for (int characterIndex = 0; characterIndex < permutationsToCalculate.length(); characterIndex++) {
			if (letterToNumber.getLettersNumberVariant(passwordCharacters[characterIndex]) != null) {
				possibleNumbers.add(passwordCharacters[characterIndex]);
				indexesOfPossibleNumbers.add(characterIndex);
					
				indexesToSwitchNumbers[switchArrayCounter] = (int) Math.pow(2, (numberOfNumberVariations - switchArrayCounter - 1));

				indexSwitchCounters[switchArrayCounter] = 0;
				isNumber[switchArrayCounter] = true;

				switchArrayCounter++;
			}
		}

		for (int possibleNumberIndex = 0; possibleNumberIndex < Math.pow(2, possibleNumbers.size()); possibleNumberIndex++) {
			String[] permutation = permutationsToCalculate.split("");
				
			for (int characterIndex = 0; characterIndex < possibleNumbers.size(); characterIndex++) {
				int maximumIndexToToggleNumber = indexesToSwitchNumbers[characterIndex];
				
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

				indexSwitchCounters[characterIndex]++;
			}

			String joinedPermutation = String.join("", permutation);
			String passwordHash = crypt.generateHash(joinedPermutation);
			
			this.manager.addPassword(passwordHash, joinedPermutation);
			this.knownFileWriter.append(joinedPermutation + "," + passwordHash + "\n");
		}
	}
}

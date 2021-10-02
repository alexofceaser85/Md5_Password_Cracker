package edu.westga.cs3152.permutations;

import edu.westga.cs3152.errormessages.StarterPasswordPermutationsErrorMessages;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.characterdata.Letters;
import edu.westga.cs3152.permutations.characterdata.Numbers;

/**
 * Calculates the permutations for the starter passwords
 * 
 * @author Alex DeCesare
 * @version 02-October-2021
 */
public class StarterPasswordPermutations {
	
	private static final int LETTERS_LENGTH = Letters.LOWER_CASE_LETTERS.length;
	private static final int NUMBERS_LENGTH = Numbers.NUMBER_ZERO_TO_NINE.length;

	private String[] possibleNumbers;
	private int[] alphabetIndexes;
	private int[] starterLetterIndexes;
	private int[] starterNumberIndexes;
	private int[] switchIndexesForStarterLetters;
	private int[] switchIndexesForStarterNumbers;
	private KnownPasswordManager manager;
	
	/**
	 * Calculates the permutations of the starter password
	 * 
	 * @precondition 
	 *     uppercaseLetterIndexes != null
	 *     && manager != null
	 *     && knownFileWriter != null
	 *     
	 * @postcondition
	 * 	   this.possibleNumbers = new String[(int) Math.pow(10, this.amountOfStarterNumbers)];
	 * 	   this.starterLetterIndexes = new int[this.amountOfStarterLetters];
	 * 	   this.starterNumberIndexes = new int[this.amountOfStarterNumbers];
	 *     this.switchIndexesForStarterLetters = new int[this.amountOfStarterLetters];
	 *     this.switchIndexesForStarterNumbers = new int[this.amountOfStarterNumbers];
	 *     this.alphabetIndexes = new int[Letters.LOWER_CASE_LETTERS.length];
	 *     this.manager = manager;
	 * 
	 * @param manager the manager to add the permutations to
	 */
	public StarterPasswordPermutations(KnownPasswordManager manager) {
		if (manager == null) {
			throw new IllegalArgumentException(StarterPasswordPermutationsErrorMessages.CANNOT_FIND_STARTER_PERMUTATIONS_FOR_NULL_MANAGER);
		}
		
		this.possibleNumbers = new String[(int) Math.pow(10, StarterPasswordFormat.AMOUNT_OF_STARTER_NUMBERS)];
		this.starterLetterIndexes = new int[StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS];
		this.starterNumberIndexes = new int[StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS];
		this.switchIndexesForStarterLetters = new int[StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS];
		this.switchIndexesForStarterNumbers = new int[StarterPasswordFormat.AMOUNT_OF_STARTER_NUMBERS];
		this.alphabetIndexes = new int[Letters.LOWER_CASE_LETTERS.length];
		this.manager = manager;
	}

	/**
	 * Calculates the starter password permutations 
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void calculateStarterPasswordPermutations() {
		this.initializePermutationIndexes();
		this.initializeNumberPortionValues();
		this.createPermutations();
	}

	private void createPermutations() {
		for (int passwordLetterIndex = 0; passwordLetterIndex < Math.pow(LETTERS_LENGTH, StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS); passwordLetterIndex++) {
			String permutation = this.calculateCasePermutation();
			this.calculateNumberPermutations(permutation);
		}
	}
	
	private String calculateCasePermutation() {
		String permutation = "";
		
		for (int passwordCharacterIndex = 0; passwordCharacterIndex < StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS; passwordCharacterIndex++) {
			if (this.starterLetterIndexes[passwordCharacterIndex] == this.switchIndexesForStarterLetters[passwordCharacterIndex]) {
				this.alphabetIndexes[passwordCharacterIndex]++;
				this.starterLetterIndexes[passwordCharacterIndex] = 0;
			} 
			
			if (this.alphabetIndexes[passwordCharacterIndex] == this.alphabetIndexes.length) {
				this.alphabetIndexes[passwordCharacterIndex] = 0;
			}
			
			if (this.isUppercase(passwordCharacterIndex, StarterPasswordFormat.UPPERCASE_LETTER_INDEXES)) {
				permutation += Letters.UPPER_CASE_LETTERS[this.alphabetIndexes[passwordCharacterIndex]];
			} else {
				permutation += Letters.LOWER_CASE_LETTERS[this.alphabetIndexes[passwordCharacterIndex]];
			}
			
			this.starterLetterIndexes[passwordCharacterIndex]++;
		}
		return permutation;
	}
	
	private void calculateNumberPermutations(String permutation) {
		for (int passwordNumberIndex = 0; passwordNumberIndex < this.possibleNumbers.length; passwordNumberIndex++) {
			this.manager.addPassword(permutation + this.possibleNumbers[passwordNumberIndex]);
		}
	}

	private void initializePermutationIndexes() {
		for (int starterLetterCounter = 0; starterLetterCounter < StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS; starterLetterCounter++) {
			this.starterLetterIndexes[starterLetterCounter] = 0;
			this.switchIndexesForStarterLetters[starterLetterCounter] = (int) Math.pow(LETTERS_LENGTH, StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS - starterLetterCounter - 1);
		}
		
		for (int starterNumberCounter = 0; starterNumberCounter < StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS; starterNumberCounter++) {
			this.starterNumberIndexes[starterNumberCounter] = 0;
			this.switchIndexesForStarterNumbers[starterNumberCounter] = (int) Math.pow(NUMBERS_LENGTH, StarterPasswordFormat.AMOUNT_OF_STARTER_LETTERS - starterNumberCounter - 1);
		}
	}
	
	private void initializeNumberPortionValues() {
		for (int possibleNumberIndex = 0; possibleNumberIndex < this.possibleNumbers.length; possibleNumberIndex++) {
			this.possibleNumbers[possibleNumberIndex] = String.format(StarterPasswordFormat.STARTER_NUMBERS_STRING_FORMAT, possibleNumberIndex);
		}
	}
	
	private boolean isUppercase(int indexToCheck, int[] uppercaseLetterIndexes) {
		for (int index : uppercaseLetterIndexes) {
			if (indexToCheck == index) {
				return true;
			}
		}
		
		return false;
	}
}
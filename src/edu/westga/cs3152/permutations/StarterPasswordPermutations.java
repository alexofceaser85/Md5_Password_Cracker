package edu.westga.cs3152.permutations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.westga.cs3152.hashing.SimpleCrypt;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.characterdata.Letters;
import edu.westga.cs3152.permutations.characterdata.Numbers;

public class StarterPasswordPermutations {
	
	private final int amountOfStarterLetters = 2;
	private final int amountOfStarterNumbers = 4;
	
	private String[] possibleNumbers;
	private int[] alphabetIndexes;
	private int[] starterLetterIndexes;
	private int[] starterNumberIndexes;
	private int[] switchIndexesForStarterLetters;
	private int[] switchIndexesForStarterNumbers;
	private int[] uppercaseLetterIndexes;
	private KnownPasswordManager manager;
	private FileWriter knownFileWriter;
	
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
	 *     this.uppercaseLetterIndexes = uppercaseLetterIndexes;
	 *     this.manager = manager;
	 *     this.knownFileWriter = knownFileWriter;
	 * 
	 * @param uppercaseLetterIndexes the indexes for the uppercase letters
	 * @param manager the manager to add the permutations to
	 * @param knownFileWriter the file to save the permutations to
	 */
	public StarterPasswordPermutations(int[] uppercaseLetterIndexes, KnownPasswordManager manager, FileWriter knownFileWriter) {
		if (uppercaseLetterIndexes == null) {
			throw new IllegalArgumentException("uppercase letter indexes cannot be null");
		}
		if (manager == null) {
			throw new IllegalArgumentException("manager cannot be null");
		}
		if (knownFileWriter == null) {
			throw new IllegalArgumentException("known file writer cannot be null");
		}
		
		this.possibleNumbers = new String[(int) Math.pow(10, this.amountOfStarterNumbers)];
		this.starterLetterIndexes = new int[this.amountOfStarterLetters];
		this.starterNumberIndexes = new int[this.amountOfStarterNumbers];
		this.switchIndexesForStarterLetters = new int[this.amountOfStarterLetters];
		this.switchIndexesForStarterNumbers = new int[this.amountOfStarterNumbers];
		this.alphabetIndexes = new int[Letters.LOWER_CASE_LETTERS.length];
		this.uppercaseLetterIndexes = uppercaseLetterIndexes;
		this.manager = manager;
		this.knownFileWriter = knownFileWriter;
	}

	public void calculateStarterPasswordPermutations(SimpleCrypt crypt) throws IOException {
		
		this.initializePermutationIndexes();

		for (int possibleNumberIndex = 0; possibleNumberIndex < this.possibleNumbers.length; possibleNumberIndex++) {
			this.possibleNumbers[possibleNumberIndex] = String.format("%04d", possibleNumberIndex);
		}
		
		for (int passwordLetterIndex = 0; passwordLetterIndex < Math.pow(Letters.LOWER_CASE_LETTERS.length, this.amountOfStarterLetters); passwordLetterIndex++) {
			String permutation = "";
			
			permutation = this.calculateCasePermutation(permutation);
			this.calculateNumberPermutations(permutation, crypt);
		}
	}

	private void initializePermutationIndexes() {
		for (int starterLetterCounter = 0; starterLetterCounter < amountOfStarterLetters; starterLetterCounter++) {
			this.starterLetterIndexes[starterLetterCounter] = 0;
			this.switchIndexesForStarterLetters[starterLetterCounter] = (int) Math.pow(Letters.LOWER_CASE_LETTERS.length, this.amountOfStarterLetters - starterLetterCounter - 1);
		}
		
		for (int starterNumberCounter = 0; starterNumberCounter < amountOfStarterNumbers; starterNumberCounter++) {
			this.starterNumberIndexes[starterNumberCounter] = 0;
			this.switchIndexesForStarterNumbers[starterNumberCounter] = (int) Math.pow(Numbers.NUMBER_ZERO_TO_NINE.length, this.amountOfStarterNumbers - starterNumberCounter - 1);
		}
	}

	private void calculateNumberPermutations(String permutation, SimpleCrypt crypt) throws IOException {
		for (int passwordNumberIndex = 0; passwordNumberIndex < this.possibleNumbers.length; passwordNumberIndex++) {
			String passwordWithNumber = permutation + this.possibleNumbers[passwordNumberIndex];
			String passwordHash = crypt.generateHash(passwordWithNumber);
			
			this.manager.addPassword(passwordHash, passwordWithNumber);
			this.knownFileWriter.append(passwordWithNumber + "," + passwordHash + "\n");
		}
	}

	private String calculateCasePermutation(String permutation) {
		for (int passwordCharacterIndex = 0; passwordCharacterIndex < amountOfStarterLetters; passwordCharacterIndex++) {
			
			if (this.starterLetterIndexes[passwordCharacterIndex] == this.switchIndexesForStarterLetters[passwordCharacterIndex]) {
				this.alphabetIndexes[passwordCharacterIndex]++;
				this.starterLetterIndexes[passwordCharacterIndex] = 0;
			} 
			
			if (this.alphabetIndexes[passwordCharacterIndex] == this.alphabetIndexes.length) {
				this.alphabetIndexes[passwordCharacterIndex] = 0;
			}
			
			if (isUppercase(passwordCharacterIndex, this.uppercaseLetterIndexes)) {
				permutation += Letters.UPPER_CASE_LETTERS[this.alphabetIndexes[passwordCharacterIndex]];
			} else {
				permutation += Letters.LOWER_CASE_LETTERS[this.alphabetIndexes[passwordCharacterIndex]];
			}
			
			this.starterLetterIndexes[passwordCharacterIndex]++;
		}
		return permutation;
	}
	
	private static boolean isUppercase(int indexToCheck, int[] uppercaseLetterIndexes) {
		for (int index : uppercaseLetterIndexes) {
			if (indexToCheck == index) {
				return true;
			}
		}
		
		return false;
	}
}
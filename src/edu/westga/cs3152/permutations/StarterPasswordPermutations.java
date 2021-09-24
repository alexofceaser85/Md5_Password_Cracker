package edu.westga.cs3152.permutations;

import java.util.ArrayList;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.characterdata.Letters;
import edu.westga.cs3152.permutations.characterdata.Numbers;

public final class StarterPasswordPermutations {

	public static ArrayList<String> starterPasswordPermutations(int[] uppercaseLetterIndexes) {
		ArrayList<String> passwordPermutations = new ArrayList<String>();
		
		int amountOfStarterLetters = 2;
		int amountOfStarterNumbers = 4;
		
		int[] alphabetIndexes = new int[Letters.LOWER_CASE_LETTERS.length];
		int[] numberIndexes = new int[Numbers.NUMBER_ZERO_TO_NINE.length];
		
		int[] starterLetterIndexes = new int[amountOfStarterLetters];
		int[] starterNumberIndexes = new int[amountOfStarterNumbers];
		
		int[] switchIndexesForStarterLetters = new int[amountOfStarterLetters];
		int[] switchIndexesForStarterNumbers = new int[amountOfStarterNumbers];
		
		for (int starterLetterCounter = 0; starterLetterCounter < amountOfStarterLetters; starterLetterCounter++) {
			starterLetterIndexes[starterLetterCounter] = 0;
			switchIndexesForStarterLetters[starterLetterCounter] = (int) Math.pow(Letters.LOWER_CASE_LETTERS.length, amountOfStarterLetters - starterLetterCounter - 1);
		}
		
		for (int starterNumberCounter = 0; starterNumberCounter < amountOfStarterNumbers; starterNumberCounter++) {
			starterNumberIndexes[starterNumberCounter] = 0;
			switchIndexesForStarterNumbers[starterNumberCounter] = (int) Math.pow(Numbers.NUMBER_ZERO_TO_NINE.length, amountOfStarterNumbers - starterNumberCounter - 1);
		}

		for (int passwordLetterIndex = 0; passwordLetterIndex < Math.pow(26, amountOfStarterLetters); passwordLetterIndex++) {
			String passwordPermutation = "";
			String permutation = "";
			
			for (int passwordCharacterIndex = 0; passwordCharacterIndex < amountOfStarterLetters; passwordCharacterIndex++) {
				
				if (starterLetterIndexes[passwordCharacterIndex] == switchIndexesForStarterLetters[passwordCharacterIndex]) {
					alphabetIndexes[passwordCharacterIndex]++;
					starterLetterIndexes[passwordCharacterIndex] = 0;
				} 
					
				starterLetterIndexes[passwordCharacterIndex]++;
				
				if (alphabetIndexes[passwordCharacterIndex] == 26) {
					alphabetIndexes[passwordCharacterIndex] = 0;
				}
				
				if (isUppercase(passwordCharacterIndex, uppercaseLetterIndexes)) {
					permutation += Letters.LOWER_CASE_LETTERS[alphabetIndexes[passwordCharacterIndex]].toUpperCase();
				} else {
					permutation += Letters.LOWER_CASE_LETTERS[alphabetIndexes[passwordCharacterIndex]];
				}
				
			}
			
			ArrayList<String> numberedPasswordPermutations = new ArrayList<String>();
			for (int passwordNumberIndex = 0; passwordNumberIndex < Math.pow(10, amountOfStarterNumbers); passwordNumberIndex++) {
				numberedPasswordPermutations.add(permutation + String.format("%04d", passwordNumberIndex));
			}
			
			passwordPermutations.addAll(numberedPasswordPermutations);
		}
		
		return passwordPermutations;
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
package edu.westga.cs3152.passwordvariationdata;

import java.util.HashMap;

import edu.westga.cs3152.errormessages.CharacterVariationsErrorMessages;

/**
 * Holds the letter to number variations for a password
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */
public class CharacterVariations {

	private HashMap<String, String> letterToNumberVariations;
	
	/**
	 * Creates a new letter to number variation
	 * 
	 * @precondition none
	 * @postcondition 
	 * this.letterToNumberVariations == new HashMap<String, String> 
	 * && this.letterToNumberVariations.isEmpty() == false
	 */
	public CharacterVariations() {
		this.letterToNumberVariations = new HashMap<String, String>();
		this.populateLetterToNumberVariations();
	}
	
	/**
	 * Gets the number of variations
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of variations
	 */
	public int getNumberOfVariations() {
		return this.letterToNumberVariations.size();
	}
	
	/**
	 * Gets the number of letters that have variations in a word
	 * 
	 * @precondition wordToCheck != null
	 * @postcondition none
	 * 
	 * @param wordToCheck the word to get the number of variations of
	 * 
	 * @return the number of letters that have variations in a word
	 */
	
	public int getNumberOfVariationsInWord(String wordToCheck) {
		if (wordToCheck == null) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_GET_NUMBER_OF_VARIANTS_OF_NULL_WORD);
		}
		if (wordToCheck.isEmpty()) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_GET_NUMBER_VARIANT_OF_EMPTY_WORD);
		}
		
		int numberOfVariations = 0;
		String[] wordCollection = wordToCheck.split("");
		
		for (String letter : wordCollection) {
			if (this.letterToNumberVariations.containsKey(letter)) {
				numberOfVariations++;
			}
		}
		
		return numberOfVariations;
	}

	/**
	 * Determines if a given word has a number variant
	 * 
	 * @precondition wordToCheck != null
	 * @postcondition none
	 * @param wordToCheck the word to check if it has any number variants
	 * @return true if the given word has number variants, false otherwise
	 */
	
	public boolean ifWordHasNumberVariant(String wordToCheck) {
		if (wordToCheck == null) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_DETERMINE_IF_NULL_WORD_HAS_NUMBER_VARIANT);
		}
		if (wordToCheck.isEmpty()) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_DETERMINE_IF_EMPTY_WORD_HAS_NUMBER_VARIANT);
		}
		
		String[] wordCollection = wordToCheck.split("");
		
		for (String letter : wordCollection) {
			if (this.letterToNumberVariations.containsKey(letter)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * gets the number variant of a letter
	 * 
	 * @precondition letterToGet != null
	 * @postcondition none
	 * 
	 * @param letterToGet the letter to use to get its number variant
	 * @return the number variant of the letter, null if letter has no variant
	 */
	public String getLettersNumberVariant(String letterToGet) {
		if (letterToGet == null) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_GET_NUMBER_VARIANT_OF_NULL_WORD);
		}
		if (letterToGet.isEmpty()) {
			throw new IllegalArgumentException(CharacterVariationsErrorMessages.CANNOT_GET_NUMBER_VARIANT_OF_EMPTY_WORD);
		}
		
		if (this.letterToNumberVariations.containsKey(letterToGet)) {
			return this.letterToNumberVariations.get(letterToGet);
		}
		
		return null;
	}
	
	private void populateLetterToNumberVariations() {
		this.populateLowerCase();
		this.populateUpperCase();
	}
	
	private void populateLowerCase() {
		this.letterToNumberVariations.put("e", "3");
		this.letterToNumberVariations.put("i", "1");
		this.letterToNumberVariations.put("o", "0");
		this.letterToNumberVariations.put("s", "5");
	}
	
	
	private void populateUpperCase() {
		this.letterToNumberVariations.put("E", "3");
		this.letterToNumberVariations.put("I", "1");
		this.letterToNumberVariations.put("O", "0");
		this.letterToNumberVariations.put("S", "5");
	}
}

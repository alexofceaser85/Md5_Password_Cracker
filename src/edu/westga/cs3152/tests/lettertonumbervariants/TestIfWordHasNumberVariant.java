package edu.westga.cs3152.tests.lettertonumbervariants;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordvariationdata.LetterToNumberVariations;

class TestIfWordHasNumberVariant {

	@Test
	void shouldNotFindIfWordHasNumberVariantOfNullWord() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		
		assertThrows(IllegalArgumentException.class, () -> {
			variations.ifWordHasNumberVariant(null);
		});
	}
	
	@Test
	void shouldReturnFalseForEmptyWord() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertFalse(variations.ifWordHasNumberVariant(""));
	}

	@Test
	void shouldReturnFalseForWordWithOneNotMatchingLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertFalse(variations.ifWordHasNumberVariant("A"));
	}
	
	@Test
	void shouldReturnFalseForOneLetterWordWithOneLowercaseMatchingLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("e"));
	}
	
	@Test
	void shouldReturnFalseForOneLetterWordWithOneUppercaseMatchingLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("E"));
	}
	
	@Test
	void shouldReturnFalseForManyLetterWordWithOneLowercaseMatchingLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("hello"));
	}
	
	@Test
	void shouldReturnFalseForManyLetterWordWithOneUppercaseMatchingLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("HELLO"));
	}
	
	@Test
	void shouldReturnFalseForManyLetterWordWithManyLowercaseMatchingLetters() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("hello i say"));
	}
	
	@Test
	void shouldReturnFalseForManyLetterWordWithManyUppercaseMatchingLetters() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertTrue(variations.ifWordHasNumberVariant("HELLO I SAY"));
	}
}

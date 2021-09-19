package edu.westga.cs3152.tests.lettertonumbervariants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordvariationdata.LetterToNumberVariations;

class TestGetLettersNumberVariant {

	@Test
	void shouldNotGetNumberVariantOfNullLetter() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertThrows(IllegalArgumentException.class, () -> {
			variations.getLettersNumberVariant(null);
		});
	}
	
	@Test
	void shouldReturnLetterIfLetterHasNoNumberVariant() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertEquals("z", variations.getLettersNumberVariant("z"));
	}

	@Test
	void shouldReturnNumberIfLowercaseLetterHasNumberVariant() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertEquals("3", variations.getLettersNumberVariant("e"));
	}
	
	@Test
	void shouldReturnNumberIfUppercasecaseLetterHasNumberVariant() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		assertEquals("3", variations.getLettersNumberVariant("E"));
	}
}

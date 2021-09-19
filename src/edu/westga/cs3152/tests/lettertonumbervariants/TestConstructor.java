package edu.westga.cs3152.tests.lettertonumbervariants;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordvariationdata.LetterToNumberVariations;

class TestConstructor {

	@Test
	void shouldInitializeDefaultValues() {
		LetterToNumberVariations variations = new LetterToNumberVariations();
		
		assertEquals(8, variations.getNumberOfVariations());
		
		assertEquals("3", variations.getLettersNumberVariant("e"));
		assertEquals("1", variations.getLettersNumberVariant("i"));
		assertEquals("0", variations.getLettersNumberVariant("o"));
		assertEquals("5", variations.getLettersNumberVariant("s"));
		
		assertEquals("3", variations.getLettersNumberVariant("E"));
		assertEquals("1", variations.getLettersNumberVariant("I"));
		assertEquals("0", variations.getLettersNumberVariant("O"));
		assertEquals("5", variations.getLettersNumberVariant("S"));
	}

}

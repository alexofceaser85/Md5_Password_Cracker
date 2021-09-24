package edu.westga.cs3152.tests.permutations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.PasswordPermutations;

class TestPopulateWithPermutations {	
	
//	@Test
//	void shouldPopulateOneCharacter() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "i");
//		assertEquals("Permutations of i" + System.lineSeparator()
//			+ "I" + System.lineSeparator()
//			+ "i" + System.lineSeparator(), this.toString(permutations.calculateCasePermutations("i", new ArrayList<String>()), "i"));
//	}
//	
//	@Test
//	void shouldPopulateTwoCharacters() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "hi");
//		assertEquals("Permutations of hi" + System.lineSeparator()
//			+ "HI" + System.lineSeparator()
//			+ "Hi" + System.lineSeparator()
//			+ "hI" + System.lineSeparator()
//			+ "hi" + System.lineSeparator(), this.toString(permutations.calculateCasePermutations("hi", new ArrayList<String>()), "hi"));
//	}
//	
//	@Test
//	void shouldPopulateManyUppercaseCharacters() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "QEST");
//		assertEquals("Permutations of qest" + System.lineSeparator()
//			+ "QEST" + System.lineSeparator()
//			+ "QESt" + System.lineSeparator()
//			+ "QEsT" + System.lineSeparator()
//			+ "QEst" + System.lineSeparator()
//			+ "QeST" + System.lineSeparator()
//			+ "QeSt" + System.lineSeparator()
//			+ "QesT" + System.lineSeparator()
//			+ "Qest" + System.lineSeparator()
//			+ "qEST" + System.lineSeparator()
//			+ "qESt" + System.lineSeparator()
//			+ "qEsT" + System.lineSeparator()
//			+ "qEst" + System.lineSeparator()
//			+ "qeST" + System.lineSeparator()
//			+ "qeSt" + System.lineSeparator()
//			+ "qesT" + System.lineSeparator()
//			+ "qest" + System.lineSeparator(), this.toString(permutations.calculateCasePermutations("QEST", new ArrayList<String>()), "qest"));
//	}
//	
//	@Test
//	void shouldPopulateManyLowercaseCharacters() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "qest");
//		assertEquals("Permutations of qest" + System.lineSeparator()
//				+ "QEST" + System.lineSeparator()
//				+ "QESt" + System.lineSeparator()
//				+ "QEsT" + System.lineSeparator()
//				+ "QEst" + System.lineSeparator()
//				+ "QeST" + System.lineSeparator()
//				+ "QeSt" + System.lineSeparator()
//				+ "QesT" + System.lineSeparator()
//				+ "Qest" + System.lineSeparator()
//				+ "qEST" + System.lineSeparator()
//				+ "qESt" + System.lineSeparator()
//				+ "qEsT" + System.lineSeparator()
//				+ "qEst" + System.lineSeparator()
//				+ "qeST" + System.lineSeparator()
//				+ "qeSt" + System.lineSeparator()
//				+ "qesT" + System.lineSeparator()
//				+ "qest" + System.lineSeparator(), this.toString(permutations.calculateCasePermutations("qest", new ArrayList<String>()), "qest"));
//	}
//	
//	@Test
//	void shouldPopulateWithOneDigit() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "hel");
//		assertEquals("Permutations of hel" + System.lineSeparator()
//				+ "h3l" + System.lineSeparator()
//				+ "hel" + System.lineSeparator(), this.toString(permutations.letterToNumberPermutations("hel", new ArrayList<String>()), "hel"));
//	}
//	
//	@Test
//	void shouldPopulateWithManyDigits() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "heiol");
//		assertEquals("Permutations of heiol" + System.lineSeparator()
//				+ "h310l" + System.lineSeparator()
//				+ "h31ol" + System.lineSeparator()
//				+ "h3i0l" + System.lineSeparator()
//				+ "h3iol" + System.lineSeparator()
//				+ "he10l" + System.lineSeparator()
//				+ "he1ol" + System.lineSeparator()
//				+ "hei0l" + System.lineSeparator()
//				+ "heiol" + System.lineSeparator(), this.toString(permutations.letterToNumberPermutations("heiol", new ArrayList<String>()), "heiol"));
//	}
//	
//	@Test
//	void shouldReturnNullIfWordHasNotNumberVaraint() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "yyyyyy");
//		assertEquals(null, permutations.letterToNumberPermutations("yyyyyy", new ArrayList<String>()), "yyyyyy");
//	}
//	
//	@Test
//	void shouldPopulateForOneLetterForBothNumbersAndCaseIfThereAreNoNumberVariants() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "y");
//		permutations.populateWithPermutations();
//		assertEquals("Permutations of y" + System.lineSeparator()
//			+ "Y" + System.lineSeparator()
//			+ "y" + System.lineSeparator(), this.toString(permutations.getPermutations(), "y"));
//	}
//	
//	@Test
//	void shouldPopulateForOneLetterForBothNumbersAndCaseIfThereAreNumberVariants() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "e");
//		permutations.populateWithPermutations();
//		assertEquals("Permutations of e" + System.lineSeparator()
//			+ "E" + System.lineSeparator()
//			+ "e" + System.lineSeparator()
//			+ "3" + System.lineSeparator(), this.toString(permutations.getPermutations(), "e"));
//	}
//	
//	@Test
//	void shouldPopulateForManyLettersAndNumbersAndCaseIfThereAreNumberVariants() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "qest");
//		permutations.populateWithPermutations();
//		assertEquals("Permutations of qest" + System.lineSeparator()
//				+ "QEST" + System.lineSeparator()
//				+ "QESt" + System.lineSeparator()
//				+ "QEsT" + System.lineSeparator()
//				+ "QEst" + System.lineSeparator()
//				+ "QeST" + System.lineSeparator()
//				+ "QeSt" + System.lineSeparator()
//				+ "QesT" + System.lineSeparator()
//				+ "Qest" + System.lineSeparator()
//				+ "qEST" + System.lineSeparator()
//				+ "qESt" + System.lineSeparator()
//				+ "qEsT" + System.lineSeparator()
//				+ "qEst" + System.lineSeparator()
//				+ "qeST" + System.lineSeparator()
//				+ "qeSt" + System.lineSeparator()
//				+ "qesT" + System.lineSeparator()
//				+ "qest" + System.lineSeparator()
//				
//				+ "Q35T" + System.lineSeparator()
//				+ "Q3ST" + System.lineSeparator()
//				+ "QE5T" + System.lineSeparator()
//				
//				+ "Q35t" + System.lineSeparator()
//				+ "Q3St" + System.lineSeparator()
//				+ "QE5t" + System.lineSeparator()
//				
//				+ "Q3sT" + System.lineSeparator()
//
//				+ "Q3st" + System.lineSeparator()
//				+ "Qe5T" + System.lineSeparator()
//				+ "Qe5t" + System.lineSeparator()
//				+ "q35T" + System.lineSeparator()
//				+ "q3ST" + System.lineSeparator()
//				+ "qE5T" + System.lineSeparator()
//				+ "q35t" + System.lineSeparator()
//				+ "q3St" + System.lineSeparator()
//				+ "qE5t" + System.lineSeparator()
//				+ "q3sT" + System.lineSeparator()
//				+ "q3st" + System.lineSeparator()
//				+ "qe5T" + System.lineSeparator()
//				+ "qe5t" + System.lineSeparator(), this.toString(permutations.getPermutations(), "qest"));
//	}
//	
	@Test
	void shouldPopulateForNoLetterToNumberConverions() {
		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "qt");
		permutations.populateWithPermutations();
		assertEquals("Permutations of qest" + System.lineSeparator()
				+ "QT" + System.lineSeparator()
				+ "Qt" + System.lineSeparator()
				+ "qT" + System.lineSeparator()
				+ "qt" + System.lineSeparator(), this.toString(permutations.getPermutations(), "qest"));
	}
//	
//	@Test
//	void shouldPopulateStarterPasswordPermutations() {
//		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "qt");
//		assertEquals("Permutations of qest" + System.lineSeparator()
//				+ "QT" + System.lineSeparator()
//				+ "Qt" + System.lineSeparator()
//				+ "qT" + System.lineSeparator()
//				+ "qt" + System.lineSeparator(), this.toString(permutations.starterPasswordPermutations(new int[] {0}), "qest"));
//	}
	
	public String toString(ArrayList<String> permutations, String password) {
		
		String permutationsString = "Permutations of " + password + System.lineSeparator();
		
		int index = 0;
		for (String permutation : permutations) {
			System.out.println(permutation);
			permutationsString += permutation + System.lineSeparator();
			index++;
		}
		
		return permutationsString;
	}
}

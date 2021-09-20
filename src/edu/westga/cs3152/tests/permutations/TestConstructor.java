package edu.westga.cs3152.tests.permutations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.PasswordPermutations;

class TestConstructor {

	@Test
	void shouldInitializeValues() {
		PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), "hello");
		assertNotNull(permutations.getPermutations());
	}

}

package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestConstructor {

	@Test
	void shouldInitializeEmptyPasswords() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertEquals(0, manager.getNumberOfPasswords());
	}

	@Test
	void shouldNotGetNullKnownPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.getKnownPassword(null);
		});
	}
	
	@Test
	void shouldGetKnownPasswordFromEmptyPasswords() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertEquals(null, manager.getKnownPassword("not exists"));
	}
}

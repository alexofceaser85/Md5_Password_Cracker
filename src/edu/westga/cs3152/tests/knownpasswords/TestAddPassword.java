package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestAddPassword {

	@Test
	void shouldNotAddNullEncryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword(null, "password");
		});
	}
	
	@Test
	void shouldNotAddEmptyEncryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("", "password");
		});
	}
	
	@Test
	void shouldNotAddNullUnencryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("password", null);
		});
	}
	
	@Test
	void shouldNotAddEmptyUnencryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("password", "");
		});
	}
	
	@Test
	void shouldAddOnePassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("hello world", "password");
		
		assertEquals(1, manager.getNumberOfPasswords());
		assertEquals("password", manager.getKnownPassword("hello world"));
	}
	
	@Test
	void shouldAddManyPasswords() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "password");
		manager.addPassword("second", "qwerty");
		manager.addPassword("third", "123456");
		
		assertEquals(3, manager.getNumberOfPasswords());
		assertEquals("password", manager.getKnownPassword("first"));
		assertEquals("qwerty", manager.getKnownPassword("second"));
		assertEquals("123456", manager.getKnownPassword("third"));
	}

}

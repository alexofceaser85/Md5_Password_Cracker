package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.knownpasswords.KnownPasswordManager;

class TestAddPassword {

	@Test
	void shouldNotAddNullEncryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword(null, "password");
		});
	}
	
	@Test
	void shouldNotAddEmptyEncryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("", "password");
		});
	}
	
	@Test
	void shouldNotAddNullUnencryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("password", null);
		});
	}
	
	@Test
	void shouldNotAddEmptyUnencryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addPassword("password", "");
		});
	}
	
	@Test
	void shouldAddOnePassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("hello world", "password");
		
		assertEquals(1, manager.getNumberOfPasswords());
		assertEquals("password", manager.getKnownPassword("hello world"));
	}
	
	@Test
	void shouldAddManyPasswords() {
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

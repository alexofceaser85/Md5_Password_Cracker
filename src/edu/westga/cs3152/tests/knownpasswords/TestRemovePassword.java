package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestRemovePassword {

	@Test
	void shouldNotRemovePasswordFromEmptyManager() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removePassword(null);
		});
	}
	
	@Test
	void shouldNotRemovePasswordFromManagerWithOneNotMatchingPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("hello world", "password");
		
		assertEquals(null, manager.removePassword("no match"));
		assertEquals(1, manager.getNumberOfPasswords());
	}
	
	@Test
	void shouldRemovePasswordFromManagerWithOneMatchingPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("hello world", "password");
		
		assertEquals("password", manager.removePassword("hello world"));
		assertEquals(0, manager.getNumberOfPasswords());
	}
	
	@Test
	void shouldNotRemovePasswordFromManagerWithManyNotMatchingPasswords() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "password");
		manager.addPassword("second", "qwerty");
		manager.addPassword("third", "123456");
		
		assertEquals(null, manager.removePassword("no match"));
		assertEquals(3, manager.getNumberOfPasswords());
	}
	
	@Test
	void shouldRemoveFirstPasswordFromManagerWithManyMatchingPasswords() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "password");
		manager.addPassword("second", "qwerty");
		manager.addPassword("third", "123456");
		
		assertEquals("password", manager.removePassword("first"));
		assertEquals(2, manager.getNumberOfPasswords());
	}
	
	@Test
	void shouldRemoveMiddlePasswordFromManagerWithManyMatchingPasswords() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "password");
		manager.addPassword("second", "qwerty");
		manager.addPassword("third", "123456");
		
		assertEquals("qwerty", manager.removePassword("second"));
		assertEquals(2, manager.getNumberOfPasswords());
	}
	
	@Test
	void shouldRemoveLastPasswordFromManagerWithManyMatchingPasswords() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "password");
		manager.addPassword("second", "qwerty");
		manager.addPassword("third", "123456");
		
		assertEquals("123456", manager.removePassword("third"));
		assertEquals(2, manager.getNumberOfPasswords());
	}
}

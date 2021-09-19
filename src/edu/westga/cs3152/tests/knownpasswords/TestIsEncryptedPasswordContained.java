package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestIsEncryptedPasswordContained {

	@Test
	void shouldNotAllowNullPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.isEncryptedPasswordContained(null);
		});
	}
	
	@Test
	public void shouldReturnFalseForEmptyManager() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithOneNotMatchingItem() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}

	@Test
	public void shouldReturnTrueForManagerWithOneMatchingItem() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertTrue(manager.isEncryptedPasswordContained("first"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithManyNotMatchingItems() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndFirstItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("first"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndMiddleItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("second"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndLastItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("third"));
	}
	
	@Test
	public void shouldReturnFalseForUnencryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isEncryptedPasswordContained("apple"));
	}
}

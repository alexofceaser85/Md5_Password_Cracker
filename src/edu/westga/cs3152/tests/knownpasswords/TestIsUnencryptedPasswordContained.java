package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestIsUnencryptedPasswordContained {

	@Test
	void shouldNotAllowNullPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.isUnencryptedPasswordContained(null);
		});
	}
	
	@Test
	public void shouldReturnFalseForEmptyManager() {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithOneNotMatchingItem() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}

	@Test
	public void shouldReturnTrueForManagerWithOneMatchingItem() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertTrue(manager.isUnencryptedPasswordContained("qwerty"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithManyNotMatchingItems() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndFirstItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("qwerty"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndMiddleItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("apple"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndLastItemMatching() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("123456"));
	}
	
	@Test
	public void shouldReturnFalseForEncryptedPassword() {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isUnencryptedPasswordContained("second"));
	}

}

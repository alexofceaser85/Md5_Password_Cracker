package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestIsUnencryptedPasswordContained {

	@Test
	void shouldNotAllowNullPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.isUnencryptedPasswordContained(null);
		});
	}
	
	@Test
	public void shouldReturnFalseForEmptyManager() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithOneNotMatchingItem() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}

	@Test
	public void shouldReturnTrueForManagerWithOneMatchingItem() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertTrue(manager.isUnencryptedPasswordContained("qwerty"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithManyNotMatchingItems() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isUnencryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndFirstItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("qwerty"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndMiddleItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("apple"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndLastItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isUnencryptedPasswordContained("123456"));
	}
	
	@Test
	public void shouldReturnFalseForEncryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isUnencryptedPasswordContained("second"));
	}

}

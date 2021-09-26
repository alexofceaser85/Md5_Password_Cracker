package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestIsEncryptedPasswordContained {

	@Test
	void shouldNotAllowNullPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.isEncryptedPasswordContained(null);
		});
	}
	
	@Test
	public void shouldReturnFalseForEmptyManager() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithOneNotMatchingItem() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}

	@Test
	public void shouldReturnTrueForManagerWithOneMatchingItem() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		assertTrue(manager.isEncryptedPasswordContained("first"));
	}
	
	@Test
	public void shouldReturnFalseForManagerWithManyNotMatchingItems() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isEncryptedPasswordContained("not match"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndFirstItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("first"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndMiddleItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("second"));
	}
	
	@Test
	public void shouldReturnTrueForManagerWithManyItemsAndLastItemMatching() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertTrue(manager.isEncryptedPasswordContained("third"));
	}
	
	@Test
	public void shouldReturnFalseForUnencryptedPassword() throws IOException {
		KnownPasswordManager manager = new KnownPasswordManager();
		manager.addPassword("first", "qwerty");
		manager.addPassword("second", "apple");
		manager.addPassword("third", "123456");
		assertFalse(manager.isEncryptedPasswordContained("apple"));
	}
}

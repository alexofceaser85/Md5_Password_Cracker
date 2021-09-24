package edu.westga.cs3152.tests.knownpasswords;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestPopulateKnownPasswords {

//	@Test
//	void shouldPopulatePasswordHashMap() throws FileNotFoundException {
//		KnownPasswordManager manager = new KnownPasswordManager();
//		
//		manager.populateKnownPasswords("./knownPasswords.csv");
//		assertEquals("", this.toString(manager));
//	}
//	
//	public String toString(KnownPasswordManager manager) {
//		String passwordString = "";
//		
//		for (String password : manager.getEncryptedPasswords()) {
//			passwordString += manager.getKnownPassword(password) + " " + password + System.lineSeparator();
//		}
//		
//		return passwordString;
//	}
}

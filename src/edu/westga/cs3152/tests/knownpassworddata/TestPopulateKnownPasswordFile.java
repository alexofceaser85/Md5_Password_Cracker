package edu.westga.cs3152.tests.knownpassworddata;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.knownpassworddata.KnownPasswordDataParser;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

class TestPopulateKnownPasswordFile {

//TODO remove test before submission
//	@Test
//	void shouldPopulateFromWorstPasswords() throws IOException {
//		KnownPasswordDataParser.populateKnownPasswordFile("./500-worst-passwords.txt");
//		assertNotEquals("", this.toString("./500-worst-passwords.txt"));
//	}
//
//	public String toString(String fileName) throws FileNotFoundException {
//		File knownPasswords = new File(fileName);
//		Scanner knownPasswordScanner = new Scanner(knownPasswords);
//		String allKnownPasswords = "";
//		
//		while (knownPasswordScanner.hasNextLine()) {
//			allKnownPasswords += knownPasswordScanner.nextLine() + "\n";
//		}
//		
//		knownPasswordScanner.close();
//		return allKnownPasswords;
//	}
}

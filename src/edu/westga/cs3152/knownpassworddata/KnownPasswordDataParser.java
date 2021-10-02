package edu.westga.cs3152.knownpassworddata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.westga.cs3152.hashing.SimpleCrypt;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.permutations.PasswordPermutations;
import edu.westga.cs3152.permutations.StarterPasswordPermutations;

/**
 * Converts a given file of all unencrypted passwords to the known passwords file
 * 
 * @author Alex DeCesare
 * @version 21-September-2021
 */
public final class KnownPasswordDataParser {

	private static final String KNOWN_PASSWORD_FILE = "./knownPasswords.csv";
	
	public static void populateKnownPasswordFile(String fileNameToPopulate, KnownPasswordManager manager, FileWriter knownFileWriter) throws IOException {
		File passwordFile = new File(fileNameToPopulate);
		Scanner passwordScanner = new Scanner(passwordFile);
		SimpleCrypt crypt = new SimpleCrypt();
		while (passwordScanner.hasNextLine()) {
			PasswordPermutations permutations = new PasswordPermutations(manager, passwordScanner.nextLine(), knownFileWriter);
			permutations.populateLetterToNumberPermutations(crypt);
		}

		StarterPasswordPermutations starterPermutations = new StarterPasswordPermutations(new int[] {0}, manager, knownFileWriter);
		starterPermutations.calculateStarterPasswordPermutations(crypt);
		passwordScanner.close();
	}
	
	public static void populatePasswordManager(String fileNameToPopulateFrom, KnownPasswordManager manager) throws IOException {
		File knownPasswordFile = new File(KNOWN_PASSWORD_FILE);
		Scanner knownPasswordScanner = new Scanner(knownPasswordFile);
		
		while (knownPasswordScanner.hasNextLine()) {
			String knownPasswordLine = knownPasswordScanner.nextLine();
			String[] knownPasswordData = knownPasswordLine.split(",");
			
			String unencryptedPassword = knownPasswordData[0];
			String encryptedPassword = knownPasswordData[1];
			
			manager.addPassword(encryptedPassword, unencryptedPassword);
		}

		knownPasswordScanner.close();
	}
}

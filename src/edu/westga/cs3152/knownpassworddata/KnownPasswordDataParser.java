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
	
	public static void populateKnownPasswordFile(String fileNameToPopulate) throws IOException {
		File passwordFile = new File(fileNameToPopulate);
		File knownPasswordFile = new File(KNOWN_PASSWORD_FILE);
		Scanner passwordScanner = new Scanner(passwordFile);
		
		Scanner knownPasswordScanner = new Scanner(knownPasswordFile);
		FileWriter knownPasswordWriter = new FileWriter(knownPasswordFile);
		
		if (knownPasswordScanner.hasNextLine()) {
			passwordScanner.close();
			knownPasswordScanner.close();
			knownPasswordWriter.close();
			return;
		}

		SimpleCrypt crypt = new SimpleCrypt();
		ArrayList<String> starterPasswords = StarterPasswordPermutations.starterPasswordPermutations(new int[] {0});
		while (passwordScanner.hasNextLine()) {
			String knownPassword = passwordScanner.nextLine();
			knownPasswordWriter.append(knownPassword + "," + crypt.generateHash(knownPassword) + "\n");
			
			PasswordPermutations permutations = new PasswordPermutations(new KnownPasswordManager(), knownPassword);
			permutations.populateWithPermutations();
			
			for (String permutation : permutations.getPermutations()) {
				knownPasswordWriter.append(permutation + "," + crypt.generateHash(permutation) + "\n");
			}
		}
		
		for (String starterPassword : starterPasswords) {
			knownPasswordWriter.append(starterPassword + "," + crypt.generateHash(starterPassword) + "\n");
		}
		
		knownPasswordScanner.close();
		knownPasswordWriter.close();
		passwordScanner.close();
	}
	
}

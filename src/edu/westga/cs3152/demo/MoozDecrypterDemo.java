package edu.westga.cs3152.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;
import edu.westga.cs3152.passwordvariationdata.CharacterVariations;
import edu.westga.cs3152.permutations.PasswordPermutations;
import edu.westga.cs3152.permutations.StarterPasswordPermutations;

/**
 * The demo for the Mooz password decrypter
 * 
 * @author Alex DeCesare
 * @version 02-October-2021
 */
public class MoozDecrypterDemo {

	private static final String STARTING_PASSWORD_SEARCH_MESSAGE = "Starting password search";
	private static final String PASSWORD_SEARCH_FINISHED_MESSAGE = "Password search finished, decrypted passwords are found at ";
	private static final String UNABLE_TO_FIND_PASSWORD_MESSAGE = "UNABLE TO FIND PASSWORD";
	private static final String OUTPUT_FILE = "./moozDecryptedPasswords.csv";
	private static final String INPUT_FILE = "./passwordData.csv";
	private static final String WORST_PASSWORD_FILE = "./500-worst-passwords.txt";

	/**
	 * The entry point into the Mooz password decrypter
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			System.out.println(STARTING_PASSWORD_SEARCH_MESSAGE);
			KnownPasswordManager manager = new KnownPasswordManager();
			populateKnownPasswordFile(manager);

			File outputFile = new File(OUTPUT_FILE);
			FileWriter outputFileWriter = new FileWriter(outputFile);
			File inputFile = new File(INPUT_FILE);
			Scanner inputFileScanner = new Scanner(inputFile);
			
			while (inputFileScanner.hasNextLine()) {
				findPassword(manager, outputFileWriter, inputFileScanner);
			}
			
			inputFileScanner.close();
			outputFileWriter.close();
			System.out.println(PASSWORD_SEARCH_FINISHED_MESSAGE + OUTPUT_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void findPassword(KnownPasswordManager manager, FileWriter outputFileWriter,
			Scanner inputFileScanner) throws IOException {
		String[] data = inputFileScanner.nextLine().split(",");
		String userName = data[0];
		String password = data[1];

		if (manager.isEncryptedPasswordContained(password)) {
			outputFileWriter.append(userName + "," + password + "," + manager.getKnownPassword(password) + System.lineSeparator());
		} else {
			outputFileWriter.append(userName + "," + password + "," + UNABLE_TO_FIND_PASSWORD_MESSAGE + System.lineSeparator());
		}
	}
	
	private static void populateKnownPasswordFile(KnownPasswordManager manager) throws IOException {
		File passwordFile = new File(WORST_PASSWORD_FILE);
		Scanner passwordScanner = new Scanner(passwordFile);
		while (passwordScanner.hasNextLine()) {
			PasswordPermutations permutations = new PasswordPermutations(manager, passwordScanner.nextLine(), new CharacterVariations());
			permutations.populateLetterVariations();
		}
		passwordScanner.close();
		
		StarterPasswordPermutations starterPermutations = new StarterPasswordPermutations(manager);
		starterPermutations.calculateStarterPasswordPermutations();
	}

}

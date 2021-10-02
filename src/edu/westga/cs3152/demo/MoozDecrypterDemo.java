package edu.westga.cs3152.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs3152.knownpassworddata.KnownPasswordDataParser;
import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

public class MoozDecrypterDemo {

	private static final String OUTPUT_FILE = "./moozDecryptedPasswords.csv";
	private static final String INPUT_FILE = "./passwordData.csv";
	private static final String KNOWN_PASSWORD_FILE = "./knownPasswords.csv";
	private static final String WORST_PASSWORD_FILE = "./500-worst-passwords.txt";
	
	private static final boolean SHOULD_GENERATE_NEW_KNOWN_PASSWORDS = true;
	
	public static void main(String[] args) {
		try {
			KnownPasswordManager manager = new KnownPasswordManager();
			if (SHOULD_GENERATE_NEW_KNOWN_PASSWORDS) {
				generateNewKnownPasswords(manager);
			} else {
				KnownPasswordDataParser.populatePasswordManager(KNOWN_PASSWORD_FILE, manager);
			}
			
			File outputFile = new File(OUTPUT_FILE);
			FileWriter outputFileWriter = new FileWriter(outputFile);
			File inputFile = new File(INPUT_FILE);
			Scanner inputFileScanner = new Scanner(inputFile);
			
			while (inputFileScanner.hasNextLine()) {
				findPassword(manager, outputFileWriter, inputFileScanner);
			}
			
			inputFileScanner.close();
			outputFileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateNewKnownPasswords(KnownPasswordManager manager) throws IOException {
		File knownPasswordFile = new File(KNOWN_PASSWORD_FILE);
		FileWriter knownFileWriter = new FileWriter(knownPasswordFile);
		KnownPasswordDataParser.populateKnownPasswordFile(WORST_PASSWORD_FILE, manager, knownFileWriter);
		knownFileWriter.close();
	}
	
	private static void findPassword(KnownPasswordManager manager, FileWriter outputFileWriter,
			Scanner inputFileScanner) throws IOException {
		String[] data = inputFileScanner.nextLine().split(",");
		String userName = data[0];
		String password = data[1];

		if (manager.isEncryptedPasswordContained(password)) {
			outputFileWriter.append(userName + "," + password + "," + manager.getKnownPassword(password) + System.lineSeparator());
		} else {
			outputFileWriter.append(userName + "," + password + "," + "UNABLE TO FIND PASSWORD" + System.lineSeparator());
		}
	}

}

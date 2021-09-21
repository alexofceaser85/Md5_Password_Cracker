package edu.westga.cs3152.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs3152.passwordmanagers.KnownPasswordManager;

public class moozDecrypterDemo {

	private final static String OUTPUT_FILE = "./moozDecryptedPasswords.csv";
	private final static String INPUT_FILE = "./passwordData.csv";
	private final static String KNOWN_PASSWORD_FILE = "./knownPasswords.csv";
	
	public static void main(String[] args) {
		try {
			File outputFile = new File(OUTPUT_FILE);
			FileWriter outputFileWriter = new FileWriter(outputFile);
			File inputFile = new File(INPUT_FILE);
			Scanner inputFileScanner = new Scanner(inputFile);
			
			KnownPasswordManager manager = new KnownPasswordManager();
			manager.populateKnownPasswords(KNOWN_PASSWORD_FILE);
			
			while (inputFileScanner.hasNextLine()) {
				String[] data = inputFileScanner.nextLine().split(",");
				
				String userName = data[0];
				String password = data[1];
				
				System.out.println(manager.isUnencryptedPasswordContained("c0lleg3"));
				
				if (manager.isEncryptedPasswordContained(password)) {
					outputFileWriter.append(userName + "," + password + "," + manager.getKnownPassword(password) + System.lineSeparator());
				} else {
					outputFileWriter.append(userName + "," + password + "," + "UNABLE TO FIND PASSWORD" + System.lineSeparator());
				}
			}
			
			inputFileScanner.close();
			outputFileWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package edu.westga.cs3152.datatier;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Class PasswordHashData
 * 
 * @author CS3152
 * @version Fall 2021
 */
public class PasswordHashData {
	private File passwordHashFile;
	private String[] username;
	private String[] passwordHash;

	/**
	 * Instantiates a PasswordHashData object. The usernames and password hashes are
	 * loaded from the specified file.
	 * 
	 * @pre filename != null and !filename.isEmpty()
	 * @post size() = number of usernames with given password hashes and getUser(i)
	 *       is the ith username and getHash(i) is the password hash of the ith
	 *       username for i = 0, 1, 2, ..., size() - 1
	 * @param filename the name of the file from which the data is loaded
	 */
	public PasswordHashData(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("filename cannot be null");
		}
		if (filename.isEmpty()) {
			throw new IllegalArgumentException("filename cannot be empty");
		}
		this.passwordHashFile = new File(filename);
		this.loadFile();
	}

	/**
	 * Gets the username at the specified index 
	 * 
	 * @pre index >= 0 and index < size()
	 * @post none
	 * @param index - index of the user
	 * @return the username of the ith user
	 * @throws ArrayIndexOutOfBoundsException if passed in index is less than 0 or
	 *                                        greater than or equal to size()
	 */
	public String getUsername(int index) {
		return this.username[index];
	}
	
	/**
	 * Gets all usernames read previously from file
	 * 
	 * @pre none
	 * @post none
	 * @return an array with the usernames
	 */
	public String[] getUsernames() {
		return this.username;
	}

	/**
	 * Gets the password hash of the password with the specified index
	 * 
	 * @pre index >= 0 and index < size()
	 * @post none
	 * @param index - index of the password
	 * @return the password hash of the ith user
	 * @throws ArrayIndexOutOfBoundsException if passed in index is less than 0 or
	 *                                        greater than or equal to size()
	 */
	public String getHash(int index) {
		return this.passwordHash[index];
	}
	
	/**
	 * Gets all password hashes read previously from file 
	 * 
	 * @pre none
	 * @post none
	 * @return an array with the password hashes
	 */
	public String[] getHashes() {
		return this.passwordHash;
	}
	
	/**
	 * Gets the number of usernames
	 * 
	 * @pre none
	 * @post none
	 * @return the number of usernames
	 */
	public int size() {
		return this.username.length;
	}
	
	private boolean loadFile() {
		try {
			this.allocateMemoryForPasswordData();
			this.loadPassworData();
		} catch (IOException exc) {
			this.logFileError(exc);
			return false;
		}
		return true;
	}

	private void allocateMemoryForPasswordData() throws IOException {
		int lineCount = 0;
		try (Scanner input = new Scanner(this.passwordHashFile)) {
			while (input.hasNextLine()) {
				input.nextLine();
				lineCount++;
			}
		}
		this.username = new String[lineCount];
		this.passwordHash = new String[lineCount];
	}

	private void loadPassworData() throws IOException {
		int index = 0;
		try (Scanner input = new Scanner(this.passwordHashFile)) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] userInfo = line.split(",");
				this.username[index] = userInfo[0];
				this.passwordHash[index] = userInfo[1];
				index++;
			}
		}
	}

	private void logFileError(IOException exc) {
		System.err.println("Could not read password hashes from " + this.passwordHashFile);
		System.err.println(exc.getMessage());
	}
}
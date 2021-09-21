package edu.westga.cs3152.passwordmanagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import edu.westga.cs3152.errormessages.KnownPasswordManagerErrorMessages;
import edu.westga.cs3152.hashing.SimpleCrypt;
import edu.westga.cs3152.permutations.PasswordPermutations;

/**
 * Holds the known passwords
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */

public class KnownPasswordManager {

	private HashMap<String, String> knownPasswords;
	
	/**
	 * Creates a new known password manager
	 * 
	 * @precondition none
	 * @postcondition this.knownPasswords == new HashMap<String, String>
	 */
	public KnownPasswordManager() {
		this.knownPasswords = new HashMap<String, String>();
	}
	
	/**
	 * Gets the unencrypted version of the encrypted password
	 * 
	 * @precondition encryptedPassword != null
	 * @postcondition none
	 * 
	 * @param encryptedPassword the encrypted password to use to get the unencrypted version
	 * @return the unencrypted version of the encrypted password or null if the encrypted password is not contained
	 */
	public String getKnownPassword(String encryptedPassword) {
		if (encryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_GET_NULL_KNOWN_PASSWORD);
		}
		
		return this.knownPasswords.get(encryptedPassword);
	}
	
	public Set<String> getEncryptedPasswords() {
		return this.knownPasswords.keySet();
	}

	/**
	 * Gets the number of passwords 
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of passwords
	 */
	public int getNumberOfPasswords() {
		return this.knownPasswords.size();
	}
	
	/**
	 * Checks if the encrypted password is contained
	 * 
	 * @precondition unencryptedPassword != null
	 * @postcondition none
	 * 
	 * @param encryptedPassword the encrypted password to check if it is contained
	 * @return if the encrypted password is contained
	 */
	public boolean isEncryptedPasswordContained(String encryptedPassword) {
		if (encryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_NULL_ENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		
		return this.knownPasswords.containsKey(encryptedPassword);
	}
	
	/**
	 * Checks if the unencrypted password is contained
	 * 
	 * @precondition unencryptedPassword != null
	 * @postcondition none
	 * 
	 * @param unencryptedPassword the unencrypted password to check if it is contained
	 * @return if the unencrypted password is contained
	 */
	public boolean isUnencryptedPasswordContained(String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_NULL_UNENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		
		return this.knownPasswords.containsValue(unencryptedPassword);
	}
	
	/**
	 * Adds a password to the known passwords
	 * 
	 * @precondition 
	 * unencryptedPassword != null 
	 * && unencryptedPassword.isEmpty == false 
	 * && encryptedPassword != null
	 * && encryptedPassword.isEmpty == false
	 * @postcondition this.knownPasswords.size == this.knownPasswords@prev + 1
	 * 
	 * @param encryptedPassword the encrypted password to add
	 * @param unencryptedPassword the unencrypted password to add
	 */
	public void addPassword(String encryptedPassword, String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_NULL);
		}
		if (unencryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_EMPTY);
		}
		if (encryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_ENCRYPTED_PASSWORD_IS_NULL);
		}
		if (encryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_ENCRYPTED_PASSWORD_IS_EMPTY);
		}
		
		this.knownPasswords.put(encryptedPassword, unencryptedPassword);
	}
	
	/**
	 * Removes a password from the known passwords
	 * 
	 * @precondition unencryptedPassword != null
	 * @postcondition this.knownPasswords.size == this.knownPasswords@prev - 1
	 * 
	 * @param unencryptedPassword the password to remove
	 * @return the password that was removed
	 */
	public String removePassword(String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_REMOVE_NULL_PASSWORD);
		}
		return this.knownPasswords.remove(unencryptedPassword);
	}
	
	/**
	 * Populates the known password data from the given password dictionary
	 * 
	 * @precondition fileNameOfKnownPasswords != null && fileNameOfKnownPasswords.isEmpty() == false
	 * @postcondition this.knownPasswords.size
	 * 
	 * @param fileNameOfKnownPasswords
	 * @throws FileNotFoundException if the file does not exist
	 */
	public void populateKnownPasswords(String fileNameOfKnownPasswords) throws FileNotFoundException {
		//TODO add preconditions
		
		File knownPasswordFile = new File(fileNameOfKnownPasswords);
		Scanner knownPasswordScanner = new Scanner(knownPasswordFile);
		
		while (knownPasswordScanner.hasNextLine()) {
			String[] passwords = knownPasswordScanner.nextLine().split(",");
			this.addPassword(passwords[1], passwords[0]);
		}
		
		knownPasswordScanner.close();
	}
}

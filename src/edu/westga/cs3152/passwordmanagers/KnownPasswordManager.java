package edu.westga.cs3152.passwordmanagers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import edu.westga.cs3152.errormessages.KnownPasswordManagerErrorMessages;
import edu.westga.cs3152.hashing.SimpleCrypt;

/**
 * Holds the known passwords
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */

public class KnownPasswordManager {

	private HashMap<String, String> knownPasswords;
	private SimpleCrypt crypt;
	
	/**
	 * Creates a new known password manager
	 * @throws IOException 
	 * 
	 * @precondition none
	 * @postcondition this.knownPasswords == new HashMap<String, String>
	 */
	public KnownPasswordManager() {
		this.knownPasswords = new HashMap<String, String>();
		this.crypt = new SimpleCrypt();
	}
	
	/**
	 * Gets the unencrypted version of the encrypted password
	 * 
	 * @precondition encryptedPassword != null && encryptedPassword.isEmpty() == false
	 * @postcondition none
	 * 
	 * @param encryptedPassword the encrypted password to use to get the unencrypted version
	 * @return the unencrypted version of the encrypted password or null if the encrypted password is not contained
	 */
	public String getKnownPassword(String encryptedPassword) {
		if (encryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_GET_NULL_KNOWN_PASSWORD);
		}
		if (encryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_GET_EMPTY_KNOWN_PASSWORD);
		}
		
		return this.knownPasswords.get(encryptedPassword);
	}
	
	/**
	 * Gets the encrypted passwords
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the encrypted passwords
	 */
	public Set<String> getEncryptedPasswords() {
		return this.knownPasswords.keySet();
	}
	
	/**
	 * Gets the unencrypted passwords
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the unencrypted passwords
	 */
	public Collection<String> getUnencryptedPasswords() {
		return this.knownPasswords.values();
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
	 * @precondition encryptedPassword != null && encryptedPassword.isEmpty == false
	 * @postcondition none
	 * 
	 * @param encryptedPassword the encrypted password to check if it is contained
	 * @return if the encrypted password is contained
	 */
	public boolean isEncryptedPasswordContained(String encryptedPassword) {
		if (encryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_NULL_ENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		if (encryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_EMPTY_ENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		
		return this.knownPasswords.containsKey(encryptedPassword);
	}
	
	/**
	 * Checks if the unencrypted password is contained
	 * 
	 * @precondition unencryptedPassword != null && unencryptedPassword.isEmpty == false
	 * @postcondition none
	 * 
	 * @param unencryptedPassword the unencrypted password to check if it is contained
	 * @return if the unencrypted password is contained
	 */
	public boolean isUnencryptedPasswordContained(String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_NULL_UNENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		if (unencryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_CHECK_IF_EMPTY_UNENCRYPTED_PASSWORD_IS_CONTAINED);
		}
		
		return this.knownPasswords.containsValue(unencryptedPassword);
	}
	
	/**
	 * Adds a known password to the password manager
	 * 
	 * @precondition 
	 * unencryptedPassword != null 
	 * && unencryptedPassword.isEmpty == false 
	 * @postcondition this.knownPasswords.size == this.knownPasswords@prev + 1
	 * 
	 * @param unencryptedPassword the unencrypted password to add
	 * @throws IOException 
	 */
	
	public void addPassword(String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_NULL);
		}
		if (unencryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_EMPTY);
		}
		
		this.knownPasswords.put(this.crypt.generateHash(unencryptedPassword), unencryptedPassword);
	}
	
	/**
	 * Removes a password from the known passwords
	 * 
	 * @precondition unencryptedPassword != null && unencryptedPassword.isEmpty == false
	 * @postcondition this.knownPasswords.size == this.knownPasswords@prev - 1
	 * 
	 * @param unencryptedPassword the password to remove
	 * @return the password that was removed
	 */
	public String removePassword(String unencryptedPassword) {
		if (unencryptedPassword == null) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_REMOVE_NULL_PASSWORD);
		}
		if (unencryptedPassword.isEmpty()) {
			throw new IllegalArgumentException(KnownPasswordManagerErrorMessages.CANNOT_REMOVE_EMPTY_PASSWORD);
		}
		return this.knownPasswords.remove(unencryptedPassword);
	}
}

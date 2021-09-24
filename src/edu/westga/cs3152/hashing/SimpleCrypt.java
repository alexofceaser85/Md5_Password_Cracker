package edu.westga.cs3152.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class SimpleCrypt
 * 
 * @author CS3152
 * @version Fall 2021
 */
public class SimpleCrypt {
	private MessageDigest md;

	/**
	 * Instantiates a new SimpleCrypt object
	 */
	public SimpleCrypt() {
		try {
			this.md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException exc) {
			System.err.println("Could not create message digest for MD5");
			System.err.println(exc.getMessage());
		}
	}

	/**
	 * Encrypts the specified password and returns the hash
	 * 
	 * @param password the password to be encrypted
	 * @return the hash of the specified password
	 */
	public String generateHash(String password) {
		String hash = null;
		this.md.update(password.getBytes());
		byte[] bytes = this.md.digest();
		StringBuilder stringBuilder = new StringBuilder();
		for (int idx = 0; idx < bytes.length; idx++) {
			stringBuilder.append(Integer.toString((bytes[idx] & 0xff) + 0x100, 16).substring(1));
		}
		hash = stringBuilder.toString();
		return hash;
	}

	/**
	 * Checks if the specified password hashes to the specified hash
	 * 
	 * @param password the password to be checked
	 * @param hash the hash of the actual password
	 * @return true if the specified password has the specified hash
	 */
	public boolean checkPassword(String password, String hash) {
		return hash.equals(this.generateHash(password));
	}
}

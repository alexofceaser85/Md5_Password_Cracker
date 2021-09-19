package edu.westga.cs3152.demo;

import org.mindrot.jbcrypt.BCrypt;

import edu.westga.cs3152.datatier.PasswordHashData;

/**
 * Class DemoBCrypt
 * 
 * The purpose of this class is to demonstrate a better password hashing method
 * than the one provided in Demo.java. Do not use the demonstrated hash method
 * and the associated file passwordDataBCrypt.csv in this project. It will be too
 * time consuming for password cracking.
 * 
 * @author CS3152
 * @version Fall 2021
 */
public class DemoBCrypt {

	/**
	 * A demo of password hashing and password checking using the OpenBSD's Blowfish
	 * algorithm with salt
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		PasswordHashData pwData = new PasswordHashData("passwordDataBCrypt.csv");

		for (int idx = 0; idx < 3; idx++) {
			System.out.println();
			System.out.println("Guessing a password for user " + pwData.getUsername(idx));
			String passwordHash = pwData.getHash(idx);
			guessPasswordBCrypt("monkey", passwordHash);
		}

		for (int idx = 1; idx <= 3; idx++) {
			System.out.println();
			String passwordHash = generatePasswordHash("monkey");
			System.out.println("Password hash of monkey with salt: " + passwordHash);
			guessPasswordBCrypt("monkey", passwordHash);
		}
	}

	/**
	 * Determines whether the guessed password is the same password as the one
	 * encoded in the specified password hash
	 * 
	 * @param hash            the hash of the actual password of the specified user
	 * @param guessedPassword the guessed password of the specified user
	 */
	public static void guessPasswordBCrypt(String guessedPassword, String hash) {
		if (BCrypt.checkpw(guessedPassword, hash)) {
			System.out.println("The password is " + guessedPassword);
		} else {
			System.out.println("The password is NOT " + guessedPassword);
		}
	}

	/**
	 * Generates the encrypted password hash of the specified password
	 * 
	 * @param password the password to be encrypted
	 * @return the encrypted password
	 */
	public static String generatePasswordHash(String password) {
		String salt = BCrypt.gensalt();
		String passwordHash = BCrypt.hashpw(password, salt);
		return passwordHash;
	}
}

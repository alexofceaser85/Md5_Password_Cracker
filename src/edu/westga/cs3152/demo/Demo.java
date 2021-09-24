package edu.westga.cs3152.demo;

import edu.westga.cs3152.datatier.PasswordHashData;
import edu.westga.cs3152.hashing.SimpleCrypt;

/**
 * Class Demo
 * 
 * @author CS3152
 * @version Fall 2021
 */
public class Demo {
	
	/**
	 * A demo of password checking using MD5 without salt
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		PasswordHashData pwData = new PasswordHashData("passwordData.csv");
		SimpleCrypt crypt = new SimpleCrypt();

		for (int idx = 0; idx < 3; idx++) {
			System.out.println();
			System.out.println("Guessing a password for user " + pwData.getUsername(idx));
			String passwordHash = pwData.getHash(idx);
			if (crypt.checkPassword("monkey", passwordHash)) {
				System.out.println("The password is monkey.");
			} else {
				System.out.println("The password is NOT monkey");
			}
		}
	}
}

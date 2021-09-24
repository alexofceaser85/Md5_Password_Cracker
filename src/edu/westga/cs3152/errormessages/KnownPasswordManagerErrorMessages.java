package edu.westga.cs3152.errormessages;

/**
 * Holds the error messages for the known password manager
 * 
 * @author Alex DeCesare
 * @version 19-September-2021
 */
public final class KnownPasswordManagerErrorMessages {

	public static final String CANNOT_GET_NULL_KNOWN_PASSWORD = "Cannot get a known password is the encrypted version to get is null";
	public static final String CANNOT_CHECK_IF_NULL_ENCRYPTED_PASSWORD_IS_CONTAINED = "Cannot check if the encrypted password is contained if it is null";
	public static final String CANNOT_CHECK_IF_NULL_UNENCRYPTED_PASSWORD_IS_CONTAINED = "Cannot check if the unencrypted password is contained if it is null";
	
	public static final String CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_NULL = "Cannot add a password if the unencrypted password is null";
	public static final String CANNOT_ADD_PASSWORD_IF_UNENCRYPTED_PASSWORD_IS_EMPTY = "Cannot add a password if the unecnrypted password is empty";
	public static final String CANNOT_ADD_PASSWORD_IF_ENCRYPTED_PASSWORD_IS_NULL = "Cannot add a password if the encrypted password is null";
	public static final String CANNOT_ADD_PASSWORD_IF_ENCRYPTED_PASSWORD_IS_EMPTY = "Cannot add a password if the encrypted password is empty";
	
	public static final String CANNOT_REMOVE_NULL_PASSWORD = "Cannot remove a password if it is null";
}

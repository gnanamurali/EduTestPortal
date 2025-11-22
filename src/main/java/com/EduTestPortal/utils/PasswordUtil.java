package com.EduTestPortal.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.Objects;
import java.util.Arrays;

public final class PasswordUtil
{
	 private PasswordUtil()
	 {
	        throw new AssertionError("Utility class should not be instantiated.");
	   
	 }
	 private static final int COST = 12;
	 
	 public static String hash(String plain) 
	 {
		    Objects.requireNonNull(plain, "Password cannot be null");
		    char[] chars = plain.toCharArray();
		    try 
		    {
		       
		        return BCrypt.withDefaults().hashToString(COST, chars);
		    } 
		    finally
		    {
		       
		        Arrays.fill(chars, '\0');
		    }
		}
	 
	 public static boolean verify(String plain, String storedHash) {
		    Objects.requireNonNull(plain, "Password cannot be null");
		    Objects.requireNonNull(storedHash, "Stored hash cannot be null");

		    char[] chars = plain.toCharArray();

		    try {
		        return BCrypt.verifyer().verify(chars, storedHash).verified;
		    } finally {
		        Arrays.fill(chars, '\0');
		    }
		}





}

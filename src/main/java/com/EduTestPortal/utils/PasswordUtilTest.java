package com.EduTestPortal.utils;

public class PasswordUtilTest {
    public static void main(String[] args) {
        String password = "Gnana";

        // Step 1: Hash the password
        String hash = PasswordUtil.hash(password);
        System.out.println("Generated Hash: " + hash);
        System.out.println("Length: " + hash.length());

        // Step 2: Test correct password
        boolean match1 = PasswordUtil.verify("Gnana", hash);
        System.out.println("Correct password matches? " + match1);

        // Step 3: Test wrong password
        boolean match2 = PasswordUtil.verify("wrongpass", hash);
        System.out.println("Wrong password matches? " + match2);
    }
}

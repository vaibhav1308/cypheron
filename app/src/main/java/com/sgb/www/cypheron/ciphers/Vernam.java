package com.sgb.www.cypheron.ciphers;

public class Vernam {

    public String encrypt(String str, String key){

        String s1 = str.toUpperCase();
        String s2 = key.toUpperCase();
        String encrypted = "";
        for (int i = 0; i < s1.length(); i++) {
            int c = s1.charAt(i);
            int p = s2.charAt(i);
            c = (c + p);
            if (c > 'Z') {
                c = c - 91;
            }
            if (c < 'A') {
                c = c + 26;
            }
            encrypted += (char) c;
        }
        return encrypted;

    }
    public String decrypt(String str, String key){

        String s1 = str.toUpperCase();
        String s2 = key.toUpperCase();
        String decrypted = "";
        for (int i = 0; i < str.length(); i++) {
            int c = s1.charAt(i);
            int p = s2.charAt(i);
            c = (c - p)+26;
            if (c < 'A') {
                c = c + 65;
            }
            if (c > 'Z') {
                c = c - 26;
            }
            decrypted += (char) c;
        }
        return decrypted;

    }
}

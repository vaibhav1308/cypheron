package com.sgb.www.cypheron.ciphers;

public class Vigenere {

    static String generateKey(String str, String key)
    {
        int x = str.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }

    public String encrypt(String str, String keyword){

        String cipher_text="";
        String key = generateKey(str, keyword);
        for (int i = 0; i < str.length(); i++)
        {
            // converting in range 0-25
            int x = (str.charAt(i) + key.charAt(i)) %26;

            // convert into alphabets(ASCII)
            x += 'A';
            cipher_text += (char)(x);
        }
        return cipher_text;
    }

    public String decrypt(String str, String keyword){

        String orig_text="";
        String key = generateKey(str, keyword);

        for (int i = 0 ; i < str.length() &&
                i < key.length(); i++)
        {
            // converting in range 0-25
            int x = (str.charAt(i) - key.charAt(i) + 26) %26;

            // convert into alphabets(ASCII)
            x += 'A';
            orig_text += (char)(x);
        }
        return orig_text;
    }
}

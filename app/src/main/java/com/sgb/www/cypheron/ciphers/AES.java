package com.sgb.www.cypheron.ciphers;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public  class AES {

    public String encrypt(String s, String key )  {
        try {
            String encp = AESCrypt.encrypt(key,s);
            return encp;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String decrypt(String s,String key){
        try {
            String decp = AESCrypt.decrypt(key,s);
            return decp;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return "";
    }
}

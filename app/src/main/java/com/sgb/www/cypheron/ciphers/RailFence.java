package com.sgb.www.cypheron.ciphers;

public class RailFence {

    public String encrypt(String s, String key){


        int depth = Integer.parseInt(key),
                r = depth, len = s.length(),
                c = len / depth, k = 0;

        char[][] mat = new char[r][c];
        StringBuilder cipherText= new StringBuilder();

        for(int i = 0; i < c; i++) {
            for(int j = 0; j < r; j++) {
                if(k != len)
                    mat[j][i] = s.charAt(k++);
                else
                    mat[j][i]='X';
            }
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                cipherText.append(mat[i][j]);
            }
        }
        return cipherText.toString();
    }

    public String decrypt(String s, String key) {

        int depth = Integer.parseInt(key),
                r = depth,len = s.length(),
                c = len / depth, k = 0;

        char[][] mat =new char[r][c];
        StringBuilder plainText= new StringBuilder();

        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                mat[i][j]=s.charAt(k++);
            }
        }
        for(int i=0;i< c;i++)
        {
            for(int j = 0; j < r; j++)
            {
                plainText.append(mat[j][i]);
            }
        }
        return plainText.toString();
    }
}

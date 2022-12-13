package com.sgb.www.cypheron.ciphers;

public class Atbash {

    private char p[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char ch[] = {'Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A'};

    public String encrypt(String s){

        s.toUpperCase();
        char[] c = new char[(s.length())];
        for (int i = 0; i < s.length(); i++){

            for(int j = 0; j < 26; j++){
                if (p[j] == s.charAt(i)){
                    c[i] = ch[j];
                    break;
                }
                else if (s.charAt(i) == ' '){
                    c[i] = s.charAt(i);
                    break;
                }
            }
        }
        return(new String(c));
    }
    public String decrypt(String s){


        char[] p1 = new char[(s.length())];
        for (int i = 0; i < s.length(); i++){

            for(int j = 0; j < 26; j++){

                if(ch[j] == s.charAt(i)){
                    p1[i] = p[j];
                    break;
                }
                else if (s.charAt(i) == ' '){
                    p1[i] += s.charAt(i);
                    break;
                }
            }
        }
        return(new String(p1));
    }
}

package com.sgb.www.cypheron.ciphers;

public class Caesar {
    int shift=2,i,n;
    String str;
    String str1="";
    String str2="";
    char ch3,ch4;

    public String encrypt(String s){

        str = s.toLowerCase();
        n = s.length();
        char[] ch1 =s.toCharArray();

        for(i=0;i<n;i++){
            if(Character.isLetter(ch1[i])){
                ch3=(char)(((int)ch1[i]+shift-97)%26+97);
                str1 += ch3;
            }
            else if(ch1[i]==' '){
                str1 += ch1[i];
            }
        }
        return (str1);
    }

    public String decrypt(String s){

        char[] ch2 =s.toCharArray();

        for(i=0;i<s.length();i++){
            if(Character.isLetter(ch2[i])){
                if(((int)ch2[i]-shift)<97) {
                    ch4=(char)(((int)ch2[i]-shift-97+26)%26+97);
                }
                else{
                    ch4=(char)(((int)ch2[i]-shift-97)%26+97);
                }
                str2+=ch4;
            }
            else if(ch2[i]==' '){
                str2=str2+ch2[i];
            }
        }
        return (str2);
    }
}

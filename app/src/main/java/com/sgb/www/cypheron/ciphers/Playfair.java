package com.sgb.www.cypheron.ciphers;

import android.animation.TypeConverter;

public class Playfair {

    public static char[][] keymat = new char[5][5];
    public static String trans = "J";
    public static char subs = 'X';

    private static int decrem(int pos) {
        if (pos < 0) {
            return pos + 5;
        } else {
            return pos;
        }
    }

    private static int[] srch(char c) {
        int i, j;
        int[] pos = new int[2];
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                if (keymat[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        return pos;
    }

    public String encrypt(String s, String key){

        int[] pos1,pos2;
        char c1,c2;

        for (int i = 0; i < s.length(); i++){

            c1 = s.charAt(i);
            c2 = key.charAt(i);

            String frag;
            pos1 = srch(c1);
            pos2 = srch(c2);

            if (pos1[0] == pos2[0]) { //condition for same row
                c1 = keymat[pos1[0]][(pos1[1] + 1) % 5];
                c2 = keymat[pos2[0]][(pos2[1] + 1) % 5];
                frag = ("" + s + key + "");
            }

            else if (pos1[1] == pos2[1]) { //condition for same column
                c1 = keymat[(pos1[0] + 1) % 5][pos1[1]];
                c2 = keymat[(pos2[0] + 1) % 5][pos2[1]];
                frag = ("" + s + key + "");
            }

            else { //condition for different row & column
                c1 = keymat[pos2[0]][pos1[1]];
                c2 = keymat[pos1[0]][pos2[1]];
                frag = ("" + s + key + "");
            }
        }
        return "";
    }
    public String decrypt(String s, String key) {

        int[] pos1, pos2;
        char c1, c2;

        for (int i = 0; i < s.length(); i++) {

            c1 = s.charAt(i);
            c2 = key.charAt(i);

            String frag;
            pos1 = srch(c1);
            pos2 = srch(c2);

            if (pos1[0] == pos2[0]) { //condition for same row
                c1 = keymat[pos1[0]][decrem(pos1[1] - 1) % 5];
                c2 = keymat[pos2[0]][decrem(pos2[1] - 1) % 5];
                frag = ("" + s + key + "");
            }

            else if (pos1[1] == pos2[1]) { //condition for same column
                c1 = keymat[decrem(pos1[0] - 1) % 5][pos1[1]];
                c2 = keymat[decrem(pos2[0] - 1) % 5][pos2[1]];
                frag = ("" + s + key + "");
            }

            else { //condition for different row & column
                c1 = keymat[pos2[0]][pos1[1]];
                c2 = keymat[pos1[0]][pos2[1]];
                frag = ("" + s + key + "");
            }
            return frag;
        }
        return  "";
    }
}

package com.my.leet.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static String[] findWords(String[] words) {
        Map<Character, Integer> keyboardRows = new HashMap<Character, Integer>();
        String[] keyboardLayoutStrings = {"qwertyuiop", "asdfghjkl", "asdfghjkl"};
        for(int i = 0; i<keyboardLayoutStrings.length; i++){
            char[] charArr = keyboardLayoutStrings[i].toCharArray();
            for(char thisChar: charArr ){
                keyboardRows.put(thisChar, i);
            }
        }
        int index = -1;
        ArrayList<String> result = new ArrayList<String>();
        for(String word: words){
            String lowerCaseWord = word.toLowerCase();
            char firstChar = lowerCaseWord.charAt(0);
            index = keyboardRows.get(firstChar);
            for(int i = 1; i < lowerCaseWord.length(); i++){
                if(keyboardRows.get(lowerCaseWord.charAt(i)) !=index){
                    index = -1;
                    break;
                }
                index = keyboardRows.get(lowerCaseWord.charAt(i));
            }
            if(index != -1){
                result.add(word);
            }
        }
        return result.toArray(new String[0]);

    }

    public static void main(String[] args) {
        String[] s = {"omk"};
        System.out.println(findWords(s));
    }
}
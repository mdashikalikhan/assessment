package com.assessment.coding;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static void main(String[] args) {

        System.out.println(longestUniqueSubstring("abcabcbb")); // 3
        System.out.println(longestUniqueSubstring("bbbb"));     // 1
        System.out.println(longestUniqueSubstring("pwwkew"));   // 3
        System.out.println(longestUniqueSubstring(""));         // 0


    }

    static int longestUniqueSubstring(String str){
        int windowStart = 0;

        Set<Character> set = new HashSet<>();

        int length = 0;

        int currentLength = 0;

        for(int windowEnd = 0; windowEnd<str.length(); windowEnd++){

            while(set.contains(str.charAt(windowEnd))){

                set.remove(str.charAt(windowStart));

                windowStart++;


            }

            set.add(str.charAt(windowEnd));

            currentLength = windowEnd -windowStart + 1;

            length = Math.max(length, currentLength);
        }

        return length;
    }
}

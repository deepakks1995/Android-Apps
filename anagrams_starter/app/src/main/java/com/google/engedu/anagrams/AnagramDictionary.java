package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private static int wordLength = DEFAULT_WORD_LENGTH-1;
    private Random random = new Random();
    ArrayList<String> wordList;
    HashSet<String> wordSet;
    HashMap<String,ArrayList<String> > lettersToWord;
    HashMap<Integer, ArrayList<String> > sizeToWords;
    private String currentWord1;

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        sizeToWords = new HashMap<Integer, ArrayList<String> >();
        lettersToWord = new HashMap<String, ArrayList<String>>();
        wordList = new ArrayList<String>();
        wordSet = new HashSet<String>();
        String line;
        ArrayList<String> list;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            if(sizeToWords.containsKey(word.length())) {
                list = sizeToWords.get(word.length());
                list.add(word);
                sizeToWords.put(word.length(),list);
            }
            else {
                list = new ArrayList<String>();
                list.add(word);
                sizeToWords.put(word.length(),list);
            }
            if(lettersToWord.containsKey(sortLetters(word))) {
                list = lettersToWord.get(sortLetters(word));
                list.add(word);
                lettersToWord.put(sortLetters(word),list);
            }
            else {
                list = new ArrayList<String>();
                list.add(word);
                lettersToWord.put(sortLetters(word),list);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base))
            return true;
        return false;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i < wordList.size(); ++i) {
            if(sortLetters(targetWord).compareTo(sortLetters(wordList.get(i)))==0)
                result.add(wordList.get(i));
        }
        return result;
    }

    public String sortLetters(String targetWord) {
        char []word = targetWord.toCharArray();
        Arrays.sort(word);
        String output = new String(word);
        return output;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char ch= 'a';
        StringBuilder str;
        while(ch<='z') {
            str = new StringBuilder(word);
            str.append(ch);
            String s = str.toString();
            if(lettersToWord.containsKey(sortLetters(s))) {
             ArrayList<String> list = lettersToWord.get(sortLetters(s));
                for(int it=0;it<list.size(); ++it) {
                   if(isGoodWord(list.get(it),word))
                        result.add(list.get(it));
                }
            }
            ++ch;
        }
        return result;
    }

    public String pickGoodStarterWord() {
        if(wordLength > MAX_WORD_LENGTH)
            wordLength = DEFAULT_WORD_LENGTH-1;
        ++wordLength;
        int i;
        while(true) {
            i = random.nextInt(sizeToWords.get(wordLength).size());
            if(getAnagramsWithOneMoreLetter(sizeToWords.get(wordLength).get(i)).size() > MIN_NUM_ANAGRAMS) {
                currentWord1 = sizeToWords.get(wordLength).get(i);
                return sizeToWords.get(wordLength).get(i);
            }
        }
    }
}

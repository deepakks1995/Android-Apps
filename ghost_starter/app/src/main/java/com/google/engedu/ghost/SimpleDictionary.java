package com.google.engedu.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    private Random random = new Random();

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    private String binarySearch(String prefix) {
        int mid, low = 0, high = words.size()-1;
        while(low < high) {
            mid = low - (low - high) / 2;
            if(words.get(mid).startsWith(prefix))
                return words.get(mid);
            else if (words.get(mid).compareTo(prefix) > 0)
                high = mid;
            else if(words.get(mid).compareTo(prefix) < 0)
                low = mid + 1;
        }
        return null;
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        Log.d("word", "getAnywordstartingwith called");
        if(prefix.length()!=0)
            return binarySearch(prefix);
        else
            return words.get(random.nextInt(words.size()));
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        Log.d("word" , "getgood called : " + prefix);
        int high = words.size();
        int low = 0, mid = 0;
        String word = words.get(high-1);
        while(low < high){
            mid = low - (low - high) / 2;
            word = words.get(mid);
            if(word.startsWith(prefix))
                break;
            if(word.compareTo(prefix) < 0 )
                low = mid + 1;
            else
                high = mid;
        }
        high = mid;
        ArrayList<String> evenList = new ArrayList<>();
        while(high < words.size() && words.get(high).startsWith(prefix)){
            evenList.add(words.get(high));
            ++high;
        }
        low = mid - 1;
        while(low > 0 && words.get(low).startsWith(prefix)) {
            evenList.add(words.get(low));
            --low;
        }
        Log.d("word" , "getgood called : " + prefix);
        if(evenList.size()==0) return getAnyWordStartingWith(prefix);
        int index = -1;
        int check = 0;
        while(true){
            if(check > 10)
                break;
            String str;
            index = random.nextInt(evenList.size());
            char ch = evenList.get(index).charAt(prefix.length());
            str = prefix + ch;
            if(evenList.contains(str))
                ++check;
            else {
                Log.d("word" , "getgood called : " + prefix);
                return evenList.get(index);
            }
        }
        Log.d("word" , "getgood called : " + prefix);
        return getAnyWordStartingWith(prefix);
    }
}

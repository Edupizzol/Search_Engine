package com.edupizzol.search_engine.index;
import com.edupizzol.search_engine.crawler.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Token {

    private static final Set<String> STOPWORDS = Set.of(
            "o", "a", "os", "as", "um", "uma", "uns", "umas",
            "de", "em", "por", "para", "com", "sem", "sob", "sobre", "até", "após", "desde", "entre",
            "e", "ou", "mas", "que", "se", "como", "quando", "porque",
            "eu", "tu", "ele", "ela", "nós", "eles", "elas", "este", "esta", "isso", "isto", "aquele", "aquela", "qual", "quais",
            "do", "da", "dos", "das", "no", "na", "nos", "nas", "ao", "aos", "pelo", "pela", "pelos", "pelas", "num", "numa",
            "não", "mais", "muito", "já", "ainda", "também", "só",
            "é", "foi", "são", "ser", "está", "estão", "ter", "tem", "há"
    );

    private static final Set<String> SYMBOLS = Set.of(
            ";", ".", ",", "'", "\"", "@", "#", "%", "&", "$", "*",
            "(", ")", "{", "}", "[", "]", ":", "!", "?", "-", "_", "/", "\\", "+", "=", "<", ">"
    );

    public String removePonctuation(String text){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            if (SYMBOLS.contains(String.valueOf(c))) {result.append(' ');}
            else {result.append(c);}
        }
        return result.toString();
    }

    public List<String> tokenizer(String text){
        String lower = text.toLowerCase();
        String noPonctuations = removePonctuation(lower);
        String[] tokens = noPonctuations.split("\\s+");
        List<String> newTokens = new ArrayList<String>();
        for(String s:tokens){
            if(STOPWORDS.contains(s)) continue;
            newTokens.add(s);
        }
        return newTokens;
    }



}

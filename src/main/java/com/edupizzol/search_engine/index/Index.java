package com.edupizzol.search_engine.index;
import com.edupizzol.search_engine.crawler.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

    public Map<String, List<Posting>> buildIndex(List<Document> documents){
        Map<String,List<Posting>> result = new HashMap<>();
        for(Document doc: documents){
            String rawText = doc.getText();
            Token tokenizer = new Token();
            List<String> words = tokenizer.tokenizer(rawText);
            Map<String,Integer> temp = new HashMap<>();

            //essa funcao tira da array list e coloca em um hash map temporario pra fazer a pesquisa em O(1)
            for(String s:words){
                temp.put(s, temp.getOrDefault(s,0)+1);
            }

            //essa popula o hash map que nos queremos
            for(Map.Entry<String,Integer> entry: temp.entrySet()){
                String s = entry.getKey();
                int frequency = entry.getValue();
                Posting posting = new Posting(doc.getUrl(),frequency);
                result.computeIfAbsent(s, k->new ArrayList<>()).add(posting);
            }
        }

        return result;
    }

    public List<Posting> searchWord(Map<String,List<Posting>> index, String word){
        return index.getOrDefault(word, new ArrayList<>());
    }

}

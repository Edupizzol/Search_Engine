package com.edupizzol.search_engine.service;
import com.edupizzol.search_engine.crawler.Crawler;
import com.edupizzol.search_engine.crawler.Document;
import com.edupizzol.search_engine.entity.DocumentEntity;
import com.edupizzol.search_engine.entity.PostingEntity;
import com.edupizzol.search_engine.entity.TermEntity;
import com.edupizzol.search_engine.index.Token;
import com.edupizzol.search_engine.repository.DocumentRepository;
import com.edupizzol.search_engine.repository.PostingRepository;
import com.edupizzol.search_engine.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class IndexingService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private PostingRepository postingRepository;

    public void indexSeed(String url){

        Crawler crawler = new Crawler();
        List<Document> documents = crawler.crawl(url);

        for(Document doc: documents){
            //salva cada um dos documentos
            DocumentEntity documentEntity = new DocumentEntity();
            documentEntity.setUrl(doc.getUrl());
            documentEntity.setTitle(doc.getTitle());
            documentEntity.setText(doc.getText());
            DocumentEntity savedDocument = documentRepository.save(documentEntity);

            Token token = new Token();
            List<String> words = token.tokenizer(doc.getText());

            //faz sentido colocar todas as palavras em um map afim de diminuir a busca de O(N) para O(1) em tempo de compilacao e evitar acessos desnecessarios ao banco
            //pra comparacao, se fosse feito colocando cada uma no banco ia pedir acessos de busca e salvamento ao banco, o que ia demorar bem mais
            Map<String,Integer> map = new HashMap<>();
            for(String s:words){
                map.put(s, map.getOrDefault(s,0)+1);
            }

            for(Map.Entry<String,Integer> entry: map.entrySet()){
                String word = entry.getKey();
                Integer frequency = entry.getValue();

                Optional<TermEntity> existingTerm = termRepository.findByWord(word);
                TermEntity termEntity;

                if(existingTerm.isPresent()){
                    termEntity = existingTerm.get();
                }
                else{
                    TermEntity newTerm = new TermEntity();
                    newTerm.setWord(word);
                    termEntity = termRepository.save(newTerm);
                }

                PostingEntity postingEntity = new PostingEntity();
                postingEntity.setFrequency(frequency);
                postingEntity.setDocument(savedDocument);
                postingEntity.setTerm(termEntity);
                postingRepository.save(postingEntity);
            }

        }
    }
}

package com.edupizzol.search_engine.controller;
import com.edupizzol.search_engine.dto.SearchResultDTO;
import com.edupizzol.search_engine.entity.PostingEntity;
import com.edupizzol.search_engine.entity.TermEntity;
import com.edupizzol.search_engine.repository.PostingRepository;
import com.edupizzol.search_engine.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private PostingRepository postingRepository;

    @GetMapping("/search")
    public List<SearchResultDTO> search(@RequestParam("q") String query){
        List<SearchResultDTO> results = new ArrayList<>();
        Optional<TermEntity> termEntity = termRepository.findByWord(query.toLowerCase());
        if(termEntity.isEmpty()) return  results;
        List<PostingEntity> postings = postingRepository.findByTerm(termEntity.get());

        for(PostingEntity posting: postings){
            results.add(
                    new SearchResultDTO(
                            posting.getDocument().getUrl(),
                            posting.getDocument().getTitle(),
                            posting.getFrequency()
                    )
            );
        }

        return results;
    }
}

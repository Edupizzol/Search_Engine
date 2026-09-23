package com.edupizzol.search_engine.repository;
import com.edupizzol.search_engine.entity.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermRepository extends JpaRepository<TermEntity,Integer>{
    Optional<TermEntity> findByWord(String word);
}

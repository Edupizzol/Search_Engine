package com.edupizzol.search_engine.repository;
import com.edupizzol.search_engine.entity.PostingEntity;
import com.edupizzol.search_engine.entity.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostingRepository extends JpaRepository<PostingEntity,Integer>{
    List<PostingEntity> findByTerm(TermEntity term);
}

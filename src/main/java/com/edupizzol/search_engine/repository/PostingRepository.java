package com.edupizzol.search_engine.repository;
import com.edupizzol.search_engine.entity.PostingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<PostingEntity,Integer>{}

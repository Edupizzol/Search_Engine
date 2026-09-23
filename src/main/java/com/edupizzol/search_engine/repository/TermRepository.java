package com.edupizzol.search_engine.repository;
import com.edupizzol.search_engine.entity.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<TermEntity,Integer>{}

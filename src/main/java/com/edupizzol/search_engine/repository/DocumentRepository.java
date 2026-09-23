package com.edupizzol.search_engine.repository;
import com.edupizzol.search_engine.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity,Integer>{}

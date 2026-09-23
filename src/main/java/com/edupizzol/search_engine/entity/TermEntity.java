package com.edupizzol.search_engine.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "TERM")
public class TermEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTerm")
    private Integer id;

    @Column(name = "word")
    private String word;

    public Integer getId(){
        return this.id;
    }

    public String word(){
        return this.word;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public void setWord(String word){
        this.word=word;
    }
}
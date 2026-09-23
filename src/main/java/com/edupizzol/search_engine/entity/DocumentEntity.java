package com.edupizzol.search_engine.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocument")
    private Integer id;

    @Column(name = "urlDocument", nullable = false, length = 2000)
    private String url;

    @Column(name = "titleDocument", nullable = false, length = 500)
    private String title;

    @Column(name = "textDocument", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String text;

    public DocumentEntity(){}

    public Integer getId(){
        return this.id;
    }

    public String getUrl(){
        return this.url;
    }

    public String getTitle(){
        return this.title;
    }

    public String getText(){
        return this.text;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setText(String text){
        this.text=text;
    }
}
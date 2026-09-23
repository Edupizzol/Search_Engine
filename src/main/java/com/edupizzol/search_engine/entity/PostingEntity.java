package com.edupizzol.search_engine.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "POSTING")
public class PostingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPosting")
    private Integer id;

    @Column(name = "frequency", nullable = false)
    private Integer frequency;

    @ManyToOne
    @JoinColumn(name="idDocument", nullable = false)
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "idTerm", nullable = false)
    private TermEntity term;

    public PostingEntity(){};

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public DocumentEntity getDocument() {
        return this.document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public TermEntity getTerm() {
        return this.term;
    }

    public void setTerm(TermEntity term) {
        this.term = term;
    }
}
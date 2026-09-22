package com.edupizzol.search_engine.index;

public class Posting {
    private final String documentUrl;
    private final int frequency;

    public Posting(String documentUrl, int frequency){
        this.documentUrl = documentUrl;
        this.frequency = frequency;
    }

    public String getDocumentUrl(){
        return this.documentUrl;
    }

    public int getFrequency(){
        return this.frequency;
    }
}

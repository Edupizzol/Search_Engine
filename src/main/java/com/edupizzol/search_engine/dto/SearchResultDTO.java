package com.edupizzol.search_engine.dto;

public class SearchResultDTO {

    private final String url;
    private final String title;
    private final int frequency;

    public SearchResultDTO(String url, String title, int frequency){
        this.url=url;
        this.title=title;
        this.frequency=frequency;
    }

    public String getUrl(){return this.url;}

    public String getTitle(){return this.title;}

    public int getFrequency(){return this.frequency;}
}

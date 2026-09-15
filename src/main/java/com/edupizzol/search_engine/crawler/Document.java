package com.edupizzol.search_engine.crawler;

public class Document {
     private final String url;
     private final String title;
     private final String text;

     public Document(String url, String title, String text){
         this.url=url;
         this.title=title;
         this.text=text;
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

    @Override
    public String toString(){
         return "Document: URL: " + this.url + " Title: " + this.title;
    }

    //o objetivo desse metodo e evitar que a comparacao do equals seja somente por memoria, e evitar duplicata de documento
    @Override
    public boolean equals(Object o){
         if(this == o) return true;
         if(o==null || o.getClass()!=this.getClass()) return false;
         Document document = (Document) o;
         return this.url.equals(document.url);
    }

    @Override
    public int hashCode(){
         return url.hashCode();
    }

}

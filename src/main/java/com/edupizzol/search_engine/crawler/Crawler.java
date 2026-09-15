package com.edupizzol.search_engine.crawler;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class Crawler {

    private static class UrlEntry{
        String url;
        int depth;

        UrlEntry(String url, int depth){
            this.url = url;
            this.depth = depth;
        }
    }

    private static final int MAX_PAGES = 100;
    private static final int DEPTH = 3;
    private static final  long DELAY = 500;
    private static final String ALLOWED_DOMAIN = "pt.wikipedia.org";

    private String normalizeUrl(String url) {
        int hashIndex = url.indexOf('#');
        if (hashIndex != -1) {
            return url.substring(0, hashIndex);
        }
        return url;
    }

    private boolean isArticleLink(String url) {
        return url.contains("://" + ALLOWED_DOMAIN + "/wiki/") && !url.substring(url.indexOf("/wiki/") + 6).contains(":");
    }

    private String downloadPage(String url) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("User-Agent", "SearchEngineBot/1.0 (edupizzol16@gmail.com)").GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private Document buildDocument(org.jsoup.nodes.Document jsoupDoc, String url) {
        return new Document(url, jsoupDoc.title(), jsoupDoc.text());
    }

    private List<String> buildLinks(org.jsoup.nodes.Document jsoupDoc) {
        List<String> links = new ArrayList<>();
        for (Element anchor : jsoupDoc.select("a[href]")) {
            String url = normalizeUrl(anchor.attr("abs:href"));
            if (isArticleLink(url)) {
                links.add(url);
            }
        }
        return links;
    }

    public List<Document> crawl(String urlSeed){
        Queue<UrlEntry> urlQueue = new LinkedList<>();
        Set<String> urlVisited = new HashSet<>();
        List<Document> docs = new ArrayList<>();
        urlQueue.add(new UrlEntry(urlSeed, 0));

        while(!urlQueue.isEmpty() && docs.size() < MAX_PAGES){
            UrlEntry current = urlQueue.poll();
            String urlTemp = current.url;
            int currentDepth = current.depth;

            if (urlVisited.contains(urlTemp)) continue;
            urlVisited.add(urlTemp);

            try {
                String html = downloadPage(urlTemp);
                System.out.println("HTML baixado, tamanho: " + html.length());
                org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html, urlTemp);
                Document doc = buildDocument(jsoupDoc, urlTemp);
                docs.add(doc);

                if (currentDepth < DEPTH) {
                    List<String> neighbours = buildLinks(jsoupDoc);
                    for (String neighbour : neighbours) {
                        if (!urlVisited.contains(neighbour)) {
                            urlQueue.add(new UrlEntry(neighbour, currentDepth + 1));
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Falha ao processar " + urlTemp + ": " + e.getMessage());
            }

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return docs;
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        List<Document> docs = crawler.crawl("https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)");

        System.out.println("Total de documentos coletados: " + docs.size());
        for (Document doc : docs) {
            System.out.println(doc);
        }
    }

}

# Search Engine

Motor de busca com crawler, índice invertido e ranking (TF-IDF/BM25) implementados do zero em Java — sem Apache Lucene.

## Por quê sem Lucene

A maioria dos projetos de "search engine" para portfólio delega a indexação e o ranking direto para o Lucene, o que reduz o trabalho a "configurar uma biblioteca pronta". Aqui a decisão foi o oposto: implementar o índice invertido e o algoritmo de ranking manualmente, para que o projeto demonstre entendimento de como um motor de busca funciona por dentro — tokenização, estrutura de dados do índice, cálculo de relevância — em vez de apenas integração com uma ferramenta.

## Stack

- **Backend:** Java 21, Spring Boot, Maven
- **Persistência:** MySQL (via Spring Data JPA)
- **Frontend:** React
- **Extração de HTML:** Jsoup (usado apenas para parsing de HTML — não para indexação ou busca)


## Status do projeto

- [x] **Fase 1 — Crawler**: BFS a partir de uma seed, restrito ao domínio `pt.wikipedia.org`, com extração de texto/título/links via Jsoup
- [ ] **Fase 2 — Índice invertido e ranking** (TF-IDF/BM25, implementado manualmente)
- [ ] **Fase 3 — API REST** (Spring Boot + persistência em MySQL)
- [ ] **Fase 4 — Frontend** (React)
- [ ] **Fase 5 — Autenticação, histórico de busca, painel admin** (Spring Security)

## Crawler (Fase 1)

Implementa BFS a partir de uma URL semente, com:

- Fila de URLs a visitar, carregando profundidade de cada uma
- Controle de URLs já visitadas
- Fetch via `HttpClient` (java.net.http), com `User-Agent` customizado — necessário porque a Wikipedia restringe respostas para requisições sem identificação adequada
- Extração de título, texto e links via Jsoup
- Filtros de link: apenas artigos do domínio configurado, excluindo páginas de namespace (`Portal:`, `Especial:`, etc.) e redlinks (artigos inexistentes)
- Limites configuráveis: número máximo de páginas, profundidade máxima, delay entre requisições

**Limitação conhecida:** redirects da Wikipedia (ex: `JVM` → `Máquina Virtual Java`) não são deduplicados — a mesma página pode aparecer sob duas URLs diferentes no dataset coletado.

## Rodando o projeto

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Autor

Eduardo Dal Pizzol — [GitHub](https://github.com/Edupizzol)

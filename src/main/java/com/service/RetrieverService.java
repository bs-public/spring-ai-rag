package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class RetrieverService {

  private static final Logger logger = LoggerFactory.getLogger(RetrieverService.class);

  private final VectorStore vectorStore;

  public RetrieverService(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  public List<Document> retrieve(String query) {

    SearchRequest searchRequest =
        SearchRequest.builder()
            .query(query)
            .topK(3) // retrieve top 3 most similar chunks
            .similarityThreshold(SearchRequest.SIMILARITY_THRESHOLD_ACCEPT_ALL)
            .build();

    List<Document> docs = vectorStore.similaritySearch(searchRequest);

    docs.forEach(doc -> logger.info("Retrieved chunk: {}", doc.getFormattedContent()));

    return docs;
  }
}

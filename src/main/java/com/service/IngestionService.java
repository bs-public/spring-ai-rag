package com.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {

  private final VectorStore vectorStore;
  private final TokenTextSplitter textSplitter;

  public IngestionService(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
    this.textSplitter = new TokenTextSplitter();
  }

  public void ingest(String content) {

    // Create a root document
    Document document =
        new Document(UUID.randomUUID().toString(), content, Map.of("source", "manual"));

    // Split into chunks
    List<Document> chunks = textSplitter.split(List.of(document));

    // Store in vector store
    vectorStore.add(chunks);
  }
}

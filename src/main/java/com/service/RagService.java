package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RagService {

  private static final Logger log = LoggerFactory.getLogger(RagService.class);

  private final ChatModel chatModel;
  private final RetrieverService retrieverService;

  public RagService(ChatModel chatModel, RetrieverService retrieverService) {
    this.chatModel = chatModel;
    this.retrieverService = retrieverService;
  }

  public String ask(String question) {

    // Retrieve relevant chunks
    List<Document> docs = retrieverService.retrieve(question);

    if (docs.isEmpty()) {
      return "I don't know.";
    }

    // Build context from documents
    String context =
        docs.stream().map(Document::getFormattedContent).collect(Collectors.joining("\n\n"));

    log.info("RAG context:\n{}", context);

    // Build prompt
    String prompt =
        """
        You are a helpful assistant.

        Answer the question using ONLY the context below.
        If the answer is not contained in the context, say "I don't know".

        Context:
        %s

        Question:
        %s
        """
            .formatted(context, question);

    log.info("\nprompt: {}", prompt);

    // Call LLM
    return chatModel.call(prompt);
  }
}

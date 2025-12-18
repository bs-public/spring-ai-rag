package com.controller;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.RetrieverService;

@RestController
public class RetrievalController {
  
  private final RetrieverService retrieverService;
  
  public RetrievalController(RetrieverService retrieverService) {
    this.retrieverService = retrieverService;
  }
  
  @GetMapping("/retrieve")
  public List<Document> retrieve(@RequestParam("q") String q) {
    return retrieverService.retrieve(q);
  }
}

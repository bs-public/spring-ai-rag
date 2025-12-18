package com.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dto.AskResponse;
import com.service.RagService;

@RestController
public class RagController {

  private final RagService ragService;

  public RagController(RagService ragService) {
    this.ragService = ragService;
  }

  @GetMapping(value = "/ask", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AskResponse> ask(@RequestParam("q") String q) {
    String answer = ragService.ask(q);
    return ResponseEntity.ok(new AskResponse(answer));
  }
}

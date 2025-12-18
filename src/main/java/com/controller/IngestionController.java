package com.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dto.IngestRequest;
import com.dto.IngestResponse;
import com.service.IngestionService;

@RestController
public class IngestionController {

  private final IngestionService ingestionService;

  public IngestionController(IngestionService ingestionService) {
    this.ingestionService = ingestionService;
  }

  @PostMapping(
      value = "/ingest",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<IngestResponse> ingest(@RequestBody IngestRequest request) {
    ingestionService.ingest(request.getContent());
    return ResponseEntity.ok(new IngestResponse("Content ingested successfully"));
  }
}

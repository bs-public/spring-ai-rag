# spring-ai-rag
Spring Boot application demonstrating Retrieval-Augmented Generation (RAG) using Spring AI

# RAG Flow 
 - Step 1 — Document ingestion (Indexing phase)
 - Step 2 — Retrieval
 - Step 3 — RAG generation


# Endpoints
```
POST   /ingest    -> Index documents into the vector store
GET    /retrieve  -> Retrieve similar document chunks
GET    /ask       -> Ask questions using Retrieval-Augmented Generation
```

1. Ingest content (Indexing phase)

Request
```bash
  curl -X POST http://localhost:8080/ingest -H "Content-Type: application/json" -d '{"content": "Spring AI provides abstractions for building AI-powered applications using Java and Spring Boot."}'
```
Response
```
{
    "message": "Content ingested successfully"
}
```

2. Retrieve similar documents (Retrieval phase)

Request
```bash
   curl -X GET "http://localhost:8080/retrieve?q=What%20is%20Spring%20AI?"
```

Response
```
[
    {
        "id": "f8920706-93d7-499b-b9e2-93ada95e51cf",
        "text": "Spring AI provides abstractions like VectorStore, EmbeddingModel, and ChatModel to build RAG applications.",
        "media": null,
        "metadata": {
            "distance": 0.1304652665035515,
            "source": "manual"
        },
        "score": 0.8695347334964485
    }
]
```

3. Ask a question (RAG generation phase)

Request
```bash
  curl -X GET "http://localhost:8080/ask?q=What%20does%20Spring%20AI%20provide?"
```

Response 
```
{
    "answer": "Spring AI provides abstractions like VectorStore, EmbeddingModel, and ChatModel to build RAG applications."
}
```

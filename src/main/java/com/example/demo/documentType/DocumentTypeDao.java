package com.example.demo.documentType;

import org.springframework.stereotype.Component;

@Component
public interface DocumentTypeDao {
    DocumentType get(DocumentType documentType);
}

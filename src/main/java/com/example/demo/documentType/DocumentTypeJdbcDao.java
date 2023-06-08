package com.example.demo.documentType;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTypeJdbcDao implements DocumentTypeDao {

    @Override
    public DocumentType get(DocumentType documentType) {
        return new DocumentType("jpeg");
    }
}

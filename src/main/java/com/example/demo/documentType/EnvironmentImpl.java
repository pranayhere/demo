package com.example.demo.documentType;

import org.springframework.stereotype.Component;

@Component
public class EnvironmentImpl implements Environment {

    @Override
    public String getAttachmentPath() {
        return "attachment-path";
    }
}

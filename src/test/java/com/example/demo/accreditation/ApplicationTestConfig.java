package com.example.demo.accreditation;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class ApplicationTestConfig {

    public ApplicationTestConfig() {
        MockitoAnnotations.openMocks(this);
    }
}

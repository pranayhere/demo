package com.example.demo.accreditation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccreditationDao {
    List<Accreditation> getAllByFilter(Accreditation accreditation);
}

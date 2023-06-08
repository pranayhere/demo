package com.example.demo.accreditation;

import org.springframework.stereotype.Component;

import java.util.List;

public interface AccreditationDao {
    List<Accreditation> getAllByFilter(Accreditation accreditation);
}

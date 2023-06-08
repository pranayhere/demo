package com.example.demo.accreditation;


import java.util.List;

public interface AccreditationService {
    List<Accreditation> getAll();
    List<AccreditationJsonBean> toJsonBean(List<Accreditation> accreditationList);
}

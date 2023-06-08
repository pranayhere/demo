package com.example.demo.accreditation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccreditationJdbcDao implements AccreditationDao {

    @Override
    public List<Accreditation> getAllByFilter(Accreditation accreditation) {
        List<Accreditation> store = new ArrayList<>();
        Accreditation acc = new Accreditation();
        acc.setAccreditationId("1");
        acc.setDescription("dummy description");
        acc.setName("dummyName");
        acc.setActiveFlag(true);
        acc.setImageType("jpeg");
        acc.setImagePath("dummy-path");
        store.add(acc);
        return store;
    }
}

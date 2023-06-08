package com.example.demo.accreditation;

import com.example.demo.documentType.DocumentTypeDao;
import com.example.demo.documentType.Environment;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class AccreditationRestServiceTest {
    @MockBean
    private AccreditationDao accreditationDao;

    @MockBean
    private DocumentTypeDao documentTypeDao;

    @MockBean
    private Environment environment;

    @Autowired
    private AccreditationRestService accreditationRestService;

    @BeforeEach
    public void setup() {
        accreditationDao = mock(AccreditationDao.class);
        documentTypeDao = mock(DocumentTypeDao.class);
        environment = mock(Environment.class);

        accreditationRestService = new AccreditationRestService(accreditationDao, documentTypeDao, environment);
    }

    @Test
    void testGetAll() {
        List<Accreditation> expected = new ArrayList<>();
        expected.add(getDummyAccreditation());
        expected.add(getDummyAccreditation());

        when(accreditationDao.getAllByFilter(ArgumentMatchers.any())).thenReturn(expected);
        List<Accreditation> actual = accreditationRestService.getAll();

        verify(accreditationDao, times(1)).getAllByFilter(ArgumentMatchers.any());
        assertEquals(expected, actual);
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void testToJsonBean() {
        List<Accreditation> expected = new ArrayList<>();
        expected.add(getDummyAccreditation());

        List<AccreditationJsonBean> actual = accreditationRestService.toJsonBean(expected);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getAccreditationId(), actual.get(0).getAccreditationId());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        // test image path
    }

    private Accreditation getDummyAccreditation() {
        Accreditation accreditation = new Accreditation();
        accreditation.setAccreditationId("1234-567-890");
        accreditation.setDescription("dummy description");
        accreditation.setName("dummyName");
        accreditation.setActiveFlag(true);
        accreditation.setImageType("jpeg");
        accreditation.setImagePath("dummy-path");
        return accreditation;
    }
}
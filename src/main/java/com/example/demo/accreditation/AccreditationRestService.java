package com.example.demo.accreditation;

import com.example.demo.documentType.DocumentType;
import com.example.demo.documentType.DocumentTypeDao;
import com.example.demo.documentType.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccreditationRestService implements AccreditationService {
    private AccreditationDao accreditationDao;
    private DocumentTypeDao documentTypeDao;
    private Environment environment;

    private final static String ACCREDITATION_IMAGES = "accreditation_images";
    private final static String DOT = ".";

    @Autowired
    public AccreditationRestService(AccreditationDao accreditationDao, DocumentTypeDao documentTypeDao, Environment environment) {
        this.accreditationDao = accreditationDao;
        this.documentTypeDao = documentTypeDao;
        this.environment = environment;
    }

    @Override
    public List<Accreditation> getAll() {
        Accreditation filterAccreditation = new Accreditation();
        filterAccreditation.setActiveFlag(true);
        return accreditationDao.getAllByFilter(filterAccreditation);
    }

    @Override
    public List<AccreditationJsonBean> toJsonBean(List<Accreditation> accreditationList) {
        List<AccreditationJsonBean> accreditationJson = new ArrayList<>();

        System.out.println("printing list " + accreditationList);

        accreditationList.stream()
                .map(accreditation -> accreditation.toJsonBean(getImagePath(accreditation)))
                .forEach(accreditationJson::add);

        return accreditationJson;
    }

    private String getImagePath(Accreditation accreditation) {
        DocumentType documentType = null;
        String imagePath = null;
        if (!StringUtils.isEmpty(accreditation.getImageType())) {
            documentType = documentTypeDao.get(new DocumentType(accreditation.getImageType()));
        }

        if (documentType != null) {
            imagePath = environment.getAttachmentPath() + File.separatorChar + ACCREDITATION_IMAGES + File.separatorChar + accreditation.getAccreditationId()
                    + File.separatorChar + accreditation.getAccreditationId() + DOT + accreditation.getImageType();
        }

        return imagePath;
    }
}

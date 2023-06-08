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

    @Autowired
    private AccreditationDao accreditationDao;

    @Autowired
    private DocumentTypeDao documentTypeDao;

    @Autowired
    private Environment environment;

    public List<Accreditation> getAll() {
        Accreditation filterAccreditation = new Accreditation();
        filterAccreditation.setActiveFlag(true);
        return accreditationDao.getAllByFilter(filterAccreditation); // changed name
    }

    @Override
    public List<AccreditationJsonBean> toJsonBean(List<Accreditation> accreditationList) {
        List<AccreditationJsonBean> accreditationJson = new ArrayList<>();
        for (Accreditation accreditation : accreditationList) {
            accreditationJson.add(accreditation.toJsonBean(getImagePath(accreditation)));
        }

        return accreditationJson;
    }

    private String getImagePath(Accreditation accreditation) {
        DocumentType documentType = null;
        String imagePath = null;
        if (!StringUtils.isEmpty(accreditation.getImageType())) {
            documentType = documentTypeDao.get(new DocumentType(accreditation.getImageType()));
        }

        if (documentType != null) {
            imagePath = environment.getAttachmentPath() + File.separatorChar + "accreditation_images" + File.separatorChar + accreditation.getAccreditationId()
                    + File.separatorChar + accreditation.getAccreditationId() + "." + accreditation.getImageType();
        }

        return imagePath;
    }
}

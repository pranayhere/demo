package com.example.demo.accreditation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccreditationRestController {

    @Autowired
    AccreditationRestService accreditationService;

    @RequestMapping(value = "/rest/accreditation_list.go", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<AccreditationJsonBean>> getAccreditationList() {

        try {
            List<Accreditation> accreditations = accreditationService.getAll();
            List<AccreditationJsonBean> accreditationJsonBean = accreditationService.toJsonBean(accreditations);
            return ResponseEntity.ok(accreditationJsonBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

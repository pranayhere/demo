package com.example.demo.accreditation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = ApplicationTestConfig.class)
@WebAppConfiguration
class AccreditationRestControllerTest {
    @MockBean
    private AccreditationRestService accreditationService;

    @Autowired
    private AccreditationRestController controller;

    private MockMvc mockMvc;
    private List<Accreditation> accreditations;
    private List<AccreditationJsonBean> accreditationsJsonBean;

    @BeforeEach
    void init() {
        accreditationService = Mockito.mock(AccreditationRestService.class);
        controller = new AccreditationRestController(accreditationService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        accreditations = new ArrayList<>();
        Accreditation accreditation = new Accreditation();
        accreditation.setAccreditationId("1");
        accreditation.setDescription("dummy description");
        accreditation.setName("dummyName");
        accreditation.setActiveFlag(true);
        accreditation.setImageType("jpeg");
        accreditation.setImagePath("dummy-path");
        accreditations.add(accreditation);

        accreditationsJsonBean = new ArrayList<>();
        AccreditationJsonBean jsonBean = new AccreditationJsonBean();
        jsonBean.setAccreditationId(accreditation.getAccreditationId());
        jsonBean.setDescription(accreditation.getDescription());
        jsonBean.setName(accreditation.getName());
    }

    @Test
    void testGetAllAccreditationSuccess() throws Exception {
        when(this.accreditationService.getAll()).thenReturn(accreditations);
        when(this.accreditationService.toJsonBean(ArgumentMatchers.any())).thenReturn(accreditationsJsonBean);

        this.mockMvc.perform(get("/rest/accreditation_list.go"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].accreditationId").value(accreditationsJsonBean.get(0).getAccreditationId()))
                .andExpect(jsonPath("$.[0].description").value(accreditationsJsonBean.get(0).getDescription()))
                .andExpect(jsonPath("$.[0].name").value(accreditationsJsonBean.get(0).getName()));
    }
}
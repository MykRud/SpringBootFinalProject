package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AddType.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class AddTypeTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TypeOfActivityService service;

    @Test
    @WithUserDetails("admin")
    void addType() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/admin/typesAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("WEB-INF/pages/admin/add-type"));
    }
}
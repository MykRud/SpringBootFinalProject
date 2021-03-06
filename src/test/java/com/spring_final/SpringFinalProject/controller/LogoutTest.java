package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Logout.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class LogoutTest {

    private MockMvc mvc;

    @InjectMocks
    private Logout logoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(logoutController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void logout() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/logout-success"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:home"));
    }
}
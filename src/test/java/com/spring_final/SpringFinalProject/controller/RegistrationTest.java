package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import com.spring_final.SpringFinalProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Registration.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class RegistrationTest {

    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private Registration registrationController;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(registrationController).setRemoveSemicolonContent(false)
                .setViewResolvers(viewResolver).build();
    }

    @WithUserDetails("qwerty")
    @Test
    void itShouldRegisterUser() throws Exception {

        User user = new User(1, "John", "Travolta", "john", "1234", 67, "+380970689690", "Male", new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(userService.getUser("john")).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/registration")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

        verify(userService, times(1)).getUser("john");

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).addUser(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

        verifyNoMoreInteractions(userService);
    }

    @WithUserDetails("qwerty")
    @Test
    void itShouldNotRegisterUserAsUserAlreadyExists() throws Exception {

        User user = new User(1, "John", "Travolta", "john", "1234", 67, "+380970689690", "Male", new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(userService.getUser("john")).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/registration")
                        .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));

        verify(userService, times(1)).getUser("john");

        verifyNoMoreInteractions(userService);
    }
}
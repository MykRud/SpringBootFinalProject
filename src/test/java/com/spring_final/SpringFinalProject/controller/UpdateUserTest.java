package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.SpringSecurityWebAuxTestConfig;
import com.spring_final.SpringFinalProject.model.User;
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

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UpdateUser.class, SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class UpdateUserTest {

    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UpdateUser updateUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(updateUserController).setRemoveSemicolonContent(false).build();
    }

    @WithUserDetails("admin")
    @Test
    void updateUser() throws Exception {
        User user = new User(1, "John", "Travolta", "qwerty", "1234", 67, "+380970689690", "Male", new HashSet<>(), new HashSet<>(), new HashSet<>());

        when(userService.getUser(1)).thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/admin/userUpdate?user_id=1")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/userUpdateDisplay?s=1"));

        verify(userService, times(1)).getUser(1);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).updateUser(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

        verifyNoMoreInteractions(userService);
    }
}
package com.assessment;

import com.assessment.entity.h2.User;
import com.assessment.rest.UserController;
import com.assessment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class UserControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserById() throws Exception{
        User user = new User(2l, "MD ASHIK ALI KHAN", "khan.ashik@gmail.com");
        Mockito.when(userService.getUserById(2l)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("MD ASHIK ALI KHAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("khan.ashik@gmail.com"));

    }
}

package com.assessment;

import com.assessment.components.DynamicNotificationManager;
import com.assessment.components.NotficationManager;
import com.assessment.dao.h2.BookDao;
import com.assessment.dao.h2.CustomerDao;
import com.assessment.dao.h2.OrderDao;
import com.assessment.dao.mysql.DepartmentDao;
import com.assessment.model.MySqlUserModel;
import com.assessment.rest.UserController;
import com.assessment.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomerDao customerDao;

    @MockBean
    private OrderDao orderDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private DepartmentDao departmentDao;

    @MockBean
    private NotficationManager notficationManager;

    @MockBean
    private DynamicNotificationManager dynamicNotificationManager;

    @Test
    void testGetMySQLUserById() throws Exception {
        MySqlUserModel mySqlUserModel =
                new MySqlUserModel("MD ASHIK ALI KHAN", "khan.ashik@gmail.com");
        Mockito.when(userService.getMySQLUserById(1L)).thenReturn(mySqlUserModel);



        mockMvc.perform((MockMvcRequestBuilders.get("/users/mysql/1")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email")
                        .value("khan.ashik@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value("MD ASHIK ALI KHAN"));

    }



}

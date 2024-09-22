package com.assessment;

import com.assessment.service.ExternalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExternalServiceTest {
    @InjectMocks
    private ExternalService externalService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGoogleHomePage(){
        String mockResponse = "10 is the number of hydrogen atoms in butane, a hydrocarbon.";
        when(restTemplate.getForObject("http://numbersapi.com/10", String.class)).thenReturn(mockResponse);
        String response = externalService.getApiContents();
        assertEquals(mockResponse, response);
        verify(restTemplate, times(1)).getForObject("http://numbersapi.com/10", String.class);
    }
}

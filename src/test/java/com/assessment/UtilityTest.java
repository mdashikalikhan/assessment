package com.assessment;

import com.assessment.service.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class UtilityTest {

    @Test
    void testStaticMethod(){
        try(MockedStatic<Utility> mockUtility
                = Mockito.mockStatic(Utility.class)) {
            mockUtility
                    .when(()->Utility.getGreetings("ASHIK"))
                    .thenReturn("Hello ASHIK!");

            Assertions.assertEquals("Hello ASHIK!", Utility.getGreetings("ASHIK"));

            mockUtility.verify(()->Utility.getGreetings("ASHIK"));
        }
    }
}

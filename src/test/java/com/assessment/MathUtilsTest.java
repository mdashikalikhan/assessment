package com.assessment;

import com.assessment.utils.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class MathUtilsTest {

    @Test
    void testStaticAdd(){
        try(MockedStatic<MathUtils> m = mockStatic(MathUtils.class)){
            m.when(()->MathUtils.add(10,15)).thenReturn(10);
            int result = MathUtils.add(10,15);
            Assertions.assertEquals(10, result);
            m.verify(()->MathUtils.add(10,15));
        }
    }

    @Test void testStaticSubtract(){
        try(MockedStatic<MathUtils> m = Mockito.mockStatic(MathUtils.class)){
            m.when(()->MathUtils.subtract(100,50)).thenReturn(200);

            int result = MathUtils.subtract(100,50);

            Assertions.assertEquals(200, result);

            m.verify(()->MathUtils.subtract(100, 50));
        }
    }
}

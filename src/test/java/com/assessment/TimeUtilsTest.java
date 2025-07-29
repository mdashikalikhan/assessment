package com.assessment;

import com.assessment.utils.TimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class TimeUtilsTest {

    @Test
    void testCurrentTime(){
        LocalDateTime now = LocalDateTime.of(2025,7,
                29, 7, 31, 01);

        try(MockedStatic<TimeUtils> m = Mockito.mockStatic(TimeUtils.class)){


            m.when(TimeUtils::getCurrentTime).thenReturn(now);

            LocalDateTime t =  TimeUtils.getCurrentTime();

            Assertions.assertEquals(now, t);

            //m.verify(LocalDateTime::now);
        }
    }

}

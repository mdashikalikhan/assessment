package com.assessment.utils;

import java.time.LocalDateTime;

public class TimeUtils {
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now().plusHours(5);
    }
}

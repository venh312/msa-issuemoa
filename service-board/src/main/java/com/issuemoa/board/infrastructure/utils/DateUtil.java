package com.issuemoa.board.infrastructure.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime getNowOfMinusDay(int day) {
        return LocalDate.now().minusDays(day).atStartOfDay();
    }
}

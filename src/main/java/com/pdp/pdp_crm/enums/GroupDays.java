package com.pdp.pdp_crm.enums;

import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public enum GroupDays {
    MONDAY(DayOfWeek.MONDAY),
    TUESDAY(DayOfWeek.TUESDAY),
    WEDNESDAY(DayOfWeek.WEDNESDAY),
    THURSDAY(DayOfWeek.THURSDAY),
    FRIDAY(DayOfWeek.FRIDAY),
    SATURDAY(DayOfWeek.SATURDAY),
    SUNDAY(DayOfWeek.SUNDAY);

    private DayOfWeek dayOfWeek;

    GroupDays(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}

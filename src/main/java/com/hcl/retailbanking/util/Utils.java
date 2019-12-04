package com.hcl.retailbanking.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {

    private Utils(){}
    private static final Random random = new Random();

    /**
     * generateRandom()
     *
     * @param size
     * @return
     */
    public static int generateRandom(int size) {
        return random.nextInt(size);
    }

    /**
     * getCurrentDate()
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * getNextDate()
     *
     * @param date
     * @param count
     * @return
     */
    public static Date getNextDate(Date date, Integer count) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, count);
        return calendar.getTime();
    }
}

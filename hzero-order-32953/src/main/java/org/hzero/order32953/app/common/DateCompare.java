package org.hzero.order32953.app.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现时间的对比
 *
 * @author 32953 2021-08-26 11:16:33
 */
public class DateCompare {

    public static boolean before(String start, String end, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);
        return startDate.before(endDate);
    }

}

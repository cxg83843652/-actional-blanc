package cn.actional.blanc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-09-29 18:44
 */
public class DateUtils {

    public static final String DATA_PATTERN = "yyyy-MM-dd";
    public static final String DATA_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date,DATA_PATTERN);
    }

    public static String format(Date date, String dataPattern) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dataPattern);
            return format.format(date);
        }
        return null;
    }


}

package cn.minsin.core.tools;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 不同时间类型之间的转换
 *
 * @author mintonzhang@163.com
 * @date 2018年6月8日
 */
public class DateUtil extends DateUtils {
    private static final String[] DATE_FORMAT = {"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy-MM-dd", "yyyyMMdd",
            "yyyy-MM-dd HH:mm"};

    public static String transform2Standard(String origin) {
        try {
            Date tmp = string2Date(origin);
            return date2String(tmp, 0);
        } catch (Exception e) {
        }
        return origin;
    }

    public static String transform2Standard(String origin, String format) {
        try {
            if (StringUtils.isNotBlank(format)) {
                Date tmp = DateUtils.parseDate(origin, Locale.CHINA, format);
                return date2String(tmp, 0);
            }
        } catch (Exception e) {
        }
        transform2Standard(origin);
        return origin;
    }

    public static String transform(String origin, String formatTo) {
        try {
            Date tmp = string2Date(origin);
            return date2String(tmp, formatTo);
        } catch (Exception e) {
        }
        return origin;
    }

    public static String transform(String origin, String formater, String formatTo) {
        try {
            Date tmp = string2Date(origin, formater);
            return date2String(tmp, formatTo);
        } catch (Exception e) {
        }
        return origin;
    }

    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String date2String(Date date, int formatIndex) {
        return DateFormatUtils.format(date, DATE_FORMAT[formatIndex], TimeZone.getTimeZone("GMT+8"));
    }

    public static String date2String(Date date, String format) {
        return DateFormatUtils.format(date, format, TimeZone.getTimeZone("GMT+8"));
    }

    public static Date string2Date(String source) throws ParseException {
        return DateUtils.parseDate(source, Locale.CHINA, DATE_FORMAT);
    }

    public static String long2DateStr(long source) {
        return long2DateStr(source, "yyyy-MM-dd HH:mm:ss");
    }

    public static String long2DateStr(long source, int formatIndex) {
        return DateFormatUtils.format(new Date(source), DATE_FORMAT[formatIndex], TimeZone.getTimeZone("GMT+8"));
    }

    public static String long2DateStr(long source, String format) {
        return DateFormatUtils.format(new Date(source), format, TimeZone.getTimeZone("GMT+8"));
    }

    public static Date long2Date(long source) {
        return new Date(source);
    }


    /**
     * 根据日期字符串格式化为指定的日期
     *
     * @param source
     * @param format
     * @return
     * @author minton
     * @email mintonzhang@163.com
     * @date 2018年5月24日
     */
    public static Date string2Date(String source, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT[0];
        }
        try {
            return DateUtils.parseDate(source, format);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据日期字符串格式化为指定的日期
     *
     * @param source
     * @param format
     * @return
     * @author minton
     * @email mintonzhang@163.com
     * @date 2018年5月24日
     */
    public static Date string2Date(String source, int formatindex) {
    	try {
    		String format = DATE_FORMAT[formatindex];
    		return DateUtils.parseDate(source, format);
    	} catch (ParseException e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    /**
     * 比较两个时间的大小
     *
     * @param date1 字符串或者时间
     * @param date2 字符串或者时间
     * @return -1为报错或者数据有误 1 为前者比后者大或者相等 相反为0
     * @throws ParseException
     * @author minton
     * @email mintonzhang@163.com
     * @date 2018年5月24日
     */
    public static Integer compareDate(Object date1, Object date2) {
        try {
            Date d1 = date1 instanceof Date ? (Date) date1
                    : date1 instanceof String ? string2Date((String) date1) : null;
            Date d2 = date2 instanceof Date ? (Date) date2
                    : date2 instanceof String ? string2Date((String) date2) : null;
            if (d1 == null || d2 == null) {
                return -1;
            }
            int r = d1.before(d2) ? 0 : 1;
            return r;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 判断两个时间字符串是否相等
     *
     * @param date1 时间字符串 或 Date
     * @param date2 时间字符串或Date
     * @param 格式化模式
     * @return
     * @date 2018年6月14日
     * @author mintonzhang@163.com
     */
    public static boolean isSameDate(Object date1, Object date2, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT[2];
        }
        try {
            String date1Str = date1 instanceof Date ? date2String((Date) date1, format)
                    : date1 instanceof String ? transform(date1.toString(), format) : null;
            String date2Str = date2 instanceof Date ? date2String((Date) date2, format)
                    : date2 instanceof String ? transform(date2.toString(), format) : null;
            return date1Str.equals(date2Str) ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String date2String(Date date,String format,String defaultValue) {
    	try {
    		if(StringUtil.isBlank(format)) {
    			format = DATE_FORMAT[0];
    		}
    		return date2String(date, format);
    	}catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
    }
    

    /**
     * 求两个日期之间距离的天数
     *
     * @param date1
     * @param date2
     * @return 2018年7月2日
     * @author zhh
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) // 同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
                {
                    timeDistance += 366;
                } else // 不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else // 不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }
  
}

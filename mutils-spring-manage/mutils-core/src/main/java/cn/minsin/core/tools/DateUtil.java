package cn.minsin.core.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    public static int getMinutesFromDayBegin(Date date) {
        long msSet = date.getTime();
        Calendar dayBegin = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        dayBegin.setTime(date);
        dayBegin.set(Calendar.HOUR_OF_DAY, 0);
        dayBegin.set(Calendar.MINUTE, 0);
        dayBegin.set(Calendar.SECOND, 0);
        dayBegin.set(Calendar.MILLISECOND, 0);
        long msBegin = dayBegin.getTimeInMillis();
        int minutesFromDayBegin = (int) ((msSet - msBegin) / (60 * 1000));
        return minutesFromDayBegin;
    }

    public static int getMinutesFromDayBegin(String time) {
        assert time != null;
        int hour = 0;
        int minute = 0;
        String[] hm = time.split(":");
        hour = Integer.valueOf(hm[0]);
        if (time.indexOf(":") != -1) {
            String m = hm[1];
            if (m.startsWith("0")) {
                m = m.substring(0);
            }
            minute = Integer.valueOf(m);
        }
        Calendar daySet = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        daySet.set(Calendar.HOUR_OF_DAY, hour);
        daySet.set(Calendar.MINUTE, minute);
        daySet.set(Calendar.SECOND, 0);
        daySet.set(Calendar.MILLISECOND, 0);
        long msSet = daySet.getTimeInMillis();

        Calendar dayBegin = Calendar.getInstance();
        dayBegin.set(Calendar.HOUR_OF_DAY, 0);
        dayBegin.set(Calendar.MINUTE, 0);
        dayBegin.set(Calendar.SECOND, 0);
        dayBegin.set(Calendar.MILLISECOND, 0);
        long msBegin = dayBegin.getTimeInMillis();
        int minutesFromDayBegin = (int) ((msSet - msBegin) / (60 * 1000));
        return minutesFromDayBegin;
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
     * 得到一个月份的最大天数
     *
     * @param date
     * @return
     * @author minton
     * @email mintonzhang@163.com
     * @date 2018年5月25日
     */
    public static Integer getMaxDayOfMonth(Object date) {
        try {
            Date d1 = date instanceof Date ? (Date) date
                    : date instanceof String ? string2Date(dateCheck((String) date)) : null;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d1);
            // 初始化为1号
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 时间校验 默认为 - 截取
     *
     * @param s
     * @return
     * @author minton
     * @email mintonzhang@163.com
     * @date 2018年5月25日
     */
    public static String dateCheck(String s) {
        String[] date = s.split("-");
        date[date.length - 1] = "1";
        return String.join("-", date);
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

    /**
     * 对时间的字符串进行格式的判断
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null; 
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            date = format.parse(str);
            if(compareDate(new Date(), date)==0)
            {
            	convertSuccess = false;
            }	
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取明天
     *
     * @return 2018年8月21日 zhh
     */
    public static Date getNextDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date d = c.getTime();
        String date = df.format(d);
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;
    }

    /**
     * 获取下周一
     *
     * @return 2018年8月21日 zhh
     */
    public static Date getNextMonDay() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {// 周日
            c.add(Calendar.DATE, 1);
        } else {// 非周日
            c.add(Calendar.DATE, 9 - dayOfWeek);
        }
        String date = df.format(c.getTime());
        Date d;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;

    }

    /**
     * 获取下一个月的一号
     *
     * @return
     */
    public static Date getNextFirstDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        String date = df.format(c.getTime());
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;
    }

    /**
     * 获取下一季度的一号
     *
     * @return
     */
    public static Date getNextSeason(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, 1);
        int i = c.get(Calendar.MONTH);
        if (i < 3) {
            c.set(Calendar.MONTH, 0);//1月
        } else if (i < 6) {
            c.set(Calendar.MONTH, 3);//4月
        } else if (i < 9) {
            c.set(Calendar.MONTH, 6);//7月
        } else {
            c.set(Calendar.MONTH, 9);//10月
        }
        c.add(Calendar.MONTH, 3);
        String date = df.format(c.getTime());
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;

    }

    /**
     * 获取下一半年的一号
     *
     * @return
     */
    public static Date getHalfYear(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int i = c.get(Calendar.MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);
        if (i < 6) {
            c.set(Calendar.MONTH, 0);//1
        } else {
            c.set(Calendar.MONTH, 6);//7
        }
        c.add(Calendar.MONTH, 6);
        String date = df.format(c.getTime());
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;

    }

    // 获取下一年的一月一号
    public static Date getNextFirstDayOfYear(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 12);
        String date = df.format(c.getTime());
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;

    }

    /**
     * 获取下两个月的一号
     *
     * @return
     */
    public static Date getTwiceFirstDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int i = c.get(Calendar.MONTH);
        if (i < 2) {
            c.set(Calendar.MONTH, 0);
        } else if (i < 4) {
            c.set(Calendar.MONTH, 2);
        } else if (i < 6) {
            c.set(Calendar.MONTH, 4);
        } else if (i < 8) {
            c.set(Calendar.MONTH, 6);
        } else if (i < 10) {
            c.set(Calendar.MONTH, 8);
        } else {
            c.set(Calendar.MONTH, 10);
        }
        c.add(Calendar.MONTH, 2);
        String date = df.format(c.getTime());
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return d;
    }

    /**
     * 得到两个时间差 如果为负数 则表示前者大于后者
     *
     * @param begin
     * @param end
     * @return
     */
    public static Long getMinutes(Date begin, Date end) {
        if (begin == null || end == null) {
            return 0L;
        }
        return end.getTime() / 60000 - begin.getTime() / 60000;
    }
	/**
	 * 获取当前月的第一天
	 * 
	 * @param str
	 * @return 2018年8月29日 zhh
	 */
	public static String getMonthNowFirstDay() {
		Calendar cale = null;
		cale = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(cale.getTime());
	}

	/**
	 * 获取当前月的最后天
	 * 
	 * @param str
	 * @return 2018年8月29日 zhh
	 */
	public static String getMonthNowLastDay() {
		Calendar cale = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return format.format(cale.getTime());
	}
}

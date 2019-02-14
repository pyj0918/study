package com.ylink.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.util.StringUtils;

/**
 * 功能描述: 日期格式化工具类
 * 创  建  者: jixu
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DateUtil {
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public final static String DATE_FORMAT_MM_yy = "MM/yy";

	public final static String DATE_FORMAT_yyyy_MM = "yyyy/MM";

	public final static String DATE_FORMAT_MMM_yy = "MMM-yy";

	public static final String DATE_FORMAT_yyyyMM = "yyyyMM";

	public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";

	public static final String DATE_FORMAT_MM_dd_yy = "MM/dd/yy";
	
	public final static String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
	
	public final static String DATE_FORMAT_TIME = "HHmmss";
	
	public final static String DATE_FORMAT_TIME_SHORT = "HH:mm:ss";
	
	public final static String DATE_FORMAT_MDHM ="MM月dd日HH时mm分";
	
	public final static String DATE_FORMAT_YMDHMS ="yyMMddHHmmss";
	
	public final static String DATE_BEGIN_AT_DAY = "yyyy-MM-dd 00:00:00";
	
	public final static String DATE_END_AT_DAY = "yyyy-MM-dd 23:59:59";

	public static List getDisplayMonth(Date time, int monthBefore, int monthAfter, SimpleDateFormat format) {
		ArrayList result = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		if (null != time) {
			calendar.setTimeInMillis(time.getTime());
		}
		SimpleDateFormat tmpformat = format;
		if (null == tmpformat) {
			tmpformat = new SimpleDateFormat(DATE_FORMAT_yyyy_MM);
		}
		Calendar calendartmp = Calendar.getInstance();

		for (int i = monthBefore; i <= monthAfter; i++) {
			calendartmp.setTimeInMillis(calendar.getTimeInMillis());
			calendartmp.set(Calendar.DAY_OF_MONTH, 1);
			calendartmp.set(Calendar.HOUR_OF_DAY, 0);
			calendartmp.set(Calendar.MINUTE, 0);
			calendartmp.set(Calendar.SECOND, 0);
			calendartmp.set(Calendar.MILLISECOND, 0);

			calendartmp.add(Calendar.MONTH, i);
			result.add(tmpformat.format(new Date(calendartmp.getTimeInMillis())));
		}
		return result;

	}

	public static List getDisplayMonth(Date from, Date to, String pattern) {
		ArrayList result = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(from);
		Calendar calendarTo = Calendar.getInstance();
		calendarTo.setTime(getMonthLastDay(to));
		if (pattern == null) {
			pattern = DATE_FORMAT_yyyy_MM;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

		while (calendar.before(calendarTo)) {
			result.add(formatter.format(calendar.getTime()));
			calendar.add(Calendar.MONTH, 1);
		}
		return result;

	}

	public static List getDisplayMonth(Date time, int monthBefore, int monthAfter) {
		return getDisplayMonth(time, monthBefore, monthAfter, null);
	}

	public static List getDisplayMonth(int monthBefore, int monthAfter) {
		return getDisplayMonth(null, monthBefore, monthAfter);
	}

	public static List getDisplayMonth(int monthBefore, int monthAfter, String format) {
		return getDisplayMonth(null, monthBefore, monthAfter, new SimpleDateFormat(format, Locale.ENGLISH));
	}

	/**
	 * get months based on time, return array length is monthAfter-monthBefore+1
	 * 
	 * @param time
	 * @param monthBefore
	 * @param monthAfter
	 * @return Date[]
	 */
	public static Date[] getMonth(Date time, int monthBefore, int monthAfter) {
		ArrayList result = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		if (null != time) {
			calendar.setTimeInMillis(time.getTime());
		}
		for (int i = monthBefore; i <= monthAfter; i++) {
			Calendar calendartmp = Calendar.getInstance();
			calendartmp.setTimeInMillis(calendar.getTimeInMillis());
			calendartmp.set(Calendar.DAY_OF_MONTH, 1);
			calendartmp.set(Calendar.HOUR_OF_DAY, 0);
			calendartmp.set(Calendar.MINUTE, 0);
			calendartmp.set(Calendar.SECOND, 0);
			calendartmp.set(Calendar.MILLISECOND, 0);
			calendartmp.add(Calendar.MONTH, i);
			result.add(new Date(calendartmp.getTimeInMillis()));
		}
		return (Date[]) (result.toArray(new Date[monthAfter - monthBefore + 1]));
	}

	/**
	 * get months based on startMonth and endMonth, return array
	 * 
	 * @param time
	 * @param monthBefore
	 * @param monthAfter
	 * @return Date[]
	 */
	public static Date[] getMonth(Date startMonth, Date endMonth) {
		ArrayList result = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		if (null != startMonth) {
			calendar.setTimeInMillis(startMonth.getTime());
		}
		if (null != endMonth) {
			calendarEnd.setTimeInMillis(endMonth.getTime());
		}
		while (!calendar.after(calendarEnd)) {
			result.add(new Date(calendar.getTimeInMillis()));
			calendar.add(Calendar.MONTH, 1);

		}
		return (Date[]) (result.toArray(new Date[result.size()]));
	}

	public static Date getNextMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		if (null != time) {
			calendar.setTimeInMillis(time.getTime());
		}
		calendar.add(Calendar.MONTH, 1);
		return new Date(calendar.getTimeInMillis());
	}

	public static Date getPreviousMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		if (null != time) {
			calendar.setTimeInMillis(time.getTime());
		}
		calendar.add(Calendar.MONTH, -1);
		return new Date(calendar.getTimeInMillis());
	}

	public static Date[] getMonth(int monthBefore, int monthAfter) {
		return getMonth(null, monthBefore, monthAfter);
	}

	public static String getCurrentTimeString() {
		java.util.Date now = Calendar.getInstance().getTime();
		Format formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return formatter.format(now);
	}

	public static String format(Date date) {
		if (date == null)
			return null;

		Format formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return formatter.format(date);
	}

	/**
	 * Format date by the format
	 * 
	 * @param date - the source date
	 * @param format - the date format
	 * @return the result String
	 */
	public static String format(Date date, String format) {
		if (date == null)
			return null;

		Format formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String formatDisplayDate(Date date, String format) {
		if (date == null)
			return null;

		Format formatter = new SimpleDateFormat(format, Locale.ENGLISH);
		return formatter.format(date);
	}

	public static boolean monthInRange(Date current, Date before, Date after) {
		Calendar curr = Calendar.getInstance();
		curr.setTime(current);
		Calendar temp = Calendar.getInstance();
		temp.setTime(before);
		temp.set(Calendar.DAY_OF_MONTH, 1);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		if (curr.before(temp)) {
			return false;
		}
		temp.setTime(after);
		temp.add(Calendar.MONTH, 1);
		temp.set(Calendar.DAY_OF_MONTH, 1);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		if (curr.after(temp)) {
			return false;
		}
		return true;
	}

	public static boolean monthEquals(Date current, Date compare) {
		Calendar curr = Calendar.getInstance();
		curr.setTime(current);
		Calendar cmp = Calendar.getInstance();
		cmp.setTime(compare);
		int year1 = curr.get(Calendar.YEAR);
		int year2 = cmp.get(Calendar.YEAR);
		int month1 = curr.get(Calendar.MONTH);
		int month2 = cmp.get(Calendar.MONTH);
		return (year1 == year2) && (month1 == month2);
	}

	public static String getMonthRate(Date date, boolean moveIn) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		int daysOfThisMonth = calendar.getActualMaximum(Calendar.DATE);
		double rate;
		if (moveIn) {
			rate = (double) (daysOfThisMonth - day + 1) / daysOfThisMonth;
		} else {
			rate = (double) day / daysOfThisMonth;
		}
		rate = (double) Math.round(rate * 10) / 10;
		return String.valueOf(rate);
	}

	public static String getMonthRate(Date dateIn, Date DateOut) {
		Calendar calIn = Calendar.getInstance();
		calIn.setTime(dateIn);
		Calendar calOut = Calendar.getInstance();
		calOut.setTime(DateOut);
		int day = calOut.get(Calendar.DAY_OF_MONTH) - calIn.get(Calendar.DAY_OF_MONTH) + 1;
		int daysOfThisMonth = calIn.getActualMaximum(Calendar.DATE);
		double rate = (double) day / daysOfThisMonth;
		rate = (double) Math.round(rate * 10) / 10;
		return String.valueOf(rate);
	}

	public static Date parse(String dateString, String format) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateString, new ParsePosition(0));
	}

	public static Date parseInEnglishLocale(String dateString, String format) {
		DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
		return formatter.parse(dateString, new ParsePosition(0));
	}

	public static Date parseTargetMonth(String dateString) {
		return parse(dateString, DATE_FORMAT_yyyy_MM);
	}

	public static int compareDate(Date first, Date second) {
		Calendar cf = Calendar.getInstance();
		cf.setTime(first);
		Calendar cs = Calendar.getInstance();
		cs.setTime(second);
		if (cf.after(cs)) {
			return 1;
		} else {
			return (cf.before(cs)) ? -1 : 0;
		}
	}

	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @param string
	 * @return
	 */
	public static String getCurrentTimeString(String pattern) {
		java.util.Date now = Calendar.getInstance().getTime();
		Format formatter = new SimpleDateFormat(pattern);
		return formatter.format(now);
	}

	/**
	 * @param startMonth
	 * @param i
	 * @return
	 */
	public static Date getMonthByOffset(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		calendar.add(Calendar.MONTH, offset);
		return calendar.getTime();
	}

	/**
	 * @param targetMonth
	 * @param newTargetMonth
	 * @return
	 */
	public static int compareMonth(Date first, Date second) {
		Calendar cf = Calendar.getInstance();
		cf.setTime(first);
		Calendar cs = Calendar.getInstance();
		cs.setTime(second);
		int offset = (cs.get(Calendar.YEAR) - cf.get(Calendar.YEAR)) * 12
				+ (cs.get(Calendar.MONTH) - cf.get(Calendar.MONTH));
		return offset;
	}

	/**
	 * @param currDate
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DATE);
	}

	/**
	 * @param mpDate
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param monthSeq
	 * @return
	 */
	public static int getDaysOfMonth(Date startMonth, Integer monthSeq) {
		Date month = getMonthByOffset(startMonth, monthSeq.intValue() - 1);
		return getDaysOfMonth(month);
		// return 30;
	}

	public static Date getStartTimeOfDate(Date date) {
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date getEndTimeOfDate(Date date) {
		if (null==date) {
			return null;
		}
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	private static Calendar getCalendarByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int year = c.get(Calendar.YEAR);
		return year;
	}

	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int month = c.get(Calendar.MONTH);
		return month;
	}

	/**
	 * 根据格式要求格式化时间，并返回原date类型时间
	 * @param date
	 * @param formate
	 * @return
	 */
	public static Date formate(Date date, String formate) {
		SimpleDateFormat formatter = new SimpleDateFormat(formate);
		String dateStr = formatter.format(date);
		return formatter.parse(dateStr, new ParsePosition(0));
	}
	
	/**
	 * 以格林尼治时间格式显示
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * 返回当前日期字符串
	 */
	public static String getStringCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static Date addDay(Date date, int days) {
		if (null==date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(calendar.getTime().getTime());
		} else {
			return calendar.getTime();
		}

	}

	public static Date addHours(Date date, Double hours) {
		if (null==date) {
			return null;
		}
		if (null == hours) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours.intValue());
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(calendar.getTime().getTime());
		} else {
			return calendar.getTime();
		}

	}

	public static Date addMinutes(Date date, Integer minutes) {
		if (null==date) {
			return null;
		}
		if (null == minutes) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(calendar.getTime().getTime());
		} else {
			return calendar.getTime();
		}

	}

	public static Date addMonth(Date date, Integer monthes) {
		if (null==date || null==monthes) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthes);
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(calendar.getTime().getTime());
		} else {
			return calendar.getTime();
		}
	}

	public static Date getBeginOfADay(Date date) {
		if (null==date) {
			return null;
		}
		Date ret = DateUtil.parse(DateUtil.format(date, "yyyy-MM-dd") + " 00:00:00", "yyyy-MM-dd");
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(ret.getTime());
		} else {
			return ret;
		}
	}

	/**
	 * 获取当前日期，YMD格式
	 * @return
	 */
	public static Date getNowYmd() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		currentTime = formatter.parse(dateString, pos);
		return currentTime;
	}

	public static Date set(Date date, Integer field, Integer value) {
		if (null==date || null==field || null==value) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(field, value);
		if (date instanceof java.sql.Date) {
			return new java.sql.Date(calendar.getTime().getTime());
		} else {
			return calendar.getTime();
		}
	}
	
	/**
	 * 得到后两个小时
	 * @return
	 */
	public static String getNextHour(){
		long curren = System.currentTimeMillis();
		curren += 120 * 60 * 1000;
		Date da = new Date(curren);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(da);
	}
	
	/**
	 * 得到指定日期的前一天日期
	 * @param date 
	 * @return
	 * @author JiangBo
	 */
	public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    } 
	/**
	 * 得到指定天数的日期
	 * @param day
	 * @return
	 * @author zhangXin
	 * @date 2015-11-11
	 */
	public static String getDateByDay(int day) {
		Calendar d = Calendar.getInstance();
		Format format=new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyy_MM_dd);
		if(day == 0){
			return format.format(d.getTime());
		}
		d.add(Calendar.DATE, day);
		return format.format(d.getTime());
	}
	
	/**
	 * 格式化日期字符串到指定格式
	 * @author wangcong
	 * @data 2015年12月22日上午11:11:00
	 */
	public static String formatBeaingAtDay(String day, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_yyyy_MM_dd);
		Date parse;
		try {
			parse = sdf.parse(day);
			sdf = new SimpleDateFormat(format);
			return sdf.format(parse);
		} catch (ParseException e) {
			e.printStackTrace();
			return day;
		}
	}
	
	 /** 
	 * @author:heBingXing
	 * @date 2017年5月12日 上午11:37:41 
	 * @param itemDate 目标时间
	 * @param format 格式
	 * @return String 返回类型
	 * @description: TODO 格式化时间字符串 成自定义的格式
	 * @version V1.0    
	 */
	public static String formatItemDateToSelfFormat(String itemDate,String format){
		SimpleDateFormat sdf=null;
		try {
			sdf = new SimpleDateFormat(format);
			return sdf.format(sdf.parse(itemDate));
		} catch (Exception e) {
			e.printStackTrace();
			return itemDate;
		}
	}
	
	/**
	 * 检查是否在允许时间范围内
	 * @param now
	 * @param beginTime
	 * @param endTime
	 * @return true-在允许范围内
	 */
	public static boolean checkTime(Date now, Date beginTime, Date endTime){
		if(now.before(beginTime) || now.after(endTime)){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.format(DateUtil.parse("20150629", DateUtil.DATE_FORMAT_yyyyMMdd),DateUtil.DATE_FORMAT_yyyy_MM_dd));
		System.out.println(DateUtil.formatBeaingAtDay("2015-12-22 12:23:11",DateUtil.DATE_BEGIN_AT_DAY));
		System.out.println(DateUtil.formatBeaingAtDay("2015-12-22 11:01:33",DateUtil.DATE_END_AT_DAY));
	}
}

package com.bc.pmpheep.back.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
public class DateUtils{
  private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String FORMAT_14 = "yyyyMMddHHmmss";

  public static String getCurrentTimeAsString()
  {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
  }

  public static String getCurrentTimeAs14String()
  {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
  }

  public static String dateToString(Date date)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
  }

  public static String dateToString(Date date, String format)
  {
    return new SimpleDateFormat(format).format(date);
  }

  public static Date parseToDateTime(String strDate, String pattern)
  {
    try
    {
      return ((org.apache.commons.lang.StringUtils.isBlank(strDate)) ? null : 
        new SimpleDateFormat(pattern).parse(strDate));
    }
    catch (ParseException localParseException) {
    }
    return null;
  }

  public static String parseStrDateTimeToString(String strDate, String pattern, String format)
  {
    return dateToString(parseToDateTime(strDate, pattern), format);
  }

  public static long getDateMesl(Date date)
  {
    long mselDate = 3224615562701176832L;
    mselDate = date.getTime();
    return mselDate;
  }

  public static long getDateHaoMiao(String dateStr, String formate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(formate);
    long mseldate = 3224615562701176832L;
    try {
      mseldate = sdf.parse(dateStr).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return mseldate;
  }

  public static boolean isToDay(String dateStr, String formate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(formate);
    String todayStr = sdf.format(new Date());

    return ((todayStr != null) && (!(todayStr.equals(""))) && (dateStr != null) && 
      (!(dateStr.equals(""))) && (dateStr.equals(todayStr)));
  }

  public static Date getDate(int year, int month, int day)
  {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, day, 0, 0, 0);
    return cal.getTime();
  }

  public static Date getDate(Date date, int hour, int min, int sec)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(10, hour);
    cal.set(12, min);
    cal.set(13, sec);
    return cal.getTime();
  }

  public static boolean isEndOfTheMonth(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int maxDay = cal.getActualMaximum(5);

    return (cal.get(5) == maxDay);
  }

  public static boolean isEndOfTheYear(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    return ((cal.get(2) + 1 == 12) && 
      (cal.get(5) == 31));
  }

  public static final int getLastDayOfTheMonth(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    return cal.getActualMaximum(5);
  }

  public static final boolean isStartOfTheMonth(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    return (cal.get(5) == 1);
  }

  public static final int getYear(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(1);
  }

  public static final int getMonth(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(2);
  }

  public static final int getDay(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(5);
  }

  public static Date addDaysToDate(Date date, int numDays)
  {
    if (date == null)
      return null;

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, numDays);
    return c.getTime();
  }

  public static Date addHoursToDate(Date date, int numHours)
  {
    if (date == null) {
      return null;
    }

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(11, numHours);

    return c.getTime();
  }

  public static Date addMinutesToDate(Date date, int numMins)
  {
    if (date == null) {
      return null;
    }

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(12, numMins);

    return c.getTime();
  }

  public static Date addMonthsToDate(Date date, int numMonths)
  {
    if (date == null)
      return null;

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(2, numMonths);
    return c.getTime();
  }

  public static Date addYearsToDate(Date date, int numYears)
  {
    if (date == null)
      return null;

    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(1, numYears);
    return c.getTime();
  }

  public static boolean isLeap(Date d)
  {
    int y = getYear(d);
    return (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0));
  }

  public static int hqxcts(String s1, String s2, String format)
  {
    SimpleDateFormat df = new SimpleDateFormat(format);
    Date d1 = null;
    Date d2 = null;
    try {
      d1 = df.parse(s1);
      d2 = df.parse(s2);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int betweenYears = c2.get(1) - c1.get(1);
    int betweenDays = c2.get(6) - 
      c1.get(6);
    for (int i = 0; i < betweenYears; ++i) {
      c1.set(1, c1.get(1) + 1);
      betweenDays += c1.getMaximum(6);
    }
    return betweenDays;
  }


  public static String getFirstDayOfWeek()
  {
    Calendar monday = Calendar.getInstance();
    return dateToString(getADayOfWeek(monday, 2).getTime(), "yyyyMMdd");
  }

  public static String getLastDayOfWeek()
  {
    Calendar monday = Calendar.getInstance();
    return dateToString(getADayOfWeek(monday, 1).getTime(), "yyyyMMdd");
  }

  public static Calendar getADayOfWeek(Calendar day, int dayOfWeek)
  {
    int week = day.get(7);
    if (week == dayOfWeek)
      return day;
    int diffDay = dayOfWeek - week;
    if (week == 1)
      diffDay -= 7;
    else if (dayOfWeek == 1)
      diffDay += 7;

    day.add(5, diffDay);
    return day;
  }
/**
 * 获取当前日期，例如20170725
 * @return
 */
  public static String getCurrentDateAsString()
  {
    return new SimpleDateFormat("yyyyMMdd").format(new Date());
  }
  
  public static void main(String[] args) throws ParseException
  {
    System.out.println(dateToString(parseToDateTime("201201", "yyyy-MM-dd HH:mm:ss")));
  }
}
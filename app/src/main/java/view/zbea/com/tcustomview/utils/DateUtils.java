package view.zbea.com.tcustomview.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具类
 */
public class DateUtils
{
    private static final int YEAR = 365 * 24 * 60 * 60;// 年
    private static final int MONTH = 30 * 24 * 60 * 60;// 月
    private static final int DAY = 24 * 60 * 60;// 天
    private static final int HOUR = 60 * 60;// 小时
    private static final int MINUTE = 60;// 分钟

    public static long getUnixStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 得到昨天的日期
     *
     * @return
     */
    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = sdf.format(calendar.getTime());
        return yesterday;
    }

    /**
     * 得到今天的日期
     *
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp
     * @param patternStr yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String timeStampToStr(long timeStamp,String patternStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(patternStr);
        String date = sdf.format(timeStamp * 1000);
        return date;
    }


    /**
     * 得到时间  HH:mm:ss
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String getTime(long timeStamp, String format) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }


    public static String getDescriptionTimeFromTimestamp(Date date) {
        long timestamp = date.getTime();
        long currentTime = System.currentTimeMillis();
        long timeGap = Math.abs(currentTime - timestamp) / 1000;// 与现在时间相差秒数

        String timeStr = null;
//        if (timeGap > YEAR) {
//            timeStr = timeGap / YEAR + "年前";
//        } else if (timeGap > MONTH) {
//            timeStr = timeGap / MONTH + "个月前";
//        } else
        if (timeGap > DAY) {// 1天以上

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            Date time1 = calendar.getTime();
            System.out.println("(time1.getTime()) = " + (time1.getTime()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String yesterday = sdf.format(calendar.getTime());
            try {
                Date yesterdayDate = sdf.parse(yesterday);
                System.out.println("yesterdayDate.getTime() = " + yesterdayDate.getTime());
                long stamp=(currentTime-yesterdayDate.getTime())/1000;
                System.out.println("(currentTime-yesterdayDate.getTime()) = " + (currentTime-yesterdayDate.getTime()));


//            System.out.println("time = " + time);
//            timeStr = timeGap / DAY + "天前";
            } catch (ParseException e) {
                e.printStackTrace();
            }
                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
                SimpleDateFormat yestodaydate = new SimpleDateFormat("HH:mm");
                String time = format.format(date);
                timeStr =time;
            System.out.println("yesterday = " + yesterday);


        } else if (timeGap > HOUR) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            Date time1 = calendar.getTime();
            System.out.println("(time1.getTime()) = " + (time1.getTime()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String yesterday = sdf.format(calendar.getTime());
            try {
                Date yesterdayDate = sdf.parse(yesterday);
                System.out.println("yesterdayDate.getTime() = " + yesterdayDate.getTime());
                long stamp=(currentTime-yesterdayDate.getTime())/1000;
                System.out.println("(currentTime-yesterdayDate.getTime()) = " + (currentTime-yesterdayDate.getTime()));


                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
                SimpleDateFormat yestodaydate = new SimpleDateFormat("HH:mm");
                String time = format.format(date);
                timeStr = stamp >= DAY && timeGap < DAY*2? "昨天 " + yestodaydate.format(date) :
                        timeGap > HOUR&&timeGap<DAY?timeGap / HOUR + "小时前":time;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("yesterday = " + yesterday);
//
//
//
//
//            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 格式化
     * @param timeStamp
     * @return
     */
    public static Date formatDate(String timeStamp) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            date=sdf.parse(timeStamp);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取剩余天数(大于一天加一)
     * @param date
     */
    public static int getDateDays(String date)
    {
        Date mDate = new Date();
        long diff = formatDate(date).getTime()-mDate.getTime()>0?((formatDate(date).getTime()-mDate.getTime()))/1000:0;
        int days=diff %DAY>0?1:0;
        return ((int)diff) /DAY+days;
    }

    /**
     * 剩余时间倒计时(大于一天加一)
     * @param date
     */
    public static String getDateDiff(String date)
    {
        String timeStr;
        Date mDate = new Date();
        long diff = formatDate(date).getTime()-mDate.getTime()>0?((formatDate(date).getTime()-mDate.getTime())/1000):0;
        if (diff >=DAY*3)
        {
            return date.split(" ")[0];
        }
        else if (diff >=DAY)
        {
            int days=diff%DAY>0?1:0;
            return "仅剩"+((int)diff/DAY+days)+"天";
        }
        else
        {
            if (diff > HOUR)
            {
                int time=diff%HOUR>0?1:0;
                timeStr = "仅剩"+(diff / HOUR+time) + "小时";
            } else if (diff > MINUTE)
            {
                int minute=diff%MINUTE>0?1:0;
                timeStr = "仅剩"+(diff / MINUTE+minute)+ "分钟";
            } else
            {// 1秒钟-59秒钟
                timeStr = "不足一分钟";
            }
            return timeStr;
        }
    }
}

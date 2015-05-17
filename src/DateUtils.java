import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Date utils. Provide helpful methods to process date time data
 */
public class DateUtils {
    /**
     * The constant DATE_FORMAT.
     */
    public static String DATE_FORMAT = "dd/MM/yy";
    /**
     * The constant TIME_FORMAT.
     */
    public static String TIME_FORMAT = "HH:mm";

    /**
     * Get time after 2 hours of current time.
     *
     * @param time the time
     * @return the date
     */
    public static Date getTimeAfter2Hours(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR, 2);
        cal.add(Calendar.MILLISECOND, 1);
        return cal.getTime();
    }

    /**
     * Get time after 15 minutes of current time.
     *
     * @param time the time
     * @return the date
     */
    public static Date getTimeAfter15Mins(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, 15);
        cal.add(Calendar.MILLISECOND, 1);
        return cal.getTime();
    }

    /**
     * Get time of 3am of today.
     *
     * @param time the time
     * @return the date
     */
    public static Date getTimeAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Get time of 3am of tomorrow.
     *
     * @param time the time
     * @return the date
     */
    public static Date getTimeTomorrowAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeAt3(time));
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
        return cal.getTime();
    }

    /**
     * Get time of 3am of yesterday.
     *
     * @param time the time
     * @return the date
     */
    public static Date getTimeYesterdayAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeAt3(time));
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        return cal.getTime();
    }

    /**
     * Convert date time to formatted string
     *
     * @param time the time
     * @return the formatted string
     */
    public static String formatDateTime(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        return formatter.format(time);
    }

    /**
     * Get date formatter
     *
     * @return the simple date format
     */
    public static SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat(DATE_FORMAT);
    }

    /**
     * Get time formatter
     *
     * @return the simple date format
     */
    public static SimpleDateFormat getTimeFormat(){
        return new SimpleDateFormat(TIME_FORMAT);
    }

    /**
     * Short hand method for getting current time.
     *
     * @return the date
     */
    public static Date getTimeNow(){
        return Calendar.getInstance().getTime();
    }

    /**
     * Convert string to time.
     *
     * @param date the date string
     * @param time the time string
     * @return the date
     */
    public static Date getTime(String date, String time){
        try{
            String combinedDateTime = String.format("%s %s", date, time);
            SimpleDateFormat formatter = new SimpleDateFormat(String.format("%s %s", DATE_FORMAT, TIME_FORMAT));

            return formatter.parse(combinedDateTime);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * Convert string to date
     *
     * @param date the input string
     * @return the date
     */
    public static Date getDate(String date){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(String.format(DATE_FORMAT));

            return formatter.parse(date);
        }catch (Exception ex){
            return null;
        }
    }
}

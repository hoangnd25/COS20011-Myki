import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String DATE_FORMAT = "dd/MM/yy";
    public static String TIME_FORMAT = "HH:mm";

    public static Date getTimeAfter2Hours(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR, 2);
        cal.add(Calendar.MILLISECOND, 1);
        return cal.getTime();
    }

    public static Date getTimeAfter15Mins(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, 15);
        cal.add(Calendar.MILLISECOND, 1);
        return cal.getTime();
    }

    public static Date getTimeAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.HOUR_OF_DAY, 3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getTimeTomorrowAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeAt3(time));
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
        return cal.getTime();
    }

    public static Date getTimeYesterdayAt3(Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeAt3(time));
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        return cal.getTime();
    }

    public static String formatDateTime(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        return formatter.format(time);
    }

    public static SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat(DATE_FORMAT);
    }

    public static SimpleDateFormat getTimeFormat(){
        return new SimpleDateFormat(TIME_FORMAT);
    }

    public static Date getTimeNow(){
        return Calendar.getInstance().getTime();
    }

    public static Date getTime(String date, String time){
        try{
            String combinedDateTime = String.format("%s %s", date, time);
            SimpleDateFormat formatter = new SimpleDateFormat(String.format("%s %s", DATE_FORMAT, TIME_FORMAT));

            return formatter.parse(combinedDateTime);
        }catch (Exception ex){
            return null;
        }
    }

    public static Date getDate(String date){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(String.format(DATE_FORMAT));

            return formatter.parse(date);
        }catch (Exception ex){
            return null;
        }
    }
}

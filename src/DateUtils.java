import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

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

    public static String formatDateTime(Date time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        return formatter.format(time);
    }
}

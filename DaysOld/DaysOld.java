/**
 * Assignment 1: Using standard libraries <br />
 * Calculate age in days
 * 
 * YongQuan Zhang
 * 
 * Student ID: 1515873
 */
import java.util.Calendar;
import java.util.Locale;
public class DaysOld {
    private static int year;
    private static int month;
    private static int day;

    private static String dateString(Calendar date){
        return String.format(
            "%s %s %s",
            date.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH),
            date.get(Calendar.DAY_OF_MONTH),
            date.get(Calendar.YEAR)
        );
    }
    /**
     * Calculate how many days between today and the date, and them out
     * @param birthday      {@code String} The start date
     */
    public static void days(String birthday) {
        // TODO: Assignment 1 -- write your code here.
        Calendar rightNow = Calendar.getInstance();
        Calendar inputDate = Calendar.getInstance();
        String[] splits = birthday.split("-");
        year = Integer.parseInt(splits[0]);
        month = (Integer.parseInt(splits[1])-1);
        day = Integer.parseInt(splits[2]);
        inputDate.set(year,month,day);
        System.out.printf("Birthday: %s; ", dateString(inputDate));
        if(inputDate.after(rightNow)){
            System.out.printf("today: %s -- Wrong birthday!\n",dateString(rightNow));
        }
        else{
            int daysOld = 0;
            while(inputDate.before(rightNow)){
                inputDate.add(Calendar.DAY_OF_MONTH,1);
                daysOld++;
            }
            System.out.printf("today: %s -- You are %d days old.\n",dateString(rightNow),daysOld);
           
        }

    }
    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        days("2000-1-1");
        days("3000-1-1");           // This is a wrong birthday
        // Add your birthday
        days("1996-5-27");
        days("2000-6-31");
    }

}

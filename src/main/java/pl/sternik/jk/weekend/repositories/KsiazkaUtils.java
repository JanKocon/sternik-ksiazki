package pl.sternik.jk.weekend.repositories;

import pl.sternik.jk.weekend.entities.Ksiazka;

import java.util.Calendar;
import java.util.Date;

public class KsiazkaUtils {
    public static Date getDateWithYearAndMonthForDay(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static void showDatabase(Ksiazka[] data)
    {
        for(int i=0;i<data.length;i++)
        {
            System.out.println(i + " : " + data[i]);
        }
    }

}

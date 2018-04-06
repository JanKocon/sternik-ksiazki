package pl.sternik.jk.weekend.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sternik.jk.weekend.entities.Ksiazka;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KsiazkaUtils {

    private static Logger logger = LoggerFactory.getLogger(KsiazkaUtils.class);
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
            logger.info(data[i].toString());
        }
    }
    public static void showDatabase(ArrayList<Ksiazka> arrayList)
    {
        for(int i=0;i<arrayList.size();i++)
        {
            logger.info(arrayList.get(i).toString());
        }
    }

}

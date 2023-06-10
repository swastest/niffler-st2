package guru.qa.niffler.page.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;


public class Converter {
   public static Date convertStringDateFromTableToDate(String dateFromTable) {
            DateFormat tableDateFormat = new SimpleDateFormat("dd MMM yy", new Locale("en", "EN"));
       try {
           return tableDateFormat.parse(dateFromTable);
       } catch (ParseException e) {
           throw new RuntimeException(e.getMessage());
       }

   }
    public static String convertDateToUiTableFormat(Date date) {
        DateFormat tableDateFormat = new SimpleDateFormat("dd MMM yy", new Locale("en", "EN"));
        return tableDateFormat.format(date);
    }
}

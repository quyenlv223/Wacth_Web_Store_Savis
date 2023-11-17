package watch.store.smart_web.util;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Component
public class ConvertUtil {
    public Date strToDate(String startDate, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(startDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public String moneyToStringFormat(Long price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}

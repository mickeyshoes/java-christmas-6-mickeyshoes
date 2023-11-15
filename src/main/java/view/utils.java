package view;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class utils {
    public static final DecimalFormat priceFormat = new DecimalFormat("###,###");
    public static String checkTargetExist(String target, String replaceWords){
        if(target.isBlank() || target.isEmpty() || target == null){
            return replaceWords;
        }
        return target;
    }

    public static LocalDate createDate(int month, int date){
        LocalDate current = LocalDate.now();
        return LocalDate.of(current.getYear(), month, date);
    }

    public static boolean isMatchString(String regexPattern, String input){
        return Pattern.matches(regexPattern, input);
    }

    public static String valueToFormatString(int value){
        return priceFormat.format(value);
    }

}

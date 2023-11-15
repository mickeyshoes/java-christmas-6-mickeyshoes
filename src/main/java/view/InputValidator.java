package view;

import java.util.List;

import static order.OrderException.INVALID_ORDER_FROM_USER;
import static order.OrderValidator.MINIMUN_COUNT_OF_FOODS;
import static order.OrderValidator.foodCountIsAvailable;
import static view.ViewException.INVALID_DATE;
import static view.utils.isMatchString;

public class InputValidator {

    public static final int MINIMUM_DAY = 1;
    public static final int MAXIMUM_DAY = 31;
    public static final String INPUT_PATTERN = "[가-힣]+-\\d+";

    public static boolean canParseInt(String input){
        try{
            Integer.parseInt(input);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return true;
    }

    public static boolean canConvertToDate(int input){
        if(input < MINIMUM_DAY || input > MAXIMUM_DAY){
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return true;
    }

    public static boolean canParsingFormat(String[] inputs){
        for(String input : inputs){
            if(!isMatchString(INPUT_PATTERN, input)){
                throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
            }
        }
        return true;
    }

    public static boolean orderCountOverMinimumLimit(List<Integer> orderCounts){
        long filtered = orderCounts.stream()
                .filter(orderCount -> orderCount>MINIMUN_COUNT_OF_FOODS)
                .count();
        try{
            Math.toIntExact(filtered);
            orderCounts.stream().peek(orderCount -> foodCountIsAvailable(orderCount));
        }catch (ArithmeticException e){
            throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
        }catch (IllegalStateException e){
            throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
        }
        return true;
    }

}

package order;

import menu.MenuManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static food.FoodValidator.foodNameIsDuplicate;
import static order.Order.MAX_ORDER_COUNT;
import static order.OrderException.*;

public class OrderValidator {

    // 나중에 이벤트 생기면 그쪽으로 옮겨야 할듯
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final int MINIMUN_COUNT_OF_FOODS = 0;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int LAST_DAY_OF_MONTH = 31;

    //메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요
    public static boolean foodCountIsAvailable(int totalFoodCount){
        if(totalFoodCount <0 || totalFoodCount > MAX_ORDER_COUNT){
            throw new IllegalStateException(ORDER_FOOD_NOT_OVER_MAXIMUM.getMessageWithArgs(MAX_ORDER_COUNT));
        }
        return true;
    }

    //중복 메뉴를 입력한 경우
    //고객이 메뉴판에 없는 메뉴를 입력하는 경우
    public static boolean checkFoodNameIsValid(List<String> foodNames, MenuManager menuManager){
        try{
            foodNameIsDuplicate(foodNames);
            foodNames.stream()
                    .peek(food -> menuManager.getFoodFromMenu(food));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
        }
        return true;
    }

    public static boolean checkFoodCountIsValid(List<Integer> foodCounts){
        Optional<Integer> totalCount = foodCounts.stream()
                .reduce(Integer::sum);
        if(!totalCount.isPresent()){
            throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
        }
        foodCountIsAvailable(totalCount.get());
        return true;
    }

    //input 에서 들어온 valid check 니까 1일부터 31일 사이인지 체크해야 하는데 달도 고려해야한다.
    //LocalDate class에서 지원하는거 있는지 확인
    public static boolean orderDateInRange(LocalDate orderDate){
        int date = orderDate.getDayOfMonth();
        if(date < FIRST_DAY_OF_MONTH || date > LAST_DAY_OF_MONTH){
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return true;
    }

    public static boolean foodCountOverZero(int count){
        if(count <= MINIMUN_COUNT_OF_FOODS){
            throw new IllegalArgumentException(OFFER_FOOD_MUST_OVER_MINUMUM.getMessageWithArgs(MINIMUN_COUNT_OF_FOODS));
        }
        return true;
    }


    public static boolean canOrder(){


        return true;
    }


}

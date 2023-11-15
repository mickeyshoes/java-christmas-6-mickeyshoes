package order;

import menu.MenuManager;

import java.util.List;
import java.util.Optional;

import static food.FoodValidator.foodNameIsDuplicate;
import static order.Order.MAX_ORDER_COUNT;
import static order.OrderException.*;

public class OrderValidator {

    public static final int MINIMUN_COUNT_OF_FOODS = 0;

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

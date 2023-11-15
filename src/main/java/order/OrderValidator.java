package order;

import food.Category;
import food.OrderFood;
import menu.MenuManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static food.FoodValidator.foodNameIsDuplicate;
import static order.Order.MAX_ORDER_COUNT;
import static order.OrderException.*;

public class OrderValidator {

    public static final int MINIMUN_COUNT_OF_FOODS = 0;

    public static boolean foodCountIsAvailable(int totalFoodCount){
        if(totalFoodCount <=0 || totalFoodCount > MAX_ORDER_COUNT){
            throw new IllegalStateException(ORDER_FOOD_NOT_OVER_MAXIMUM.getMessageWithArgs(MAX_ORDER_COUNT));
        }
        return true;
    }

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


    public static boolean isOrderHasOnlyDrink(List<OrderFood> orderFoods){

        List<OrderFood> filterFoods = orderFoods.stream()
                .filter(orderFood -> orderFood.getCategory() == Category.DRINK)
                .collect(Collectors.toList());

        if(filterFoods.size() == orderFoods.size()){
            throw new IllegalArgumentException(INVALID_ORDER_FROM_USER.getMessage());
        }

        return true;
    }


}

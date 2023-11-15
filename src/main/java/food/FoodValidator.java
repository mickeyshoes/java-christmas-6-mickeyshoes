package food;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static food.FoodException.*;

public class FoodValidator {

    public static final int MINIMUM_COST = 0;
    public static final int MAXIMUM_COST = 2_100_000_000;
    public static final int MINIMUM_FOOD_NAME_LEN = 1;

    public static boolean priceIsIn(int price){
        if(price <= MINIMUM_COST || price >= MAXIMUM_COST){
            throw new IllegalStateException(
                    FOOD_PRICE_MUST_IN_RANGE.getMessageWithArgs(MINIMUM_COST, MAXIMUM_COST));
        }
        return true;
    }

    public static boolean nameIsNull(String name){
        if(name.isBlank() || name.isEmpty() || name == null){
            throw new IllegalStateException(
                    FOOD_NAME_LENGTH.getMessageWithArgs(MINIMUM_FOOD_NAME_LEN));
        }
        return false;
    }

    public static boolean foodNameIsDuplicate(List<String> foodsName){
        Set<String> distinctFood = new HashSet<>();
        Optional<String> duplicateFood = foodsName.stream()
                .filter(foodName -> distinctFood.add(foodName) == false)
                .findFirst();
        if(duplicateFood.isPresent()){
            String foodName = duplicateFood.get();
            throw new IllegalArgumentException(FOOD_NAME_IS_DUPLICATED.getMessageWithArgs(foodName));
        }
        return false;
    }


}

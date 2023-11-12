package menu;

import food.Food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static food.FoodException.FOOD_IS_NOT_EXIST_IN_MENU;
import static food.FoodException.FOOD_NAME_IS_DUPLICATED;
import static food.FoodValidator.foodNameIsDuplicate;

public class Menu {

    private final Map<String, Food> menuBoard;
    public Menu(List<Food> foods){
        menuBoard = menuBoardinit(foods);
    }

    private Map<String, Food> menuBoardinit(List<Food> foods){
        return foods.stream()
                .collect(
                        Collectors.toMap(
                                food -> food.getName(),
                                food-> food
                        ));
    }

    public boolean addFood(Food food){
        menuBoard.putIfAbsent(food.getName(), food);
        return true;
    }

    public boolean removeFood(String foodName){
        menuBoard.remove(foodName);
        return true;
    }

    public boolean hasFood(String foodName){
        return menuBoard.containsKey(foodName);
    }

    public Food getFood(String foodName){
        return menuBoard.get(foodName);
    }
}

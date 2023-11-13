package menu;

import food.Food;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

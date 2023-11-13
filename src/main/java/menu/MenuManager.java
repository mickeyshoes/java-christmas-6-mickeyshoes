package menu;

import food.Food;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static menu.MenuException.FOOD_IS_NOT_EXIST_IN_MENU;
import static food.FoodException.FOOD_NAME_IS_DUPLICATED;
import static food.FoodValidator.foodNameIsDuplicate;
import static menu.MenuValidator.foodIsExistInMenu;

public class MenuManager {

    private static final MenuManager manager = new MenuManager();

    private Menu menu;

    private MenuManager(){ }

    public static MenuManager getInstance(){
        return manager;
    }

    public void initMenu(List<Food> foods){
        foodNameIsDuplicate(foods.stream()
                .map(Food::getName)
                .collect(Collectors.toList()));
        this.menu = new Menu(foods);
    }

    public boolean addFoodToMenu(Food food){
        if(foodIsExistInMenu(menu, food.getName())){
           throw new IllegalArgumentException(FOOD_NAME_IS_DUPLICATED.getMessageWithArgs(food.getName()));
        }
        menu.addFood(food);
        return true;
    }

    public boolean deleteFoodFromMenu(String foodName){
        if(!foodIsExistInMenu(menu, foodName)){
            throw new IllegalArgumentException(FOOD_IS_NOT_EXIST_IN_MENU.getMessageWithArgs(foodName));
        }
        menu.removeFood(foodName);
        return true;
    }

    public Food getFoodFromMenu(String foodName){
        if(!foodIsExistInMenu(menu, foodName)){
            throw new IllegalArgumentException(FOOD_IS_NOT_EXIST_IN_MENU.getMessageWithArgs(foodName));
        }
        return menu.getFood(foodName);
    }

}

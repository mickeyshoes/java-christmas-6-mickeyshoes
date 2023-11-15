package food;

import java.util.ArrayList;
import java.util.List;

import static food.Category.*;
import static food.Category.DRINK;

public class FoodGenerator {

    private static final FoodGenerator foodGenerator = new FoodGenerator();

    private FoodGenerator(){}

    public static FoodGenerator getInstance(){
        return foodGenerator;
    }

    public List<Food> initFoods(List<Category> categories, List<List<String>> foods, List<List<Integer>> prices){
        List<Food> foodsForMenu = new ArrayList<>();
        for(int index=0; index<categories.size(); index++){
            Category category = categories.get(index);
            List<String> foodName = foods.get(index);
            List<Integer> price = prices.get(index);
            foodsForMenu.addAll(foodGenerator.generateFoodsByCategory(category, foodName, price));
        }
        return foodsForMenu;
    }

    public List<Food> generateFoodsByCategory(Category category, List<String> foodNames, List<Integer> prices){
        List<Food> foods = new ArrayList<>();
        for(int idx=0; idx<foodNames.size(); idx++){
            String foodName = foodNames.get(idx);
            int price = prices.get(idx);
            foods.add(new Food(category, foodName, price));
        }
        return foods;
    }
}

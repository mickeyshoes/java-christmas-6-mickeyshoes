package order;

import food.Food;
import food.OrderFood;
import menu.MenuManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static order.OrderValidator.*;

public class OrderGenerator {

    private static final OrderGenerator generator = new OrderGenerator();
    private static final MenuManager menuManager = MenuManager.getInstance();

    private OrderGenerator(){}

    public static OrderGenerator getInstance(){
        return generator;
    }

    public Order makeOrder(LocalDate orderDate, List<String> foodNames, List<Integer> foodCounts){
        orderDateInRange(orderDate);
        return new Order(orderDate, makeOrderFoods(foodNames, foodCounts));
    }

    public List<OrderFood> makeOrderFoods(List<String> foodNames, List<Integer> foodCounts){
        checkFoodNameIsValid(foodNames, menuManager);
        checkFoodCountIsValid(foodCounts);

        List<OrderFood> userOrderFoods = new ArrayList<>();
        for(int idx=0; idx<foodNames.size(); idx++){
            String foodName = foodNames.get(idx);
            int foodCount = foodCounts.get(idx);
            userOrderFoods.add(makeOrderFood(foodName, foodCount));
        }
        return userOrderFoods;
    }

    private OrderFood makeOrderFood(String foodName, int foodCount){
        Food targetFood = menuManager.getFoodFromMenu(foodName);
        return new OrderFood(targetFood, foodCount);
    }
}

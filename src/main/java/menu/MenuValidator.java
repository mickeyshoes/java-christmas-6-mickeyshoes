package menu;

import static food.FoodValidator.nameIsNull;
import static menu.MenuException.MENU_IS_NOT_INITIALIZE;

public class MenuValidator {

    public static boolean foodIsExistInMenu(Menu menu, String foodName){
        if(menu == null){
            throw new IllegalStateException(MENU_IS_NOT_INITIALIZE.getMessage());
        }
        nameIsNull(foodName);
        return menu.hasFood(foodName);
    }
}

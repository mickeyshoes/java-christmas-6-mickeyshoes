package menu;

import static food.FoodValidator.nameIsNull;
import static menu.MenuException.MENU_IS_NOT_INITIALIZE;

public class MenuValidator {

    public static boolean foodIsExistInMenu(Menu menu, String foodName){
        menuIsExist(menu);
        nameIsNull(foodName);
        return menu.hasFood(foodName);
    }

    public static boolean menuIsExist(Menu menu){
        if(menu == null){
            throw new IllegalStateException(MENU_IS_NOT_INITIALIZE.getMessage());
        }
        return true;
    }
}

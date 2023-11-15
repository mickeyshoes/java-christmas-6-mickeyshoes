package food;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static food.FoodException.FOOD_NAME_LENGTH;
import static food.FoodException.FOOD_PRICE_MUST_IN_RANGE;
import static food.FoodValidator.MAXIMUM_COST;
import static food.FoodValidator.MINIMUM_COST;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class FoodTest {

    @DisplayName("Food 생성 실패 : 이름이 없음")
    @Test
    void generateFoodFailWithStringTest(){
        Category targetCat = Category.DESSERT;
        String name = null;
        int price = 5000;
        assertThatThrownBy(() -> new Food(targetCat, name, price))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(FOOD_NAME_LENGTH.getMessageWithArgs(1));
    }

    @DisplayName("Food 생성 실패 : 가격이 없음")
    @Test
    void generateFoodFailWithIntTest(){
        Category targetCat = Category.DESSERT;
        String name = "초코케이크";
        int price = -5;
        assertThatThrownBy(() -> new Food(targetCat, name, price))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(FOOD_PRICE_MUST_IN_RANGE.getMessageWithArgs(MINIMUM_COST, MAXIMUM_COST));
    }

    @DisplayName("Food 생성 성공")
    @Test
    void generateFoodSuccessTest(){
        Category targetCat = Category.DESSERT;
        String name = "초코케이크";
        int price = 5000;
        assertThatCode(()-> new Food(targetCat, name, price));
    }

    @DisplayName("이름이 같으면 같은 Food 객체")
    @Test
    void checkSameObjectWithName(){
        Food food = new Food(Category.DRINK, "쉐이크", 5000);
        Food sameExpect = new Food(Category.DRINK, "쉐이크", 6000);
        Food falseExpect = new Food(Category.DRINK, "초코쉐이크", 5000);

        assertThat(food.equals(sameExpect)).isTrue();
        assertThat(food.equals(falseExpect)).isFalse();
    }
}

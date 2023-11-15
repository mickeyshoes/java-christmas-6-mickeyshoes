package promotion;

import food.Category;
import food.Food;
import food.OrderFood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static food.Category.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DayWithCategoryDiscountTest {

    @DisplayName("평일 이벤트 대상 및 할인액 확인")
    @Test
    void checkWeekDayWithCategoryDiscount(){
        LocalDate date = LocalDate.of(2023,12,25);
        List<OrderFood> discountFoods = List.of(
                new OrderFood(new Food(DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(MAIN, "스테이크", 50000), 2)
                );

        DayWithCategoryDiscount dayWithCategoryDiscount
                = new DayWithCategoryDiscount("평일 이벤트",
                LocalDate.of(2023,12,1),
                LocalDate.of(2023,12,31),
                PromotionType.DISCOUNT,
                2023,
                Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
                Set.of(MAIN)
        );

        assertThat(dayWithCategoryDiscount.apply(date, discountFoods)).isEqualTo(-2023*2);

        LocalDate overDate = LocalDate.of(2022,12,25);
        List<OrderFood> noDiscountFoods = List.of(
                new OrderFood(new Food(DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(APPETIZER, "케이크", 50000),1)
        );

        assertThat(dayWithCategoryDiscount.apply(date, noDiscountFoods)).isEqualTo(0);
        assertThat(dayWithCategoryDiscount.apply(overDate, noDiscountFoods)).isEqualTo(0);
    }

    @DisplayName("주말 이벤트 대상 및 할인액 확인")
    @Test
    void checkWeekEndWithCategoryDiscount(){
        LocalDate date = LocalDate.of(2023,12,23);
        List<OrderFood> discountFoods = List.of(
                new OrderFood(new Food(DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(APPETIZER, "케이크", 5000), 2),
                new OrderFood(new Food(MAIN, "스테이크", 50000), 2)
        );
        int totalDiscountNum = 1 + 2;

        DayWithCategoryDiscount dayWithCategoryDiscount
                = new DayWithCategoryDiscount("주말 이벤트",
                LocalDate.of(2023,12,1),
                LocalDate.of(2023,12,31),
                PromotionType.DISCOUNT,
                2023,
                Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
                Set.of(APPETIZER, DRINK)
        );

        assertThat(dayWithCategoryDiscount.apply(date, discountFoods)).isEqualTo(-2023*totalDiscountNum);

        LocalDate overDate = LocalDate.of(2022,12,25);
        List<OrderFood> noDiscountFoods = List.of(
                new OrderFood(new Food(MAIN, "스테이크", 50000), 1),
                new OrderFood(new Food(MAIN, "볶음밥", 9000),1)
        );

        assertThat(dayWithCategoryDiscount.apply(date, noDiscountFoods)).isEqualTo(0);
        assertThat(dayWithCategoryDiscount.apply(overDate, noDiscountFoods)).isEqualTo(0);
    }
}

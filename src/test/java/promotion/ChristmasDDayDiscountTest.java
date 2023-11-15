package promotion;

import food.Category;
import food.Food;
import food.OrderFood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChristmasDDayDiscountTest {

    @DisplayName("크리스마스 이벤트 대상 및 할인액 확인")
    @Test
    void checkDateIsTargetOfPromotion(){
        LocalDate date = LocalDate.of(2023,12,23);
        ChristmasDDayDiscount christmasDDayPromotion = new ChristmasDDayDiscount(
                "크리스마스 이벤트",
                LocalDate.of(2023,12,1),
                LocalDate.of(2023,12,31),
                PromotionType.DISCOUNT,
                1000,
                100
        );

        assertThat(christmasDDayPromotion.dateInRange(date)).isTrue();
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(Category.DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(Category.APPETIZER, "케이크", 50000),1)
        );

        assertThat(christmasDDayPromotion.apply(date, orderFoods)).isEqualTo(-3200);
        LocalDate overDate = LocalDate.of(2022,12,24);
        assertThat(christmasDDayPromotion.apply(overDate, orderFoods)).isEqualTo(0);
    }
}

package promotion;

import food.Food;
import food.OrderFood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static food.Category.DRINK;
import static food.Category.MAIN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpecificDaysDiscountTest {

    @DisplayName("특정 일에 할인되는 이벤트 확인")
    @Test
    void checkSpecificDaysDiscount(){
        LocalDate date = LocalDate.of(2023, 12, 22);
        List<OrderFood> discountFoods = List.of(
                new OrderFood(new Food(DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(MAIN, "스테이크", 50000), 2)
        );

        SpecificDaysDiscount specificDaysDiscount
                = new SpecificDaysDiscount("특별 이벤트",
                LocalDate.of(2023,12,01),
                LocalDate.of(2023,12,30),
                PromotionType.DISCOUNT,
                2000,
                List.of(
                        LocalDate.of(2023, 12, 22),
                        LocalDate.of(2023, 12, 23)
                ));

        assertThat(specificDaysDiscount.apply(date, discountFoods)).isEqualTo(-2000);

        LocalDate notInDate = LocalDate.of(2023,12,24);
        assertThat(specificDaysDiscount.apply(notInDate, discountFoods)).isEqualTo(0);

    }
}

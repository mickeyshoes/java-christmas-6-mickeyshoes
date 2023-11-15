package promotion;

import food.Category;
import food.Food;
import food.OfferFood;
import food.OrderFood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static food.Category.DRINK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpecialOfferTest {

    @DisplayName("기간안에 특정 금액 이상 주문시 증정품을 주는 프로모션 동작 확인")
    @Test
    void checkSpecialOffer(){
        LocalDate date = LocalDate.of(2023,12,20);
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(DRINK, "레드와인", 5000), 1),
                new OrderFood(new Food(Category.APPETIZER, "케이크", 50000),1)
        );

        SpecialOffer specialOffer
                = new SpecialOffer("증정품을 드려요",
                LocalDate.of(2023,12,01),
                LocalDate.of(2023,12,30),
                PromotionType.FREEBIE,
                0,
                List.of(
                        new OfferFood(new Food(DRINK, "뿌요뿌요", 500), 1, 5000),
                        new OfferFood(new Food(DRINK, "조니워커블루", 100000), 1, 100000)
                ));

        assertThat(specialOffer.apply(date, orderFoods)).isEqualTo(-500);


        List<OrderFood> muchFoods = List.of(
                new OrderFood(new Food(DRINK, "고급레드와인", 50000), 2),
                new OrderFood(new Food(Category.APPETIZER, "케이크", 50000),1)
        );
        assertThat(specialOffer.apply(date, muchFoods)).isEqualTo(-100000);
        LocalDate overDate = LocalDate.of(2022, 12,25);
        assertThat(specialOffer.apply(overDate, muchFoods)).isEqualTo(0);
    }
}

package order;

import food.Category;
import food.Food;
import food.OfferFood;
import food.OrderFood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import promotion.ChristmasDDayDiscount;
import promotion.PromotionType;
import promotion.SpecialOffer;

import java.time.LocalDate;
import java.util.List;

import static food.Category.DRINK;
import static order.OrderException.INVALID_ORDER_FROM_USER;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class OrderTest {

    @DisplayName("Order 생성 실패 : 드링크만 주문 한 경우")
    @Test
    void generateOrderFailWithOnlyDrinks(){
        LocalDate date = LocalDate.of(2023, 12,29);
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(Category.DRINK, "쉐이크", 5000), 2),
                new OrderFood(new Food(Category.DRINK, "레드와인", 500000), 5)
                );
        assertThatThrownBy(()-> new Order(date, orderFoods))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_FROM_USER.getMessage());
    }

    @DisplayName("Order 생성 성공")
    @Test
    void generateOrder(){
        LocalDate date = LocalDate.of(2023, 12,29);
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(Category.MAIN, "스테이크", 15000), 2),
                new OrderFood(new Food(Category.DRINK, "레드와인", 500000), 5)
        );
        assertThatCode(()-> new Order(date, orderFoods));
    }

    @DisplayName("Order 에 할인 적용")
    @Test
    void checkDiscountPromotion(){
        LocalDate date = LocalDate.of(2023, 12,25);
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(Category.APPETIZER, "초코케이크", 5000), 2),
                new OrderFood(new Food(Category.DRINK, "레드와인", 500000), 5)
        );
        Order order = new Order(date, orderFoods);
        ChristmasDDayDiscount christmasDDayPromotion = new ChristmasDDayDiscount(
                "크리스마스 이벤트",
                LocalDate.of(2023,12,1),
                LocalDate.of(2023,12,31),
                PromotionType.DISCOUNT,
                1000,
                100
        );

        LocalDate dateOver = LocalDate.of(2024,01,5);
        assertThat(christmasDDayPromotion.isTarget(dateOver, orderFoods)).isFalse();

        assertThat(christmasDDayPromotion.isTarget(date, orderFoods)).isTrue();
        order.applyPromotion(christmasDDayPromotion);

        String expectPromotionResults = "크리스마스 이벤트: -3400\n";
        assertThat(order.printPromotionResults()).isEqualTo(expectPromotionResults);

        String expectOrderMenuToString = "초코케이크 2개\n레드와인 5개\n";
        assertThat(order.printOrderMenu()).isEqualTo(expectOrderMenuToString);

        int expectTotalPrice = (500000*5) + (5000*2);
        assertThat(order.calculateTotalPrice() + order.calculateTotalDiscount())
                .isEqualTo(expectTotalPrice-3400);
    }

    @DisplayName("Order 에 증정품 적용")
    @Test
    void checkFreebiesPromotion(){

        LocalDate date = LocalDate.of(2023, 12,25);
        List<OrderFood> orderFoods = List.of(
                new OrderFood(new Food(Category.APPETIZER, "초코케이크", 5000), 2),
                new OrderFood(new Food(Category.DRINK, "레드와인", 500000), 5)
        );

        Order order = new Order(date, orderFoods);
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

        LocalDate dateOver = LocalDate.of(2024,01,5);
        assertThat(specialOffer.isTarget(dateOver, orderFoods)).isFalse();

        assertThat(specialOffer.isTarget(date, orderFoods)).isTrue();
        order.applyPromotion(specialOffer);

        String expectPromotionResults = "증정품을 드려요: -100000\n";
        assertThat(order.printPromotionResults()).isEqualTo(expectPromotionResults);

        String expectOrderMenuToString = "초코케이크 2개\n레드와인 5개\n";
        assertThat(order.printOrderMenu()).isEqualTo(expectOrderMenuToString);

        String expectFreebiesString = "조니워커블루 1개\n";
        assertThat(order.printFreebies()).isEqualTo(expectFreebiesString);

        int expectTotalPrice = (500000*5) + (5000*2);
        assertThat(order.calculateTotalPrice() + order.calculateCostWithoutFreebies())
                .isEqualTo(expectTotalPrice);
    }
}

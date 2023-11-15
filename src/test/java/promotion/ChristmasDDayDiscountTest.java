package promotion;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChristmasDDayDiscountTest {

    @Test
    void test(){
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
    }
}

package promotion;

import food.OfferFood;
import food.OrderFood;

import java.time.LocalDate;
import java.util.List;

public class ChristmasDDayDiscount extends Promotion{

    private final int offPricePerDay;
    public ChristmasDDayDiscount(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice,
                                    int offPricePerDay) {
        super(name, start, end, type, offPrice);
        this.offPricePerDay = offPricePerDay;
    }

    @Override
    public boolean isTarget(LocalDate date, List<OrderFood> orderFoods) {
        return dateInRange(date);
    }

    @Override
    public int discount(LocalDate date, List<OrderFood> orderFoods) {
        int dayCount = date.compareTo(getStart());
        return (dayCount * offPricePerDay) + super.getOffPrice();
    }

    @Override
    public List<OfferFood> offerFreebies(int totalPrice) {
        return null;
    }
}

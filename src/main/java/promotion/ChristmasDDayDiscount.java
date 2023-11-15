package promotion;

import food.OfferFood;
import food.OrderFood;

import java.time.LocalDate;
import java.util.List;

public class ChristmasDDayDiscount extends Promotion{

    private final int offPricePerDay = 100;
    protected ChristmasDDayDiscount(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice) {
        super(name, start, end, type, offPrice);
    }

    @Override
    public boolean isTarget(LocalDate date, List<OrderFood> orderFoods) {
        return super.dateInRange(date);
    }

    @Override
    public int discount(LocalDate date, List<OrderFood> orderFoods) {
        int dayCount = super.getStart().compareTo(date);
        return (dayCount * offPricePerDay) + super.getOffPrice();
    }

    @Override
    public List<OfferFood> offerFreebies(int totalPrice) {
        return null;
    }
}

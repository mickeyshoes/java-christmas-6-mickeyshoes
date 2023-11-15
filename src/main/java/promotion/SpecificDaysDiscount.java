package promotion;

import food.OfferFood;
import food.OrderFood;

import java.time.LocalDate;
import java.util.List;

public class SpecificDaysDiscount extends Promotion{

    private final List<LocalDate> specificDays;
    protected SpecificDaysDiscount(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice,
                                   List<LocalDate> specificDays) {
        super(name, start, end, type, offPrice);
        this.specificDays = specificDays;
    }

    @Override
    public boolean dateInRange(LocalDate date) {
        return specificDays.contains(date);
    }

    @Override
    public boolean isTarget(LocalDate date, List<OrderFood> orderFoods) {
        return this.dateInRange(date);
    }

    @Override
    public int discount(LocalDate date, List<OrderFood> orderFoods) {
        return super.getOffPrice();
    }

    @Override
    public List<OfferFood> offerFreebies(int totalPrice) {
        return null;
    }
}

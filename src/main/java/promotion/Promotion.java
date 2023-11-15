package promotion;

import food.OfferFood;
import food.OrderFood;

import java.time.LocalDate;
import java.util.List;

public abstract class Promotion {
    private final String name;
    private final LocalDate start;
    private final LocalDate end;
    private final PromotionType type;
    private final int offPrice;

    protected Promotion(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
        this.offPrice = offPrice;
    }

    public abstract boolean isTarget(LocalDate date, List<OrderFood> orderFoods);
    public abstract int discount(LocalDate date, List<OrderFood> orderFoods);
    public abstract List<OfferFood> offerFreebies(int totalPrice);
    public int apply(LocalDate date, List<OrderFood> orderFoods){
        if(isTarget(date, orderFoods)){
            return -1 * discount(date, orderFoods);
        }
        return 0;
    }

    public boolean dateInRange(LocalDate date){
        return date.isAfter(start) && date.isBefore(end);
    }

    public String getName() { return name; }
    public LocalDate getStart() { return start; }
    public LocalDate getEnd() { return end; }
    public PromotionType getType() { return type; }
    public int getOffPrice() { return offPrice; }

}

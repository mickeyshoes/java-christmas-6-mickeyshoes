package promotion;

import order.OrderFood;

import java.time.LocalDate;
import java.util.List;

public abstract class Promotion {
    private final String name;
    private final LocalDate start;
    private final LocalDate end;
    private final PromotionType type;

    protected Promotion(String name, LocalDate start, LocalDate end, PromotionType type) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public abstract boolean isTarget(LocalDate date, List<OrderFood> orderFoods);

    public abstract int discount(List<OrderFood> orderFoods);

    public abstract List<OrderFood> getFreebies();
    public String getName() { return name; }
    public PromotionType getType() { return type; }
    //고쳐야함
    public String getNameWithBenefit(){ return String.format("%s: %d", name); }
}

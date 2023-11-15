package food;

import static food.FoodValidator.priceIsIn;
import static order.OrderValidator.foodCountOverZero;

public class OfferFood implements Comparable<OfferFood>{

    private final Food food;
    private final int ea;
    private final int limit;

    public OfferFood(Food food, int ea, int limit) {
        validate(ea, limit);
        this.food = food;
        this.ea = ea;
        this.limit = limit;
    }

    public boolean validate(int ea, int limit){
        return foodCountOverZero(ea)
                && priceIsIn(limit);
    }
    public int getLimit() { return limit; }
    public int calculatePrice(){
        return ea * food.getPrice();
    }

    public String getNameWithEa(){
        return String.format("%s %d개", food.getName(), ea);
    }

    @Override
    public int compareTo(OfferFood o) {
        return Integer.compare(limit, o.limit);
    }
}

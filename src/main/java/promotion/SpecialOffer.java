package promotion;

import food.OfferFood;
import food.OrderFood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecialOffer extends Promotion{
    //food, count, limit
    private final List<OfferFood> offerFoods;
    protected SpecialOffer(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice,
                           List<OfferFood> offerFoods) {
        super(name, start, end, type, offPrice);
        this.offerFoods = new ArrayList<>(offerFoods);
        Collections.sort(offerFoods, Collections.reverseOrder());
    }

    private int getTotalPriceByOrder(List<OrderFood> orderFoods){
        return orderFoods.stream()
                .mapToInt(OrderFood::calculatePrice)
                .sum();
    }

    private boolean canOfferFreebies(int totalOrderPrice){
        return offerFreebieByPolicy(totalOrderPrice)
                .isPresent();
    }

    private Stream<OfferFood> findOfferFoodByCondition(int totalOrderPrice){
        return offerFoods.stream()
                .filter(offerFood -> offerFood.getLimit() >= totalOrderPrice);
    }

    private Optional<OfferFood> offerFreebieByPolicy(int totalOrderPrice){
        return findOfferFoodByCondition(totalOrderPrice)
                .findFirst();
    }

    @Override
    public boolean isTarget(LocalDate date, List<OrderFood> orderFoods) {
        int totalOrderPrice = getTotalPriceByOrder(orderFoods);
        return canOfferFreebies(totalOrderPrice);
    }

    @Override
    public int discount(LocalDate date, List<OrderFood> orderFoods) {
        int totalOrderPrice = getTotalPriceByOrder(orderFoods);
        if(canOfferFreebies(totalOrderPrice)){
            return offerFreebieByPolicy(totalOrderPrice)
                    .get()
                    .calculatePrice();
        }
        return 0;
    }

    @Override
    public List<OfferFood> offerFreebies(int totalPrice) {
        return offerFreebieByPolicy(totalPrice)
                .stream()
                .collect(Collectors.toList());
    }
}

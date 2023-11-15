package order;

import food.OfferFood;
import food.OrderFood;
import promotion.Promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static promotion.PromotionType.FREEBIE;

public class Order {

    public static final int MAX_ORDER_COUNT = 20;

    private LocalDate date;
    private List<OrderFood> foods;

    private List<String> promotionNames;
    private List<Integer> discountCosts;
    private List<OfferFood> freebies;

    public Order(LocalDate orderDate, List<OrderFood> orderFoods){
        this.date = orderDate;
        this.foods = orderFoods;
        promotionNames = new ArrayList<>();
        discountCosts = new ArrayList<>();
        freebies = new ArrayList<>();
    }

    public void applyPromotion(Promotion promotion){
        promotionNames.add(promotion.getName());
        discountCosts.add(promotion.apply(date, foods));
        if(promotion.getType() == FREEBIE){
            applyFreebie(promotion);
        }
    }

    public void applyFreebie(Promotion promotion){
        promotion.offerFreebies(calculateTotalPrice())
                .stream()
                .peek(item -> freebies.add(item));
    }

    public int calculateTotalPrice(){
        return foods.stream()
                .mapToInt(food -> food.calculatePrice())
                .sum();
    }

    public String printOrderMenu(){
        StringBuilder sb = new StringBuilder();
        foods.forEach(
                food -> sb.append(food.getNameWithCount())
        );
        return sb.toString();
    }

}

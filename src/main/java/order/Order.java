package order;

import food.OfferFood;
import food.OrderFood;
import promotion.ChristmasBadge;
import promotion.Promotion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static order.OrderValidator.isOrderHasOnlyDrink;
import static promotion.PromotionType.FREEBIE;

public class Order {

    public static final int MAX_ORDER_COUNT = 20;

    private LocalDate date;
    private List<OrderFood> foods;
    private List<String> promotionNames;
    private List<Integer> discountCosts;
    private List<OfferFood> freebies;

    private ChristmasBadge badge;

    public Order(LocalDate orderDate, List<OrderFood> orderFoods){
        validate(orderFoods);
        this.date = orderDate;
        this.foods = orderFoods;
        promotionNames = new ArrayList<>();
        discountCosts = new ArrayList<>();
        freebies = new ArrayList<>();
    }

    private boolean validate(List<OrderFood> orderFoods){
        isOrderHasOnlyDrink(orderFoods);
        return true;
    }

    public void applyPromotion(Promotion promotion){
        promotionNames.add(promotion.getName());
        discountCosts.add(promotion.apply(date, foods));
        if(promotion.getType() == FREEBIE){
            applyFreebie(promotion);
        }
    }

    public void applyFreebie(Promotion promotion){
        List<OfferFood> promotionFreebies = promotion.offerFreebies(calculateTotalPrice());
        for(OfferFood freebie : promotionFreebies){
            freebies.add(freebie);
        }
    }

    public int calculateTotalPrice(){
        return foods.stream()
                .mapToInt(food -> food.calculatePrice())
                .sum();
    }

    public int calculateTotalDiscount(){
        int sum = 0;
        for(int cost : discountCosts){
            sum += cost;
        }
        return sum;
    }

    public int calculateCostWithoutFreebies(){
        int sum = calculateTotalDiscount();
        for(OfferFood freebie : freebies){
            sum+=freebie.calculatePrice();
        }
        return sum;
    }

    public String printOrderMenu(){
        StringBuilder sb = new StringBuilder();
        foods.forEach(
                food -> sb.append(food.getNameWithCount())
                        .append("\n")
        );
        return sb.toString();
    }

    public String printFreebies(){
        StringBuilder sb = new StringBuilder();
        freebies.forEach(
                freebie -> sb.append(freebie.getNameWithEa())
                        .append("\n")
        );
        return sb.toString();
    }

    public String printPromotionResults(){
        StringBuilder sb = new StringBuilder();
        for(int index=0; index<promotionNames.size(); index++){
            sb.append(
                    String.format("%s: %d", promotionNames.get(index), discountCosts.get(index)))
                    .append("\n");
        }
        return sb.toString();
    }

    public List<OrderFood> getFoods() {
        return new ArrayList<>(foods);
    }

    public ChristmasBadge getBadge() { return badge; }

    public void setBadge(){
        this.badge = ChristmasBadge.calculateBadge(-1 * calculateTotalDiscount());
    }

}

package promotion;

import food.Category;
import food.OfferFood;
import food.OrderFood;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DayWithCategoryDiscount extends Promotion{

    private Set<DayOfWeek> targetDays;
    private Set<Category> targetCategories;

    public DayWithCategoryDiscount(String name, LocalDate start, LocalDate end, PromotionType type, int offPrice,
                                      Set<DayOfWeek> targetDays, Set<Category> targetCategories) {
        super(name, start, end, type, offPrice);
        this.targetDays = targetDays;
        this.targetCategories = targetCategories;
    }

    private boolean hasSameCategories(List<OrderFood> orderFoods){
        for(OrderFood orderFood : orderFoods){
            if(hasSameCategory(orderFood)){
                return true;
            }
        }
        return false;
    }

    private boolean hasSameCategory(OrderFood orderFood){
        return targetCategories.contains(orderFood.getCategory());
    }

    private int getSameCategoryFoodCount(List<OrderFood> orderFoods){
        int count = 0;
        for(OrderFood orderFood : orderFoods){
            if(hasSameCategory(orderFood)){
                count+= orderFood.getCount();
            }

        }
        return count;
    }

    private boolean hasSameDay(LocalDate date){
        return targetDays.contains(date.getDayOfWeek());
    }

    @Override
    public boolean isTarget(LocalDate date, List<OrderFood> orderFoods) {
        return dateInRange(date)
                && hasSameDay(date)
                && hasSameCategories(orderFoods);
    }

    @Override
    public int discount(LocalDate date, List<OrderFood> orderFoods) {
        return getSameCategoryFoodCount(orderFoods) * super.getOffPrice();
    }

    @Override
    public List<OfferFood> offerFreebies(int totalPrice) {
        return null;
    }
}

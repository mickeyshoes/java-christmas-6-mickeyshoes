package promotion;

import food.Category;
import food.Food;
import food.OfferFood;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static food.Category.DESSERT;
import static food.Category.MAIN;
import static java.time.DayOfWeek.*;

public class PromotionGenerator {

    private static final PromotionGenerator promotionGenerator = new PromotionGenerator();
    private final int YEAR = 2023;
    private final int DECEMBER = 12;
    private final int DAY_FIRST = 1;
    private final int CHIRSTMAS = 25;
    private final int DAY_LAST = 31;

    private List<Promotion> promotions;

    private PromotionGenerator(){
        init();
    }

    public static PromotionGenerator getInstance(){
        return promotionGenerator;
    }

    private void init(){
        if(promotions == null){
            promotions = new ArrayList<>();
            for(String name : List.of("christmas", "weekday", "weekend", "specificDays", "specialOffer")){
                generatePromotionByName(name);
            }
        }
    }

    public void generatePromotionByName(String name){

        if(name.equals("christmas")){
            promotions.add(
                generateChristmasPromotion(YEAR, DECEMBER, DAY_FIRST,
                        CHIRSTMAS, 1000, 100)
            );
            return;
        }
        if(name.equals("weekday")){
            promotions.add(
                    generateDayWithCategoryPromotion("평일 할인", YEAR, DECEMBER, DAY_FIRST, DAY_LAST,
                            2023,
                            Set.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY),
                            Set.of(DESSERT))
            );
            return;
        }
        if(name.equals("weekend")){
            promotions.add(
                    generateDayWithCategoryPromotion("주말 할인", YEAR, DECEMBER, DAY_FIRST, DAY_LAST,
                            2023,
                            Set.of(FRIDAY, SATURDAY),
                            Set.of(MAIN))
            );
            return;
        }
        if(name.equals("specificDays")){
            promotions.add(
                    generateSpecificDaysPromotion(YEAR, DECEMBER, DAY_FIRST, DAY_LAST,
                            1000,
                            List.of(
                                    LocalDate.of(YEAR, DECEMBER, 3),
                                    LocalDate.of(YEAR, DECEMBER, 10),
                                    LocalDate.of(YEAR, DECEMBER, 17),
                                    LocalDate.of(YEAR, DECEMBER, 24),
                                    LocalDate.of(YEAR, DECEMBER, CHIRSTMAS),
                                    LocalDate.of(YEAR, DECEMBER, 31)
                            )
                    )
            );
            return;
        }
        return;
    }

    public void generateSpecialOfferEvent(List<Food> giveaways, List<Integer> eas, List<Integer> limits){
        List<OfferFood> offerFoods = new ArrayList<>();
        for(int index = 0; index<giveaways.size(); index++){
            offerFoods.add(
                    new OfferFood(
                            giveaways.get(index),
                            eas.get(index),
                            limits.get(index)
                    )
            );
        }
        promotions.add(generateSpecialOfferPromotion(YEAR, DECEMBER, DAY_FIRST, DAY_LAST, offerFoods));
    }

    private ChristmasDDayDiscount generateChristmasPromotion(int year, int month, int startDate, int endDate,
                                                             int offPrice, int offPricePerDay){
        return new ChristmasDDayDiscount(
                "크리스마스 디데이 할인",
                LocalDate.of(year, month, startDate),
                LocalDate.of(year, month, endDate),
                PromotionType.DISCOUNT,
                offPrice,
                offPricePerDay
        );
    }

    private DayWithCategoryDiscount generateDayWithCategoryPromotion(String name, int year, int month,
                                                                     int startDate, int endDate, int offPrice,
                                                                     Set<DayOfWeek> targetDays,
                                                                     Set<Category> targetCategories){
        return new DayWithCategoryDiscount(name,
                LocalDate.of(year, month, startDate),
                LocalDate.of(year, month, endDate),
                PromotionType.DISCOUNT,
                offPrice,
                targetDays,
                targetCategories
        );
    }

    private SpecificDaysDiscount generateSpecificDaysPromotion(int year, int month, int startDate, int endDate,
                                                               int offPrice, List<LocalDate> specificDays){
        return new SpecificDaysDiscount("특별 할인",
                LocalDate.of(year, month, startDate),
                LocalDate.of(year, month, endDate),
                PromotionType.DISCOUNT,
                offPrice,
                specificDays);
    }

    private SpecialOffer generateSpecialOfferPromotion(int year, int month, int startDate, int endDate,
                                                       List<OfferFood> giveaway){
        return new SpecialOffer(
                "증정 이벤트",
                LocalDate.of(year, month, startDate),
                LocalDate.of(year, month, endDate),
                PromotionType.FREEBIE,
                0,
                new ArrayList<>(giveaway)
        );
    }

    public List<Promotion> getPromotions() { return new ArrayList<>(promotions); }


}

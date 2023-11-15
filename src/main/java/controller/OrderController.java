package controller;

import camp.nextstep.edu.missionutils.Console;
import food.Category;
import food.Food;
import food.FoodGenerator;
import food.OfferFood;
import menu.MenuManager;
import order.Order;
import order.OrderGenerator;
import promotion.*;
import view.View;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static food.Category.*;
import static java.time.DayOfWeek.*;

public class OrderController {
    private View view;
    private MenuManager menuManager;
    private OrderGenerator orderGenerator;
    private FoodGenerator foodGenerator;
    private PromotionGenerator promotionGenerator;
    private List<Promotion> promotions;

    public OrderController(View view) {
        this.view = view;
        this.menuManager = MenuManager.getInstance();
        this.orderGenerator = OrderGenerator.getInstance();
        this.foodGenerator = FoodGenerator.getInstance();
        this.promotionGenerator = PromotionGenerator.getInstance();
        init();
        this.promotions = initEvents();
    }

    public void init(){
        List<Food> foods = initFoods();
        menuManager.initMenu(foods);
    }

    public List<Food> initFoods(){
        List<Category> categories = List.of(APPETIZER, MAIN, DESSERT, DRINK);
        List<List<String>> foods = List.of(
                List.of("양송이수프", "타파스", "시저샐러드"),
                List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"),
                List.of("초코케이크", "아이스크림"),
                List.of("제로콜라", "레드와인", "샴페인"));
        List<List<Integer>> prices = List.of(
                List.of(6000, 5500, 8000),
                List.of(55000, 54000, 35000, 25000),
                List.of(15000, 5000),
                List.of(3000, 60000, 25000));
        return foodGenerator.initFoods(categories, foods, prices);
    }

    public List<Promotion> initEvents(){
        Food giveaway = menuManager.getFoodFromMenu("샴페인");
        int limit = 120_000;
        promotionGenerator.generateSpecialOfferEvent(List.of(giveaway), List.of(1), List.of(limit));
        return promotionGenerator.getPromotions();
    }

    public void start(){
        view.printInitMessage();
        LocalDate date = getDate();
        Order order = makeOrder(date);
        applyPromotion(order, date);
        order.setBadge();
        view.printOrderWithPromotionResults(order);
        exit();
    }

    public LocalDate getDate(){
        LocalDate date = null;
        while(date == null){
            try{
                date = view.printReservationAndGetDate();
            }catch (IllegalArgumentException e){
                view.printError(e.getMessage());
            }
        }
        return date;
    }

    public Order makeOrder(LocalDate date){
        Order order = null;
        while(order == null){
            try{
                Map<String, Integer> orderMap = view.printOrderFoodsAndGet();
                order = orderGenerator.makeOrder(date,
                        orderMap.keySet().stream().toList(),
                        orderMap.values().stream().toList());
            }catch (IllegalArgumentException e){
                view.printError(e.getMessage());
            }
        }
        return order;
    }

    public void applyPromotion(Order order, LocalDate date){
        for(Promotion promotion : promotions){
            if(promotion.isTarget(date, order.getFoods())){
                order.applyPromotion(promotion);
            }
        }
    }

    public void exit(){
        Console.close();
    }
}

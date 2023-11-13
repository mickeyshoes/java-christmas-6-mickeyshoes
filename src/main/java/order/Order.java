package order;

import java.time.LocalDate;
import java.util.List;

public class Order {

    public static final int MAX_ORDER_COUNT = 20;

    private LocalDate date;
    private List<OrderFood> orderFoods;

    public Order(LocalDate orderDate, List<OrderFood> orderFoods){
        this.date = orderDate;
        this.orderFoods = orderFoods;
    }

    private boolean validate(List<OrderFood> orderFoods){
        return true;
    }

    public int calculateTotalPrice(){
        return orderFoods.stream()
                .mapToInt(orderFood -> orderFood.getPrice())
                .sum();
    }

    public String printOrderMenu(){
        StringBuilder sb = new StringBuilder();
        orderFoods.forEach(
                orderFood -> sb.append(orderFood.getNameWithCount())
        );
        return sb.toString();
    }

}

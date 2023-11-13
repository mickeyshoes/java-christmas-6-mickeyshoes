package order;

import food.Food;

public class OrderFood extends Food {

    private int count;
    public OrderFood(Food food, int count) {
        super(food.getCategory(), food.getName(), food.getPrice());
        this.count = count;
    }

    public int calculatePrice(){
        return this.count * this.getPrice();
    }

    public String getNameWithCount(){
        return String.format("%s %d개\n", this.getName(), this.count);
    }
}

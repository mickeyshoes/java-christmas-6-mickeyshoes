package food;

import java.text.DecimalFormat;
import java.util.Objects;

import static food.FoodValidator.nameIsNull;
import static food.FoodValidator.priceIsIn;

public class Food {

    private final Category category;
    private final String name;
    private final int price;

    public Food(Category category, String name, int price){
        validate(name, price);
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public boolean validate(String name, int price){
        return priceIsIn(price) && !nameIsNull(name);
    }

    public Category getCategory() { return category; }

    public String getName() { return name; }

    public int getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

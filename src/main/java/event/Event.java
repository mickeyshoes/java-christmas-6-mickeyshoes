package event;

import java.time.LocalDate;

public abstract class Event {
    public String name;
    public LocalDate start;
    public LocalDate end;

    public abstract int discount();

    //고쳐야함
    public String getNameWithBenefit(){
        return String.format("%s: %d", name, discount());
    }
}

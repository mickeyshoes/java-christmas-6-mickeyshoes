package view;

import order.Order;

import java.time.LocalDate;
import java.util.Map;

public class View {

    private InputView inputView;
    private OutputView outputView;
    private final String errorHeader = "[ERROR]";

    public View(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void printInitMessage(){
        outputView.initMessage();
    }

    public LocalDate printReservationAndGetDate(){
        outputView.guideUserInsertReservationDate();
        return inputView.inputReservationDate();
    }

    public Map<String, Integer> printOrderFoodsAndGet(){
        outputView.guideUserInsertFoodsAndCounts();
        return inputView.inputOrderFoods();
    }

    public void printOrderWithPromotionResults(Order order){
        outputView.printPromotionResults(order);
    }

    public void printError(String message){
        System.out.println(
                String.format("%s %s", errorHeader, message)
        );
    }
}

package view;

import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.util.*;

import static view.InputValidator.*;
import static view.utils.createDate;

public class InputView {

    public static final int FOODNAME = 0;
    public static final int FOODCOUNTS = 1;
    public static final int DECEMBER = 12;
    public static final String SPLIT_REGEX = ",";
    public static final String SPLIT_ORDER_TO_FOODNAME_COUNTS = "-";

    private String inputFromUser(){
        return Console.readLine();
    }

    public LocalDate inputReservationDate(){
        String input = inputFromUser();
        canParseInt(input);
        int inputToNumber = Integer.parseInt(input);
        canConvertToDate(inputToNumber);
        return createDate(DECEMBER, inputToNumber);
    }

    public Map<String, Integer> inputOrderFoods(){
        String[] inputs = inputFromUser().split(SPLIT_REGEX);
        canParsingFormat(inputs);
        return convertOrderToMap(new ArrayList<>(Arrays.asList(inputs)));
    }

    public Map<String, Integer> convertOrderToMap(List<String> inputs){
        Map<String, Integer> toMap = new LinkedHashMap<>();
        for(String input : inputs){
            String[] items = input.split(SPLIT_ORDER_TO_FOODNAME_COUNTS);
            String foodName = items[FOODNAME];
            int cost = Integer.parseInt(items[FOODCOUNTS]);
            toMap.put(foodName, cost);
        }
        return toMap;
    }



}

package view;

import order.Order;

import static view.utils.checkTargetExist;
import static view.utils.valueToFormatString;

public class OutputView {

    public static final String LINE_BREAK = "\n";
    private static final String PRINT_NONE = "없음" + LINE_BREAK;

    public void initMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void guideUserInsertReservationDate(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void guideUserInsertFoodsAndCounts(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printPromotionResults(Order order){
        printPromotionHeader();
        printOrderedMenu(order);
        printTotalPriceBeforeDiscount(order);
        printGiveaway(order);
        printApplyPromotionList(order);
        printApplyPromotionsDiscount(order);
        printExpectedPayment(order);
        printPromotionBedge(order);
    }

    public void printPromotionHeader(){
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printOrderedMenu(Order order){
        System.out.println("<주문 메뉴>");
        System.out.println(order.printOrderMenu());
    }

    public void printTotalPriceBeforeDiscount(Order order){
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(
                String.format("%s원" + LINE_BREAK,
                        valueToFormatString(order.calculateTotalPrice()))
        );
    }

    public void printGiveaway(Order order){
        System.out.println("<증정 메뉴>");
        String freebies = order.printFreebies();
        System.out.println(checkTargetExist(freebies, PRINT_NONE));
    }

    public void printApplyPromotionList(Order order){
        System.out.println("<혜택 내역>");
        System.out.println(checkTargetExist(order.printPromotionResults(), PRINT_NONE));
    }

    public void printApplyPromotionsDiscount(Order order){
        System.out.println("<총혜택 금액>");
        System.out.println(
                String.format("%s원"+LINE_BREAK,
                        valueToFormatString(order.calculateTotalDiscount()))
        );
    }

    public void printExpectedPayment(Order order){
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(
                String.format("%s원" + LINE_BREAK,
                        valueToFormatString(
                                order.calculateTotalPrice() + order.calculateCostWithoutFreebies()
                        ))
        );
    }

    public void printPromotionBedge(Order order){
        System.out.println("<12월 이벤트 배지>");
        System.out.println(order.getBadge().getName());
    }

}

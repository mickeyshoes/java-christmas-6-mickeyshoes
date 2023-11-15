package order;

public enum OrderException {
    ORDER_FOOD_NOT_OVER_MAXIMUM("최대 %d개까지만 주문할 수 있습니다."),
    INVALID_ORDER_FROM_USER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    OFFER_FOOD_MUST_OVER_MINUMUM("사은품의 최소 제공량은 %d개 입니다.");

    OrderException(String message) { this.message = message; }

    private final String message;


    public String getMessageWithArgs(Object ...args) { return String.format(message, args); }
    public String getMessage() { return message; }

}

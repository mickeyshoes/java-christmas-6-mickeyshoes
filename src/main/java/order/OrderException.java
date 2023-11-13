package order;

public enum OrderException {
    ORDER_FOOD_NOT_OVER_MAXIMUM("최대 %d개까지만 주문할 수 있습니다."),
    INVALID_ORDER_FROM_USER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    OrderException(String message) { this.message = message; }

    private final String message;


    public String getMessageWithArgs(Object ...args) { return String.format(message, args); }
    public String getMessage() { return message; }

}

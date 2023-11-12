package food;

public enum FoodException {
    FOOD_PRICE_MUST_IN_RANGE("음식의 가격은 %d 이상 %d 이하여야 합니다."),
    FOOD_NAME_LENGTH("음식의 이름의 길이는 %d 이상이어야 합니다."),
    FOOD_NAME_IS_DUPLICATED("이미 %s 라는 이름을 가진 음식이 존재합니다."),
    FOOD_IS_NOT_EXIST_IN_MENU("%s 라는 이름을 가진 음식이 존재하지 않습니다."),
    ;

    FoodException(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessageWithArgs(Object ...args) {
        return String.format(message, args);
    }

    @Override
    public String toString() {
        return message;
    }
}

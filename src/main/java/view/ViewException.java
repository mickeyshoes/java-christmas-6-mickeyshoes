package view;

public enum ViewException {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    ;

    ViewException(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() { return message; }
}

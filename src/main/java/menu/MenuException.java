package menu;

public enum MenuException {
    MENU_IS_NOT_INITIALIZE("메뉴가 생성되지 않았습니다. 메뉴를 생성 해 주세요.");

    MenuException(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessageWithArgs(Object ...args) {
        return String.format(message, args);
    }

    public String getMessage() {
        return message;
    }
}

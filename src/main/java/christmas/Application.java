package christmas;

import controller.OrderController;
import view.InputView;
import view.OutputView;
import view.View;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        View view = new View(new InputView(), new OutputView());
        OrderController controller = new OrderController(view);
        controller.start();
    }
}

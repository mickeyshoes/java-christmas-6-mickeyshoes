package view;

public class View {
    public static final String errorHeader = "[Error]";

    public void printError(String message){
        System.out.println(
                String.format("%s %s", errorHeader, message)
        );
    }
}

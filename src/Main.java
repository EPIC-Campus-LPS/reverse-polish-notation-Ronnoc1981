import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("7 + 27 / 9 - ( 3 * 5 )");
        String eq = ReversePolishNotation.infixToPostfix("7 + 27 / 9 - ( 3 * 5 )");
        System.out.println(eq);
        System.out.println(ReversePolishNotation.evaluatePostfix(eq));
    }

}
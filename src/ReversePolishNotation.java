import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReversePolishNotation {

    public static int  evaluatePostfix(String input){
        Stack storage = new Stack();
        String[] splits = input.split(" ");
        int total = 0;
        for (int i = 1; i < splits.length; i++){
            if(Character.isDigit(splits[i].charAt(0))){
                storage.push(splits[i]);
            }else{
                int part1 = Integer.parseInt(storage.pop());
                int part2 = Integer.parseInt(storage.pop());
                total = math(part1, part2, splits[i]);
                storage.push(Integer.toString(total));

            }

        }
        return total;
    }
    public static String infixToPostfix(String input){
        Stack storage = new Stack();
        String[] splits = input.split(" ");
        String output = "";
        ArrayList<String> indexes = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "(", ")"));
        for (int i = 0; i < splits.length; i++){
            String spot = splits[i];
            if (!Character.isDigit(spot.charAt(0))){
                if (storage.isEmpty()){
                    storage.push(spot);
                } else{
                    int spotI = indexes.indexOf(spot);
                    if(spotI == 2){
                        spotI = 1;
                    } else if (spotI == 4) {
                        spotI = 3;
                    }
                    int ind = indexes.indexOf(storage.peek());
                    if(ind == 1){
                        ind = 0;
                    } else if (ind == 3) {
                        ind = 2;
                    }
                    while (spotI <= ind && !storage.isEmpty()){
                        ind = indexes.indexOf(storage.peek());
                        if (ind == 4){
                            break;
                        }
                        if(ind == 1){
                            ind = 0;
                        } else if (ind == 3) {
                            ind = 2;
                        }
                        output += " " + storage.pop();
                    }
                    if(spotI == 5){
                        while (ind != 4 && !storage.isEmpty()){
                            ind = indexes.indexOf(storage.peek());
                            if(ind != 4) {
                                output += " " + storage.pop();
                            }else{
                                storage.pop();
                            }
                        }
                    }else {
                        storage.push(spot);
                    }
                }
            }else{
                output += " " + splits[i];

            }
        }
        while(!storage.isEmpty()){
            output += " " + storage.pop();
        }
        return output;
    }
    private static int math(int i, int j, String operator){
        return switch (operator) {
            case "+" -> i + j;
            case "-" -> j - i;
            case "*" -> i * j;
            default -> j / i;
        };
    }
}

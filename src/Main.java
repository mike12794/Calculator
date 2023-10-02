import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static String [] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static String [] romansTens = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: "); String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        String [] parts = input.split(" ");
        String result = " ";

        if (((isDigit(parts[0]) && isDigit(parts[2])) || (isRoman(parts[0]) && isRoman(parts[2]))) && parts.length == 3){
            if (isRoman(parts[0])){
                int tempResult = Integer.parseInt(applyOperation(romanToArabic(parts[0]), romanToArabic(parts[2]), parts[1]));
                if (tempResult >= 1) {
                    result = arabicToRoman(tempResult);
                } else {
                    throw new Exception();
                }
            } else {
                result = applyOperation(parts[0], parts[2], parts[1]);
            }
        } else {
            throw new Exception();
        }

        return result;
    }

    //перевод римских в арабские
    public static String romanToArabic(String str) throws Exception {
        for(int i = 0; i < romans.length; i++){
            if(Objects.equals(str, romans[i])){
                return Integer.toString(++i);
            }
        }
        throw new Exception();
    }

    //перевод арабских в римские
    public static String arabicToRoman(int number){
        int firstNumber = number / 10;
        int secondNumber = number % 10;

        String romanFirstNumber = "";
        String romanSecondNumber = "";

        if(firstNumber > 0){ romanFirstNumber = romansTens[--firstNumber];}
        if(secondNumber > 0){ romanSecondNumber = romans[--secondNumber];}

        return (romanFirstNumber + romanSecondNumber);
    }

    public static boolean isDigit(String string) throws Exception {

        for (int i = 0; i < string.length(); i++){
            if (!Character.isDigit(string.charAt(i))) return false;
        }

        return Math.abs(Integer.parseInt(string)) <= 10;
    }

    public static boolean isRoman(String string){
        for (String element:romans) {
            if (Objects.equals(string, element)) return true;
        }
        return false;
    }

    public static String applyOperation(String number1, String number2, String operation) throws Exception {
        switch (operation){
            case ("+"):
                return Integer.toString(Integer.parseInt(number1) + Integer.parseInt(number2));
            case ("-"):
                return Integer.toString(Integer.parseInt(number1) - Integer.parseInt(number2));
            case ("*"):
                return Integer.toString(Integer.parseInt(number1) * Integer.parseInt(number2));
            case ("/"):
                if (Integer.parseInt(number2) != 0){
                    return Integer.toString(Integer.parseInt(number1) / Integer.parseInt(number2));
                } else {
                    throw new Exception();
                }
        }
        throw new Exception();
    }
}
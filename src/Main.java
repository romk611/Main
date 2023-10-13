import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("Введите выражение: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        DataReader reader = new DataReader();
        Calculator calc = new Calculator();

        reader.proverka(text);
        reader.read(text);
        int number1 = reader.getNumber1();
        int number2 = reader.getNumber2();
        char operation = reader.getOperation();
        int res = calc.calculated(number1, number2, operation);
        String result = reader.convertToRoman(res);

        if (reader.isFlag()) {
            result = String.valueOf(res);
        }

        System.out.println(result);


    }
}

class Calculator {

    public int calculated(int number1, int number2, char operation) {
        int res = 0;
        switch (operation) {
            case '+': res = number1 + number2; break;
            case '-': res = number1 - number2; break;
            case '*': res = number1 * number2; break;
            case '/': res = number1 / number2; break;
        }
        return res;
    }
}

class DataReader {
    public static int i;
    int res;
    boolean roman1;
    boolean roman2;
    boolean arabic1;
    boolean arabic2;
    private int number1;
    private int number2;
    private char operation;
    private boolean flag;

    private static int romanToNumber(String roman) {
        try {
            switch (roman) {
                case "I": return 1;
                case "II": return 2;
                case "III": return 3;
                case "IV": return 4;
                case "V": return 5;
                case "VI": return 6;
                case "VII": return 7;
                case "VIII": return 8;
                case "IX": return 9;
                case "X": return 10;
                default: return -1;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException(" Неверный формат");
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void proverka(String number) throws Exception {

        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] blocks = number.split(" ");

        if (blocks.length < 3) {
            throw new Exception("Строка не является математической операцией");
        } else if (blocks.length > 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один " +
                    "оператор (+, -, /, *)");
        }
        for (String s : roman) {
            if (s.equals(blocks[0])) roman1 = true;
            if (s.equals(blocks[2])) roman2 = true;
        }
        if ((!roman1 && roman2) || (roman1 && !roman2))
            throw new Exception("Используются одновременно разные системы счисления");
        if (!roman1 && !roman2) {
            for (int j = 1; j < 11; j++) {
                if (j == Integer.parseInt(blocks[0])) arabic1 = true;
                if (j == Integer.parseInt(blocks[2])) arabic2 = true;
            }
        }
        if ((!roman1 && !roman2) && (!arabic1 || !arabic2)) throw new Exception("Нужно вводит числа от 1 до 10");
    }

    public int read(String text) throws Exception {

        Integer[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] blocks = text.split(" ");


        try {
            for (int i = 0; i < arabic.length; i++) {

                int num1 = Integer.parseInt(blocks[0]);
                int num2 = Integer.parseInt(blocks[2]);

                if (num1 == i || num2 == i) {
                    this.number1 = num1;
                    this.number2 = num2;
                    switch (blocks[1]) {
                        case "+" -> operation = '+';
                        case "-" -> operation = '-';
                        case "*" -> operation = '*';
                        case "/" -> operation = '/';
                    }
                }
            }

            flag = true;

            return 0;

        } catch (Exception e) {

        }

        try {
            for (int i = 0; i < roman.length; i++) {

                int number1 = romanToNumber(blocks[0]);
                int number2 = romanToNumber(blocks[2]);

                if (number1 == i || number2 == i) {
                    this.number1 = number1;
                    this.number2 = number2;
                    switch (blocks[1]) {
                        case "+" -> operation = '+';
                        case "-" -> operation = '-';
                        case "*" -> operation = '*';
                        case "/" -> operation = '/';
                    }
                }
            }
        } catch (Exception e) {

        }

        return 0;
    }

    public String convertToRoman(int numberArabian) throws Exception {
        String s = null;
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV"
                , "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
                "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI",
                "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX"
                , "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV"
                , "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
                "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        try {
            s = roman[numberArabian];
        } catch (Exception e) {
            throw new Exception("В римской системе нет отрицательных чисел");
        }
        return s;
    }
    public int getNumber1() {
        return number1;
    }
    public int getNumber2() {
        return number2;
    }
    public char getOperation() {

        return operation;
    }

}
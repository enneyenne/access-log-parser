import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();

        int sum = firstNumber + secondNumber;
        int diff = firstNumber - secondNumber;
        int multiply = firstNumber * secondNumber;
        double quotient = (double) firstNumber / secondNumber;

        System.out.println("Сумма двух чисел: " + sum);
        System.out.println("Разность двух чисел: " + diff);
        System.out.println("Произведение двух чисел: " + multiply);
        System.out.println("Частное двух чисел: " + quotient);
    }
}
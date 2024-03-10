import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        finder();
    }

    public static void finder() {

        int fileCounter = 0;
        int findCounter = 0;

        for (; ;) {

            System.out.print("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists) {
                System.out.println("Указан путь к несуществующему файлу! Повторите попытку");
                System.out.println("Выполнено попыток поиска: " + ++findCounter);
                continue;
            }
            if (isDirectory) {
                System.out.println("Указан путь к папке! Повторите попытку");
                System.out.println("Выполнено попыток поиска: " + ++findCounter);
                continue;
            }
            fileCounter++;
            findCounter++;
            System.out.println("Путь указан верно. Это файл " + path + " с номером " + fileCounter);
            System.out.println("Выполнено попыток поиска: " + findCounter);
        }
    }
}

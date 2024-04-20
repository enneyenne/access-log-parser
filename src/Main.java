import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

            try {
                int minLineLength = Integer.MAX_VALUE;
                int maxLineLength = 0;
                int totalLinesCount = 0;

                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) {
                        throw new RuntimeException("Длина строки в файле более 1024 символов");
                    }
                    if (length > maxLineLength) {
                        maxLineLength = length;
                    }
                    if (length < minLineLength) {
                        minLineLength = length;
                    }
                    totalLinesCount++;
                }
                System.out.println("Общее количество строк в файле: " + totalLinesCount);
                System.out.println("Длина самой длинной строки в файле: " + maxLineLength);
                System.out.println("Длина самой короткой строки в файле: " + minLineLength);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

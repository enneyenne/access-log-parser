import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        finder();
    }

    public static void finder() {

        String line;
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

            int totalLinesCount = 0;
            int yandexBotCount = 0;
            int googleBotCount = 0;

            try {

                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) {
                        throw new RuntimeException("Длина строки в файле более 1024 символов");
                    }
                    totalLinesCount++;

                    String[] userAgent = (line.split("\""));
                    String userAgentInfo = userAgent[userAgent.length - 1];
                    String firstBrackets = userAgentInfo.replaceAll(".*\\(|\\).*", "");
                    String[] parts = firstBrackets.split(";");
                    if (parts.length >= 2) {
                        String fragment = parts[1];
                        String botName = fragment.trim().split("/")[0];

                        if (botName.equals("YandexBot")) {
                            yandexBotCount++;
                        } else if (botName.equals("Googlebot")) {
                            googleBotCount++;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("**********");
            System.out.println("Общее количество строк в файле: " + totalLinesCount);
            System.out.println("**********");
            System.out.println("Количество строк в файле, соответствующих запросам от YandexBot: " + yandexBotCount);
            System.out.println("Доля запросов от YandexBot относительно общего числа сделанных запросов: " +
                    String.format("%.4f", ((double) yandexBotCount / totalLinesCount) * 100) + " %");
            System.out.println("**********");
            System.out.println("Количество строк в файле, соответствующих запросам от Googlebot: " + googleBotCount);
            System.out.println("Доля запросов от Googlebot относительно общего числа сделанных запросов: " +
                    String.format("%,.4f", ((double) googleBotCount / totalLinesCount) * 100) + " %");
        }
    }
}

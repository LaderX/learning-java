import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private TreeMap<Long, String> pBook = new TreeMap<>();

    //
    public PhoneBook() {
        //Test data
        pBook.put(793213212121L, "Алексей");
        pBook.put(793213212122L, "Инокентий");
        pBook.put(793213212123L, "Сократ");
    }
    //

    public int size() {
        return pBook.size();
    }

    public static long phoneAnalyzer(String num) {
        num = num.replaceAll("\\D", "");
        if (num.length() == 11 && ((Character.digit(num.charAt(0), 10) == 7) ||
                Character.digit(num.charAt(0), 10) == 8)) {
            return Long.parseLong("7" + num.substring(1));
        } else if (num.length() == 10 && (Character.digit(num.charAt(0), 10) == 9)) {
            return Long.parseLong("7" + num);
        } else {
            System.out.println("Неверный формат номера");
            return -1;
        }
    }

    public void help() {
        System.out.println("Если пишем новое имя, программа просит ввести номер телефона и запоминает его. " +
                "Если новый номер телефона — просит ввести имя и также запоминает.\n" +
                "Если вводим существующее имя или номер телефона, программа выводит всю информацию о контакте.\n" +
                "При вводе команды LIST программа печатает в консоль " +
                "список всех абонентов в алфавитном порядке с номерами.");
    }

    public void show() {
        System.out.println("===start===");
        for (Map.Entry entry : pBook.entrySet()) {
            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
        System.out.println("===end===");
    }

    public void add(long number, String name) {
        pBook.put(number, name);
    }
}

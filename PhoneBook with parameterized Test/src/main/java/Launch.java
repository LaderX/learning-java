import java.util.Scanner;

public class Launch {


    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();
        Menu menu = new Menu(book);
        System.out.println("Введите команду, для отображения списка команд введите HELP");
        while (true) {
            menu.parsing(menu.waiting());
        }
    }
}

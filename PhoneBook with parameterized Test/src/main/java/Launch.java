public class Launch {


    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();
        ConsoleLineAccess consoleLineAccess = new ConsoleLineAccess(book);
        System.out.println("Введите команду, для отображения списка команд введите HELP");
        while (true) {
            consoleLineAccess.parsing(consoleLineAccess.waiting());
        }
    }
}

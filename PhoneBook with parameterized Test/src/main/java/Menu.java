import java.util.Scanner;

public class Menu {

    private PhoneBook book;
    private Scanner scan;
    private String nameInBook;
    private Long phoneInBook;

    public Menu(PhoneBook book) {
        this.book = book;
        this.scan = new Scanner(System.in);
    }

    public String waiting() {
        if (nameInBook == null && phoneInBook == null) {
            System.out.println("Система ждет комманды");
        } else if (!(nameInBook == null) && phoneInBook == null) {
            System.out.println("Вы ввели имя, система ждет номера");
        } else if ((nameInBook == null) && !(phoneInBook == null)) {
            System.out.println("Вы ввели номер, система ждет имени");
        }
        return scan.nextLine();
    }

    public void parsing(String command) {

        command = command.trim();

        if (command.toLowerCase().indexOf("help") == 0) {
            executeCommand("help");
        } else if (command.toLowerCase().indexOf("list") == 0) {
            executeCommand("list");
        } else if (command.indexOf('+') == 0 ||
                command.indexOf('7') == 0 ||
                command.indexOf('8') == 0 ||
                command.indexOf('9') == 0) {
            System.out.println("Вы ввели номер");
            long numInBook = PhoneBook.phoneAnalyzer(command);
            if (numInBook != -1) {
                System.out.println("После корректировки " + numInBook);
            }
            System.out.println("Введите имя");
            String name = this.waiting().trim();
            executeCommand("add", numInBook, name);
        } else {
            //тут имя
            System.out.println("Введите номер");
            long phoneNum = -2;
            while (phoneNum == -2) {
                phoneNum = this.waitingPhone();
            }
            if (phoneNum != -1) {
                executeCommand("add", phoneNum, command);
            } else {
                System.out.println("Вы отказались от ввода номера");
            }
        }
    }

    private void executeCommand(String command) {
        Command execute = Command.valueOf(command.toUpperCase());

        switch (execute) {
            case HELP -> book.help();
            case LIST -> book.show();
        }
    }

    public void executeCommand(String command, Long phone, String name) {
        Command execute = Command.valueOf(command.toUpperCase());

        if (execute == Command.ADD) {
            book.add(phone, name);
        }
    }

    private long waitingPhone() {
        String command = this.waiting();
        if (command.indexOf('+') == 0 ||
                command.indexOf('7') == 0 ||
                command.indexOf('8') == 0 ||
                command.indexOf('9') == 0) {
            System.out.println("Вы ввели номер");
            long rightNumber = PhoneBook.phoneAnalyzer(command);
            if (rightNumber != -1) {
                System.out.println("После корректировки " + rightNumber);
            }
            return rightNumber;
        } else if (command.toLowerCase().equals("exit")) {
            return -1;
        } else {
            System.out.println("Номер не корректен, введите корректный номер или ключевое слово exit");
            return -2;
        }
    }
}


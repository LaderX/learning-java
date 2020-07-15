public class Loader {

    public static void main(String[] args) throws OverMoneyException {
        BankAccount test = new BankAccount(0);
        test.depositMoney(1000000001);
        System.out.println(test.getBalance());
    }
}

package bank.persistence;

public class TransactionDAO {
    private static TransactionDAO instance;

    private TransactionDAO() {
    }

    public static TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

    public void addTransaction() {

    }
}

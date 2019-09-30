package pl.javastart.homebudget.repository;

import org.springframework.stereotype.Repository;
import pl.javastart.homebudget.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private List<Transaction> transactionList;

    public TransactionRepository() {
        transactionList = new ArrayList<>();
    }

    public List<Transaction> getAll() {
        return transactionList;
    }

    public List<Transaction> getByCategory(String category) {
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction t : transactionList) {
            if (category.equals(t.getTransactionType())) {
                transactions.add(t);
            }
        }
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }
}

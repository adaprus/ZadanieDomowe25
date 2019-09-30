package pl.javastart.homebudget.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.javastart.homebudget.factory.ConnectionFactory;
import pl.javastart.homebudget.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionDao {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionDao(ConnectionFactory connectionFactory) throws SQLException, ClassNotFoundException {
        connection = connectionFactory.createConnection();
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void add(Transaction transaction) throws SQLException {
        final String query = "INSERT INTO transactions(transaction_type, transaction_description, amount, transaction_date) VALUES(?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, transaction.getTransactionType());
        preparedStatement.setString(2, transaction.getDescription());
        preparedStatement.setDouble(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getTransactionDate());

        preparedStatement.executeUpdate();
    }

    public void update(Transaction transaction) throws SQLException {
        final String query = "UPDATE transactions SET transaction_type = ?, transaction_description = ?, amount = ?, transaction_date = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, transaction.getTransactionType());
        preparedStatement.setString(2, transaction.getDescription());
        preparedStatement.setDouble(3, transaction.getAmount());
        preparedStatement.setString(4, transaction.getTransactionDate());
        preparedStatement.setLong(5, transaction.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(Long id) throws SQLException {
        final String query = "DELETE FROM transactions WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    private Transaction read(Long id, String transaction_type) throws SQLException {
        final String query = "SELECT transaction_description, amount, transaction_date WHERE id = ? AND transaction_type = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, transaction_type);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            Transaction transaction = new Transaction();
            transaction.setId(resultSet.getLong("id"));
            transaction.setTransactionType(resultSet.getString("transaction_type"));
            transaction.setDescription(resultSet.getString("transaction_description"));
            transaction.setAmount(resultSet.getDouble("amount"));
            transaction.setTransactionDate(resultSet.getString("transaction_date"));
            return transaction;
        }
        return null;
    }


}

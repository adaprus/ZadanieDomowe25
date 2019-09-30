package pl.javastart.homebudget.model;

import java.sql.Date;

public class Transaction {
    private long id;
    private String transactionType;
    private String description;
    private double amount;
    private String transactionDate;

    public Transaction(String type, String description, double amount, String date) {
        this.transactionType = type;
        this.description = description;
        this.amount = amount;
        this.transactionDate = date;
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}

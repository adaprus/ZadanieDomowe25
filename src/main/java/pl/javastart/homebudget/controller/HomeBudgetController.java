package pl.javastart.homebudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.javastart.homebudget.dao.TransactionDao;
import pl.javastart.homebudget.model.Transaction;
import pl.javastart.homebudget.repository.TransactionRepository;

import java.sql.SQLException;

@Controller
public class HomeBudgetController {
    private TransactionRepository transactionRepository;
    private TransactionDao transactionDao;

    public HomeBudgetController(TransactionRepository transactionRepository, TransactionDao transactionDao) {
        this.transactionRepository = transactionRepository;
        this.transactionDao = transactionDao;
    }

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/add")
    @ResponseBody
    public String addProduct(@RequestParam String transactionType,
                             @RequestParam String description,
                             @RequestParam double amount,
                             @RequestParam String transactionDate) throws SQLException {
        try {
            Transaction transaction = new Transaction(transactionType, description, amount, transactionDate);
            transactionRepository.addTransaction(transaction);
            transactionDao.add(transaction);

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Dodano transakcję";
    }
}

package org.geekhub.hw7;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Analyzer implements TransactionAnalyzer {

    private final Collection<Transaction> transactions;

    public Analyzer(Collection<Transaction> transactionsToAnalyze) {
        transactions = transactionsToAnalyze;
    }

    @Override
    public Optional<Transaction> getBiggestTransactionInCategory(String category) {
        return transactions.stream()
            .filter(transaction -> transaction.category().equals(category))
            .max(Comparator.comparingDouble(Transaction::amount));
    }

    @Override
    public double getTotalSpentForDate(LocalDate date) {
        return transactions.stream()
            .filter(transaction -> transaction.date().equals(date))
            .count();
    }

    @Override
    public List<Transaction> getTransactionsByCategoryAndDate(String category, LocalDate date) {
        return transactions.stream()
            .filter(transaction -> transaction.category().equals(category))
            .filter(transaction -> transaction.date().equals(date))
            .toList();
    }

    @Override
    public Map<String, Double> getSpentAmountByCategory() {
        return null;
    }

    @Override
    public Optional<LocalDate> getDateWithMostExpenses() {
        return Optional.empty();
    }

    @Override
    public Map<String, Double> getAverageSpendingPerCategory() {
        return null;
    }

    @Override
    public Optional<String> getMostPopularCategory() {
        return Optional.empty();
    }

    @Override
    public Map<String, Double> getCategoryWiseDistribution() {
        return null;
    }
}

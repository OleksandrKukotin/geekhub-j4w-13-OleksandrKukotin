package org.geekhub.hw7;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Analyzer implements TransactionAnalyzer {

    private final Collection<Transaction> transactions;

    public Analyzer(Collection<Transaction> transactionsToAnalyze) {
        transactions = transactionsToAnalyze;
    }

    @Override
    public Optional<Transaction> getBiggestTransactionInCategory(String category) {
        if (transactions.isEmpty()) {
            return Optional.empty();
        }
        return transactions.stream()
            .filter(transaction -> transaction.category().equals(category))
            .max(Comparator.comparingDouble(Transaction::amount));
    }

    @Override
    public double getTotalSpentForDate(LocalDate date) {
        if (transactions.isEmpty()) {
            return 0.0;
        }
        return transactions.stream()
                .filter(transaction -> transaction.date().equals(date)).mapToDouble(Transaction::amount).sum();
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
        if (transactions.isEmpty()) {
            return new HashMap<>();
        }
        return transactions.stream()
            .sorted()
            .collect(Collectors.toMap(Transaction::category, Transaction::amount, Double::sum));
    }

    @Override
    public Optional<LocalDate> getDateWithMostExpenses() {
        if (transactions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Double> getAverageSpendingPerCategory() {
        return transactions.stream()
            .collect(Collectors.toMap(Transaction::category, Transaction::amount));
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

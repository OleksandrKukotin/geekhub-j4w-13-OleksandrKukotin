package org.geekhub.hw7;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Map<String, Double> totalAmountByCategory = transactions.stream()
            .collect(Collectors.toMap(Transaction::category, Transaction::amount, Double::sum));
        return totalAmountByCategory.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    @Override
    public Optional<LocalDate> getDateWithMostExpenses() {
        if (transactions.isEmpty()) {
            return Optional.empty();
        }
        Map<LocalDate, Double> totalExpensesByDate = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::date, Collectors.summingDouble(Transaction::amount)));

        return totalExpensesByDate.entrySet().stream()
            .max(Comparator.comparingDouble(Map.Entry::getValue))
            .map(Map.Entry::getKey);
    }

    @Override
    public Map<String, Double> getAverageSpendingPerCategory() {
        if (transactions.isEmpty()) {
            return new HashMap<>();
        }
        return transactions.stream()
            .collect(Collectors.groupingBy(Transaction::category, Collectors.averagingDouble(Transaction::amount)));
    }

    @Override
    public Optional<String> getMostPopularCategory() {
        if (transactions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Double> getCategoryWiseDistribution() {
        if (transactions.isEmpty()) {
            return new HashMap<>();
        }
        return null;
    }
}

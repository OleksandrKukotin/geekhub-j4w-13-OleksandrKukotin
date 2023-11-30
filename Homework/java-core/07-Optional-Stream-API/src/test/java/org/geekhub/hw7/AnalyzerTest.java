package org.geekhub.hw7;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyzerTest {
    private static final String FIRST_CATEGORY = "Books";

    private static final String SECOND_CATEGORY = "Food";
    private static final String THIRD_CATEGORY = "Entertainment";

    @Test
    void GetBiggestTransactionInCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.now()),
            new Transaction(100.0, THIRD_CATEGORY, LocalDate.MIN),
            new Transaction(20.0, THIRD_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        assertEquals(100.0, analyzer.getBiggestTransactionInCategory(THIRD_CATEGORY).get().amount());
        assertEquals(50.0, analyzer.getBiggestTransactionInCategory(FIRST_CATEGORY).get().amount());
        assertTrue(analyzer.getBiggestTransactionInCategory("Nonexistent").isEmpty());
    }

    @Test
    void GetBiggestTransactionInCategory_withEmptyCollection_shouldReturnEmptyOptional() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(Optional.empty(), analyzer.getBiggestTransactionInCategory(FIRST_CATEGORY));
    }

    @Test
    void testGetSpentAmountByCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put(SECOND_CATEGORY, 150.0);
        expectedMap.put(FIRST_CATEGORY, 50.0);
        expectedMap.put(THIRD_CATEGORY, 30.0);

        assertEquals(expectedMap, analyzer.getSpentAmountByCategory());
    }

    @Test
    void testGetDateWithMostExpenses() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(30.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(20.0, FIRST_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        assertEquals(LocalDate.now(), analyzer.getDateWithMostExpenses().get());
    }

    @Test
    void testGetTotalSpentForDate() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MIN),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MIN),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MIN),
            new Transaction(80.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(30.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(15.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(20.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(10.0, SECOND_CATEGORY, LocalDate.MAX)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        assertEquals(150.0, analyzer.getTotalSpentForDate(LocalDate.MIN));
        assertEquals(160.0, analyzer.getTotalSpentForDate(LocalDate.now()));
        assertEquals(45.0, analyzer.getTotalSpentForDate(LocalDate.MAX));
    }

    @Test
    void testGetTransactionsByCategoryAndDate() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(50.0, THIRD_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        assertEquals(0, analyzer.getTransactionsByCategoryAndDate(FIRST_CATEGORY, LocalDate.now()).size());
        assertEquals(2, analyzer.getTransactionsByCategoryAndDate(SECOND_CATEGORY, LocalDate.MAX).size());
        assertEquals(1, analyzer.getTransactionsByCategoryAndDate(THIRD_CATEGORY, LocalDate.MIN).size());
    }

    @Test
    void testGetAverageSpendingPerCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(40.0, SECOND_CATEGORY, LocalDate.MIN),
            new Transaction(20.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_CATEGORY, 45.0);
        expectedMap.put(SECOND_CATEGORY, 40.0);
        expectedMap.put(THIRD_CATEGORY, 30.0);

        assertEquals(expectedMap, analyzer.getAverageSpendingPerCategory());
    }

    @Test
    void testGetMostPopularCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(10.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(10.0, THIRD_CATEGORY, LocalDate.now())
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        assertEquals(FIRST_CATEGORY, analyzer.getMostPopularCategory().get());
    }

    @Test
    void testGetCategoryWiseDistribution() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(30.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(20.0, THIRD_CATEGORY, LocalDate.now())
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_CATEGORY, 0.5);
        expectedMap.put(SECOND_CATEGORY, 0.3);
        expectedMap.put(THIRD_CATEGORY, 0.2);

        assertEquals(expectedMap, analyzer.getCategoryWiseDistribution());
    }
}

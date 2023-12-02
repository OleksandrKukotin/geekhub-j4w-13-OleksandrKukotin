package org.geekhub.hw7;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyzerTest {

    private static final String FIRST_CATEGORY = "Books";

    private static final String SECOND_CATEGORY = "Food";
    private static final String THIRD_CATEGORY = "Entertainment";

    @Test
    void getBiggestTransactionInCategory_withEmptyCollection_shouldReturnEmptyOptional() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(Optional.empty(), analyzer.getBiggestTransactionInCategory(FIRST_CATEGORY));
    }

    @Test
    void getBiggestTransactionInCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.now()),
            new Transaction(100.0, THIRD_CATEGORY, LocalDate.MIN),
            new Transaction(20.0, THIRD_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Optional<Transaction> firstCategoryResult = analyzer.getBiggestTransactionInCategory(FIRST_CATEGORY);
        Optional<Transaction> thirdCategoryResult = analyzer.getBiggestTransactionInCategory(THIRD_CATEGORY);

        firstCategoryResult.ifPresent(transaction -> assertEquals(50, transaction.amount()));
        thirdCategoryResult.ifPresent(transaction -> assertEquals(100, transaction.amount()));
        assertTrue(analyzer.getBiggestTransactionInCategory("Nonexistent").isEmpty());
    }

    @Test
    void getTotalSpentForDate_withEmptyCollection_shouldReturnZero() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(0.0, analyzer.getTotalSpentForDate(LocalDate.now()));
    }

    @Test
    void getTotalSpentForDate() {
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
    void getTransactionsByCategoryAndDate_withEmptyCollection_shouldReturnEmptyList() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(new ArrayList<>(), analyzer.getTransactionsByCategoryAndDate(SECOND_CATEGORY, LocalDate.now()));
    }

    @Test
    void getTransactionsByCategoryAndDate() {
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
    void getSpentAmountByCategory_withEmptyCollection_shouldReturnEmptyMap() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(new HashMap<>(), analyzer.getSpentAmountByCategory());
    }

    @Test
    void getSpentAmountByCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(50.0, SECOND_CATEGORY, LocalDate.MAX),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.MAX),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.MAX)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new LinkedHashMap<>();
        expectedMap.put(SECOND_CATEGORY, 150.0);
        expectedMap.put(THIRD_CATEGORY, 60.0);
        expectedMap.put(FIRST_CATEGORY, 50.0);

        Map<String, Double> actualMap = analyzer.getSpentAmountByCategory();

        assertEquals(expectedMap, actualMap);
        Assertions.assertThat(actualMap).containsExactlyEntriesOf(expectedMap);
    }

    @Test
    void getDateWithMostExpenses_withEmptyCollection_shouldReturnEmptyOptional() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(Optional.empty(), analyzer.getDateWithMostExpenses());
    }

    @Test
    void getDateWithMostExpenses() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(30.0, FIRST_CATEGORY, LocalDate.MAX),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Optional<LocalDate> actualDate = analyzer.getDateWithMostExpenses();
        actualDate.ifPresent(date -> assertEquals(LocalDate.now(), date));
    }

    @Test
    void getAverageSpendingPerCategory_withEmptyCollection_shouldReturnEmptyMap() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(new HashMap<>(), analyzer.getAverageSpendingPerCategory());
    }

    @Test
    void getAverageSpendingPerCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(40.0, SECOND_CATEGORY, LocalDate.MIN),
            new Transaction(20.0, FIRST_CATEGORY, LocalDate.MIN),
            new Transaction(30.0, THIRD_CATEGORY, LocalDate.MIN)
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_CATEGORY, 35.0);
        expectedMap.put(SECOND_CATEGORY, 40.0);
        expectedMap.put(THIRD_CATEGORY, 30.0);

        assertEquals(expectedMap, analyzer.getAverageSpendingPerCategory());
    }

    @Test
    void getMostPopularCategory_withEmptyCollection_shouldReturnEmptyOptional() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(Optional.empty(), analyzer.getMostPopularCategory());
    }

    @Test
    void getMostPopularCategory() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(10.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(10.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(10.0, THIRD_CATEGORY, LocalDate.now())
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Optional<String> result = analyzer.getMostPopularCategory();
        result.ifPresent(s -> assertEquals(FIRST_CATEGORY, s));
    }

    @Test
    void getCategoryWiseDistribution_withEmptyCollection_shouldReturnEmptyMap() {
        TransactionAnalyzer analyzer = new Analyzer(new ArrayList<>());

        assertEquals(new HashMap<>(), analyzer.getCategoryWiseDistribution());
    }

    @Test
    void getCategoryWiseDistribution() {
        List<Transaction> transactions = Arrays.asList(
            new Transaction(50.0, FIRST_CATEGORY, LocalDate.now()),
            new Transaction(30.0, SECOND_CATEGORY, LocalDate.now()),
            new Transaction(20.0, THIRD_CATEGORY, LocalDate.now())
        );

        TransactionAnalyzer analyzer = new Analyzer(transactions);

        Map<String, Double> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_CATEGORY, 50.0);
        expectedMap.put(SECOND_CATEGORY, 30.0);
        expectedMap.put(THIRD_CATEGORY, 20.0);

        assertEquals(expectedMap, analyzer.getCategoryWiseDistribution());
    }
}

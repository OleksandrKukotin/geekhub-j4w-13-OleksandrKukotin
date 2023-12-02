package org.geekhub.hw7;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Objects;

public record Transaction(double amount, @Nonnull String category, @Nonnull LocalDate date) {

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Transaction that = (Transaction) obj;
        return Double.compare(this.amount, that.amount) == 0 &&
            this.category.equals(that.category) &&
            this.date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, category, date);
    }

    @Override
    public String toString() {
        return String.format("Transaction{amount=%.2f, category='%s', date=%s}", amount, category, date);
    }
}

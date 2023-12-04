package org.geekhub.learnit.model;

import java.util.Map;
import java.util.Objects;

public record Student(String name, Map<String, Double> scores) {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() == obj.getClass()) {
            Student that = (Student) obj;
            return this.name.equals(that.name) && this.scores.equals(that.scores);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scores);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s:%nScores:%n", name));
        for (Map.Entry<String, Double> entry:scores.entrySet()) {
            result.append(String.format("%s - %f%n", entry.getKey(), entry.getValue()));
        }
        return result.toString();
    }
}

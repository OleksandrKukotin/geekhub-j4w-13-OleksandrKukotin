package org.geekhub.hw6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatFactsApplicationTest {

    @Test
    void main_withNoArguments_shouldThrowIllegalArgumentException() {
        String[] empty = {};
        assertThrows(IllegalArgumentException.class, () -> CatFactsApplication.main(empty));
    }

    @Test
    void main_withCorrectArguments_RunsSuccessfully() {
        String[] args = {"file.txt", "4"};
        assertDoesNotThrow(() -> CatFactsApplication.main(args));
    }
}

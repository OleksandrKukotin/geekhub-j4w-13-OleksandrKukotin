package org.geekhub.hw6;

import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

class FileServiceTest {

    @BeforeEach
    void setUp() {
        new FileService(Paths.get("test.txt"));
    }


}

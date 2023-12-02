package org.geekhub.example.entity;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public record FileContent(FileInfo fileInfo, byte[] content) {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        FileContent that = (FileContent) obj;
        return this.fileInfo().equals(that.fileInfo()) && (Arrays.equals(this.content, that.content));
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("File info:%s%nContent: %s", fileInfo, new String(content, StandardCharsets.UTF_8));
    }
}

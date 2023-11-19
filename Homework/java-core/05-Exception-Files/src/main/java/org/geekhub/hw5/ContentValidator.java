package org.geekhub.hw5;

import org.geekhub.hw5.exception.InvalidContentLengthException;
import org.geekhub.hw5.exception.FileExistException;
import org.geekhub.hw5.exception.LimitSizeException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContentValidator {

    private static final int CONTENT_LENGTH_IS_NOT_KNOWN = -1;

    private final int maxFileSize;

    public ContentValidator(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public boolean isValid(URL url, String pathToFile, String filename)
        throws IOException {
        final URLConnection urlConnection = url.openConnection();
        int contentLength = urlConnection.getContentLength();
        hasContent(contentLength, url);
        validateContentLength(contentLength, url);

        isExistFile(pathToFile, filename, url);

        return true;
    }

    private void hasContent(int contentLength, URL url) throws InvalidContentLengthException {
        if (contentLength == CONTENT_LENGTH_IS_NOT_KNOWN) {
            throw new InvalidContentLengthException(String.join(" ", "Cannot download file from url:",
                url.toString() + "\n"));
        }
    }

    private void validateContentLength(int contentLength, URL url) {
        if (contentLength > maxFileSize) {
            throw new LimitSizeException(String.join(" ", "Failed to download from url:",
                url.toString(), "over", String.valueOf(maxFileSize)) + "\n");
        }
    }

    private void isExistFile(String pathToFile, String filename, URL url) throws FileExistException {
        if (Files.exists(Path.of(pathToFile, filename))) {
            throw new FileExistException(String.join(": ", "File you trying to work with " +
                "is already exist", url.toString()));
        }
    }
}

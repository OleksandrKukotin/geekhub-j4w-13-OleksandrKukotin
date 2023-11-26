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

        isExistFile(pathToFile, filename);

        return true;
    }

    private void hasContent(int contentLength, URL url) throws InvalidContentLengthException {
        if (contentLength == CONTENT_LENGTH_IS_NOT_KNOWN) {
            String exceptionMessage = "Cannot download file from url: %s%n";
            throw new InvalidContentLengthException(String.format(exceptionMessage , url.toString()));
        }
    }

    private void validateContentLength(int contentLength, URL url) {
        if (contentLength > maxFileSize) {
            String exceptionMessage = "Failed to download from url: %s over %d%n";
            throw new LimitSizeException(String.format(exceptionMessage, url.toString(), maxFileSize));
        }
    }

    private void isExistFile(String pathToFile, String filename) throws FileExistException {
        if (Files.exists(Path.of(pathToFile, filename))) {
            String exceptionMessage = "File you trying to work with (%s) is already exist";
            throw new FileExistException(String.format(exceptionMessage, pathToFile+filename));
        }
    }
}

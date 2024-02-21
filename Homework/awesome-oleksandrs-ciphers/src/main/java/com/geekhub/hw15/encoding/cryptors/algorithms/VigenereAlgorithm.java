package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.encoding.cryptors.Decryptor;
import com.geekhub.hw15.encoding.cryptors.Encryptor;
import com.geekhub.hw15.exception.WrongKeyException;

import java.util.stream.Collectors;

public class VigenereAlgorithm implements Encryptor, Decryptor {

    private final String keyword;

    public VigenereAlgorithm(String keyword) {
        checkKey(keyword);
        this.keyword = keyword.toUpperCase(); // Convert keyword to uppercase for consistency
    }

    private void checkKey(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new WrongKeyException("Keyword is empty");
        }
    }

    @Override
    public String encrypt(String inputMessage) {
        return inputMessage.chars()
            .mapToObj(character -> Character.isLetter(character) ?
                String.valueOf(processCharacter((char) character, keyword.charAt(0) - 'A')) :
                String.valueOf((char) character))
            .collect(Collectors.joining());
    }

    @Override
    public String decrypt(String inputMessage) {
        return inputMessage.chars()
            .mapToObj(character -> Character.isLetter(character) ?
                String.valueOf(processCharacter((char) character, -(keyword.charAt(0) - 'A'))) :
                String.valueOf((char) character))
            .collect(Collectors.joining());
    }

    private char processCharacter(char character, int shift) {
        char base = Character.isUpperCase(character) ? 'A' : 'a';
        return (char) ((character - base + shift + 26) % 26 + base);
    }
}

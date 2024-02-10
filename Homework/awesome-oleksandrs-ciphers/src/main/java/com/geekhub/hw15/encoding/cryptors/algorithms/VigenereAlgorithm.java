package com.geekhub.hw15.encoding.cryptors.algorithms;

import com.geekhub.hw15.encoding.cryptors.Decryptor;
import com.geekhub.hw15.encoding.cryptors.Encryptor;

public class VigenereAlgorithm implements Encryptor, Decryptor {

    private final String keyword;

    public VigenereAlgorithm(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String encrypt(String inputMessage) {
        int keywordLength = keyword.length();
        final int[] keywordIndex = {0};

        return inputMessage.chars()
            .mapToObj(character -> {
                int shift = keyword.charAt(keywordIndex[0]) - 'A';
                keywordIndex[0] = (keywordIndex[0] + 1) % keywordLength;
                return processCharacter((char) character, true, shift);
            })
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    @Override
    public String decrypt(String inputMessage) {
        int keywordLength = keyword.length();
        final int[] keywordIndex = {0};

        return inputMessage.chars()
            .mapToObj(character -> {
                int shift = keyword.charAt(keywordIndex[0]) - 'A';
                keywordIndex[0] = (keywordIndex[0] + 1) % keywordLength;
                return processCharacter((char) character, false, shift);
            })
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    private char processCharacter(char character, boolean isEncrypt, int shift) {
        if (Character.isLetter(character)) {
            char base = Character.isUpperCase(character) ? 'A' : 'a';
            int adjustedShift = isEncrypt? shift : -shift;
            return (char) ((character - base + adjustedShift + 26) % 26 + base);
        } else {
            return character;
        }
    }
}
